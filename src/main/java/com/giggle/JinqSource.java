package com.giggle;

import com.giggle.models.*;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Enda on 22/11/2015.
 */
@Component
public class JinqSource {
    private JinqJPAStreamProvider streams;

    @PersistenceUnit
    public void setEntityManagerFactory(
            EntityManagerFactory emf) throws Exception {
        streams = new JinqJPAStreamProvider(emf);
        // Do any additional Jinq initialization needed here.
    }

    // Wrapper that passes through Jinq requests to Jinq
    public <U> JPAJinqStream<U> streamAll(
            EntityManager em, Class<U>entity) {
        return streams.streamAll(em, entity);
    }

    public JPAJinqStream<User> users(EntityManager em) {
        return streams.streamAll(em, User.class);
    }

    public JPAJinqStream<UserRole> roles(EntityManager em) {
        return streams.streamAll(em, UserRole.class);
    }

    public JPAJinqStream<Genre> genres(EntityManager em) {
        return streams.streamAll(em, Genre.class);
    }

    public JPAJinqStream<Country> countries(EntityManager em) {
        return streams.streamAll(em, Country.class);
    }

    public JPAJinqStream<County> counties(EntityManager em) {
        return streams.streamAll(em, County.class);
    }

    public JPAJinqStream<City> cities(EntityManager em) {
        return streams.streamAll(em, City.class);
    }

    public JPAJinqStream<Venue> venues(EntityManager em) {
        return streams.streamAll(em, Venue.class);
    }

    public JPAJinqStream<VenueType> venueTypes(EntityManager em) { return streams.streamAll(em, VenueType.class); }

    public JPAJinqStream<UserVenue> userVenues(EntityManager em) {
        return streams.streamAll(em, UserVenue.class);
    }

    public JPAJinqStream<ActCategory> actCategories(EntityManager em) { return streams.streamAll(em, ActCategory.class); }

    public JPAJinqStream<ActUser> actUsers(EntityManager em) {
        return streams.streamAll(em, ActUser.class);
    }

    public JPAJinqStream<Act> acts(EntityManager em) {
        return streams.streamAll(em, Act.class);
    }

    public JPAJinqStream<Event> events(EntityManager em) {
        return streams.streamAll(em, Event.class);
    }

    public JPAJinqStream<EventUser> eventUsers(EntityManager em) {
        return streams.streamAll(em, EventUser.class);
    }

    public JPAJinqStream<EventType> eventTypes(EntityManager em) {
        return streams.streamAll(em, EventType.class);
    }
}
