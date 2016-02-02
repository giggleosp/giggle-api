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

    public JPAJinqStream<Country> countries(EntityManager em) {
        return streams.streamAll(em, Country.class);
    }

    public JPAJinqStream<County> counties(EntityManager em) {
        return streams.streamAll(em, County.class);
    }
}
