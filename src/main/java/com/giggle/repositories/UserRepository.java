package com.giggle.repositories;

import com.giggle.JinqSource;
import com.giggle.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Enda on 22/11/2015.
 */

public interface UserRepository {
    @PreAuthorize("hasRole('ROLE_USER')")
    String requiresUserRole();
    List<User> users();
    boolean emailExists(String email);
    boolean usernameExists(String username);
    void insertUser(User user);
}

@Repository
class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public String requiresUserRole() {
        return "You have ROLE_USER";
    }

    @Override
    public List<User> users() {
        return source.users(em)
                .toList();
    }

    @Override
    public boolean emailExists(String email) {
        List<User> users = source.users(em)
                .where(u -> u.getEmail()
                        .toLowerCase()
                        .equals(email.toLowerCase()))
                .toList();
        return users.size() > 0;
    }

    @Override
    public boolean usernameExists(String username) {
        List<User> users = source.users(em)
                .where(u -> u.getUsername()
                        .equals(username))
                .toList();
        return users.size() > 0;
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        em.persist(user);
    }

}
