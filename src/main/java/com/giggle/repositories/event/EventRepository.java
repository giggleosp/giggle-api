package com.giggle.repositories.event;

import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.User;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 24/02/16.
 */
@Singleton
public interface EventRepository {
    List<Event> getEvents();
    List<Event> getRecommendedEvents(User user);
    List<EventType> getEventTypes();
    Event addEvent(Event event);
    boolean eventExists(Event event);
}
