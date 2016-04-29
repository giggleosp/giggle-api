package com.giggle.repositories.event;

import com.giggle.JinqSource;
import com.giggle.models.Event;
import com.giggle.models.EventType;
import com.giggle.models.EventUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by enda on 24/02/16.
 */
@Repository
public class EventRepositoryImpl implements EventRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public List<Event> getEvents() {
        List<Event> events = source.events(em)
                .toList();
        return events.isEmpty() ? null : events;
    }


    @Override
    public List<EventType> getEventTypes() {
        List<EventType> types = source.eventTypes(em)
                .sortedBy(EventType::getName)
                .toList();
        return types.isEmpty() ? null : types;
    }

    @Override @Transactional
    public Event addEvent(Event event) {
        em.persist(event);
        return event;
    }

    @Override
    public Event getEventWithId(long id) {
        List<Event> events = source.events(em)
                .where(e -> e.getId() == id)
                .toList();
        return events.isEmpty() ? null : events.get(0);
    }

    @Override @Transactional
    public void addEventUser(EventUser eventUser) {
        em.persist(eventUser);
    }

    @Override
    public boolean eventExists(Event event) {
        List<Event> events =
                source.events(em)
                        .filter(e -> e.getAct() != null && e.getAct().getId() == event.getAct().getId())
                        .filter(e -> e.getVenue() != null && e.getVenue().getId() == event.getVenue().getId())
                        .filter(e -> e.getStartDate().equals(event.getStartDate()))
                .collect(Collectors.toList());
        return !events.isEmpty();
    }

    @Override
    public EventUser getEventUserRelationship(long eventId, long userId) {
        List<EventUser> results =
                source.eventUsers(em)
                        .where(e -> e.getEvent().getId() == eventId)
                        .where(e -> e.getUser().getId() == userId)
                        .toList();

        return results.isEmpty() ? null : results.get(0);
    }

    @Override @Transactional
    public void createEventUser(EventUser eventUser) {
        em.persist(eventUser);
    }

    @Override @Transactional
    public void updateEventUser(EventUser eventUser) {
        em.merge(eventUser);
    }
}
