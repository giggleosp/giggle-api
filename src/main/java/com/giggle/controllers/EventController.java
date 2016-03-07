package com.giggle.controllers;

import com.giggle.Application;
import com.giggle.exceptions.ConflictException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.helpers.StringGenerator;
import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.User;
import com.giggle.repositories.event.EventRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by enda on 24/02/16.
 */
@RestController
@RequestMapping("/events/")
public class EventController {

    private final EventRepository repo;

    @Inject public EventController(EventRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody Event addEvent(@RequestPart Event event, @RequestPart(required = false) MultipartFile photo) {
        if (eventExists(event)) {
            throw new ConflictException();
        } else {
            if (photo != null)
                event.setImageUri(uploadPhoto(photo));

            event.setDateCreated(new Timestamp(System.currentTimeMillis()));
            return repo.addEvent(event);
        }
    }

    private boolean eventExists(Event event) {
        return repo.eventExists(event);
    }

    @RequestMapping(value = "recommended", method = RequestMethod.POST)
    public @ResponseBody List<Event> getRecommendedEvents(@RequestBody User user) {
        List<Event> events = repo.getRecommendedEvents(user);

        if (events == null)
            throw new NotFoundException();

        return events;
    }

    @RequestMapping(value = "types", method = RequestMethod.GET)
    public @ResponseBody List<EventType> getEventTypes() {
        List<EventType> types = repo.getEventTypes();

        if (types == null) {
            throw new NotFoundException();
        } else {
            return types;
        }
    }

    public String uploadPhoto(MultipartFile photo) {
        String fileName = StringGenerator.generateString();

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();

                File dir = new File(Application.IMAGES_DIRECTORY + File.separator + "events");
                if(!dir.exists())
                    dir.mkdirs();

                File file = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                while (file.exists()) {
                    // regenerate file name if there is a conflict
                    fileName = StringGenerator.generateString();
                }
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
            } catch (Exception ignored) {
            }
        }
        return "images/events/" + fileName;
    }
}
