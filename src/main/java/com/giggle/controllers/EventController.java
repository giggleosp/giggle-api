package com.giggle.controllers;

import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.ConflictException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.EventUser;
import com.giggle.models.User;
import com.giggle.repositories.event.EventRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by enda on 24/02/16.
 */
@RestController
@RequestMapping("/events/")
public class EventController {

    private final EventRepository repo;

    @Inject
    public EventController(EventRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ACT','ROLE_VENUE')")
    public @ResponseBody long addEvent(
            @RequestPart Event event, @RequestPart User user,
            @RequestPart(required = false) MultipartFile photo) {

        if (event.getAct() != null && event.getVenue() != null && eventExists(event)) {
            throw new ConflictException("This event has already been created.");
        } else {
            if (photo != null) {
                event.setImageUri(ResourceController.uploadPhoto(photo, "events"));
            }

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

    @RequestMapping(value = "event_user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody EventUser getEventUserRelationship(@RequestParam long event,
                                                            @RequestParam long user) {
        EventUser eventUser = repo.getEventUserRelationship(event, user);

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

    private boolean eventExists(Event event) {
        return repo.eventExists(event);
    }

    @RequestMapping(value = "event_user/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody void createEventUser(@RequestBody EventUser eventUser) {
        if (eventUser.getUser() != null && eventUser.getEvent() != null)
            repo.createEventUser(eventUser);
        else
            throw new BadRequestException();
    }

    @RequestMapping(value = "event_user/update", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateEventUser(@RequestBody EventUser eventUser) {
        if (eventUser != null)
            repo.updateEventUser(eventUser);
    }
}
