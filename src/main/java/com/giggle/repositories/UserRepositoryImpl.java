package com.giggle.repositories;

import com.giggle.JinqSource;
import com.giggle.models.User;
import com.giggle.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by enda on 20/12/15.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

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
    public User insertUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User getUserWithId(long id) {
        List<User> users = source.users(em)
                .where(u -> u.getId() == id)
                .toList();
        return users.size() < 1 ? null : users.get(0);
    }

    @Override
    public User getUserWithUsername(String username) {
        List<User> users = source.users(em)
                .where(u -> u.getUsername()
                        .toLowerCase()
                        .equals(username.toLowerCase()))
                .toList();

        return users.size() < 1 ? null : users.get(0);
    }

    @Override
    public UserRole getRoleByName(String role) {
        List<UserRole> roles = source.roles(em)
                .where(r -> r.getName().toUpperCase()
                        .equals(role.toUpperCase()))
                .toList();

        return roles.size() < 1 ? null : roles.get(0);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return em.merge(user);
    }

}
