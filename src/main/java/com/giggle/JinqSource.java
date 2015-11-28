package com.giggle;

import com.giggle.models.City;
import com.giggle.models.User;
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

    // You can include helper methods here too
    public JPAJinqStream<City> cities(EntityManager em) {
        return streams.streamAll(em, City.class);
    }
}
