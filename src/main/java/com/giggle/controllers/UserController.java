package com.giggle.controllers;

import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.UnauthorisedRequestException;
import com.giggle.exceptions.UserConflictException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.models.User;
import com.giggle.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Timestamp;


/**
 * Created by Enda on 23/11/2015.
 */
@RestController
@RequestMapping("/user")
public class UserController  {

    private final UserRepository repo;

    @Inject
    public UserController(final UserRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public @ResponseBody User login(@RequestParam String username, @RequestParam String password) {

        if (username == null || password == null)
            throw new BadRequestException();

        User user = null;
        // try and login with username field first
        if (usernameExists(username)) {
            user = repo.getUserWithUsername(username);
        } else if (emailExists(username)) {
            user = repo.getUserWithEmail(username);
        } else {
            throw new NotFoundException();
        }

        if (user != null && !checkPassword(password, user.getPassword())) {
            throw new UnauthorisedRequestException(); // incorrect password for this user
        } else {
            return user; // success
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public @ResponseBody User insertUser(@RequestParam String username,
                                         @RequestParam String email,
                                         @RequestParam String password) {

        // missing parameter(s)
        if (username == null || email == null || password == null)
            throw new BadRequestException();

        // check if username already exists in database
        if (usernameExists(username))
            throw new UserConflictException("username", username);

        // check if email already exists in database
        if (emailExists(email))
            throw new UserConflictException("email", email);

        // create a hashed password
        String hashedPassword = hashPassword(password);

        // create user
        User user = new User(username, email, hashedPassword);

        // insert user into database and retrieve updated object
        return repo.insertUser(user);
    }

    private Timestamp timeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUserWithId(@PathVariable long id) {
        User user = repo.getUserWithId(id);
        if (user == null) throw new NotFoundException();
        return user;
    }


    @RequestMapping(value = "/emailexists", method = RequestMethod.GET)
    public boolean emailExists(@RequestParam("email") String email) {
        if (email == null) throw new BadRequestException();
        return repo.emailExists(email);
    }

    @RequestMapping(value = "/usernameexists", method = RequestMethod.GET)
    public boolean usernameExists(@RequestParam("username") String username) {
        if (username == null) throw new BadRequestException();
        return repo.usernameExists(username);
    }
    
    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    private boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
