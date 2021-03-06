package com.giggle.repositories.event;

import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.EventUser;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 24/02/16.
 */
@Singleton
public interface EventRepository {
    List<Event> getEvents();
    List<EventType> getEventTypes();
    Event addEvent(Event event);
    Event getEventWithId(long id);
    void addEventUser(EventUser eventUser);
    boolean eventExists(Event event);
    EventUser getEventUserRelationship(long eventId, long userId);
    void createEventUser(EventUser eventUser);
    void updateEventUser(EventUser eventUser);
}
