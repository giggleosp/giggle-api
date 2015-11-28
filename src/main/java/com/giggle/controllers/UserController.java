package com.giggle.controllers;

import com.giggle.models.User;
import com.giggle.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Enda on 23/11/2015.
 */
@RestController
@RequestMapping("/users")
public class UserController  {

    private final UserRepository repo;

    @Inject
    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @RequestMapping("/")
    public List<User> allUsers() {
        List<User> users = repo.users();
        return users.size() > 0 ? users : null;
    }

    @RequestMapping(value = "/emailexists/{email}", method = RequestMethod.GET)
    public boolean emailExists(@PathVariable String email) {
        return repo.emailExists(email);
    }

    @RequestMapping(value = "/usernameexists/{username}", method = RequestMethod.GET)
    public boolean usernameExists(@PathVariable String username) {
        return repo.usernameExists(username);
    }

    @RequestMapping(value = "/new")
    public ResponseEntity<User> insertUser() {
        User user = new User("s00126107", "s00126107@mail.itsligo.ie",
                "password", new Timestamp(System.currentTimeMillis()));

        if (emailExists(user.getEmail()) && usernameExists(user.getUsername())) {
            // hash password
            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            // insert User object
            repo.insertUser(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

}
