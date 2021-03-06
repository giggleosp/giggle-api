package com.giggle.controllers;

import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.ConflictException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.models.User;
import com.giggle.models.UserRole;
import com.giggle.repositories.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
    public @ResponseBody User login(@RequestParam String username) {
        User user = repo.getUserWithUsername(username);

        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }   

    @RequestMapping(method = POST, value = "/signup")
    public @ResponseBody User insertUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {

        // missing parameter(s)
        if (username == null || email == null || password == null)
            throw new BadRequestException();

        // check if username already exists in database
        if (usernameExists(username))
            throw new ConflictException();

        // check if email already exists in database
        if (emailExists(email))
            throw new ConflictException();

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

    @RequestMapping(value = "/update", method = PUT)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody User updateUser(@RequestBody User newUser) {
        User user = repo.getUserWithId(newUser.getId());

        if (user == null) throw new NotFoundException();

        //region COMPARE PROPERTIES
        // check all properties for change and update
        boolean changed = false; // watch for change
        if (newUser.getFirstName() != null && !Objects.equals(user.getFirstName(), newUser.getFirstName())) {
            user.setFirstName(newUser.getFirstName());
            changed = true;
        }
        if (newUser.getLastName() != null && !Objects.equals(user.getLastName(), newUser.getLastName())) {
            user.setLastName(newUser.getLastName());
            changed = true;
        }
        if (user.getDateOfBirth() != newUser.getDateOfBirth()) {
            user.setDateOfBirth(newUser.getDateOfBirth());
            changed = true;
        }
        if (newUser.getCountry() != null && user.getCountry() != newUser.getCountry()) {
            user.setCountry(newUser.getCountry());
            changed = true;
        }
        if (newUser.getCounty() != null && user.getCounty() != newUser.getCounty()) {
            user.setCounty(newUser.getCounty());
            changed = true;
        }
        if (newUser.getCity() != null && user.getCity() != newUser.getCity()) {
            user.setCity(newUser.getCity());
            changed = true;
        }
        //endregion

        // perform update and return response;
        if (changed) {
            user.setDateUpdated(timeStamp());
            return repo.updateUser(user);
        } else {
            return user;
        }

    }

    private Timestamp timeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    @RequestMapping(value = "/username", method = POST)
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
    public boolean usernameExists(@RequestParam("username") String username) {
        if (username == null) throw new BadRequestException();
        return repo.usernameExists(username);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

}
