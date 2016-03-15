package com.giggle.controllers;

import com.giggle.Application;
import com.giggle.exceptions.ConflictException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.helpers.StringGenerator;
import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.EventUser;
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
    public @ResponseBody long addEvent(
            @RequestPart Event event, @RequestPart User user,
            @RequestPart(required = false) MultipartFile photo) {

        if (event.getAct() != null && event.getVenue() != null && eventExists(event)) {
            throw new ConflictException("This event has already been created.");
        } else {
            if (photo != null)
                event.setImageUri(uploadPhoto(photo));

            event.setDateCreated(new Timestamp(System.currentTimeMillis()));

            event = repo.addEvent(event);

            EventUser eventUser = new EventUser(event, user);
            eventUser.setAdmin(true);
            repo.addEventUser(eventUser);

            return event.getId();
        }
    }

    @RequestMapping(value = "event/{id}", method = RequestMethod.GET)
    public @ResponseBody Event getEventWithId(@PathVariable long id) {
        Event event = repo.getEventWithId(id);

        if (event == null) {
            throw new NotFoundException();
        }

        return event;
    }

    @RequestMapping(value = "event/{eventId}/user/{userId}", method = RequestMethod.GET)
    public @ResponseBody EventUser getEventUserRelationship(@PathVariable long eventId,
                                                            @PathVariable long userId) {
        EventUser eventUser = repo.getEventUserRelationship(eventId, userId);

        if(eventUser == null) {
            throw new NotFoundException();
        }

        return eventUser;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public @ResponseBody List<Event> getEvents() {
        List<Event> events = repo.getEvents();

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
        fileName += ".png";

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

    private boolean eventExists(Event event) {
        return repo.eventExists(event);
    }
}
