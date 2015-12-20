package com.giggle.repositories;

import com.giggle.models.User;

import javax.inject.Singleton;

/**
 * Created by Enda on 22/11/2015.
 */

@Singleton
public interface UserRepository {
    boolean emailExists(String email);
    boolean usernameExists(String username);
    User insertUser(User user);
    User getUserWithId(long id);
    User getUserWithUsername(String username);
    User getUserWithEmail(String email);
}