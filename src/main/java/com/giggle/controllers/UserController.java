package com.giggle.controllers;

import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.exceptions.UserConflictException;
import com.giggle.models.User;
import com.giggle.models.UserRole;
import com.giggle.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by Enda on 23/11/2015.
 */
@RestController
@RequestMapping("/users")
public class UserController  {

    private final UserRepository repo;

    @Inject
    public UserController(final UserRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = POST, value = "/login")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody User login(@RequestParam String username) {
        if (username == null) {
            throw new BadRequestException();
        }

        User user = repo.getUserWithUsername(username);

        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }   


    @RequestMapping(method = POST, value = "/insert")
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

        // set role to default value -> user
        List<UserRole> roles = new ArrayList<>();
        roles.add(repo.getRoleByName("ROLE_USER"));
        user.setRoles(roles);

        // insert user into database and retrieve updated object
        return repo.insertUser(user);
    }

    @RequestMapping(value = "/id/{id}", method = GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody User getUserWithId(@PathVariable long id) {
        User user = repo.getUserWithId(id);
        if (user == null) throw new NotFoundException();
        return user;
    }

    @RequestMapping(value = "/username", method = POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody User getUserWithUsername(@RequestParam String username) {
        if (username == null) throw new BadRequestException();

        User user = repo.getUserWithUsername(username);

        if (user == null) throw new NotFoundException();
        return user;
    }

    @RequestMapping(value = "/emailexists", method = GET)
    public boolean emailExists(@RequestParam("email") String email) {
        if (email == null) throw new BadRequestException();
        return repo.emailExists(email);
    }

    @RequestMapping(value = "/usernameexists", method = GET)
    @PreAuthorize("permitAll")
    public boolean usernameExists(@RequestParam("username") String username) {
        if (username == null) throw new BadRequestException();
        return repo.usernameExists(username);
    }
    
    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

}
