package com.giggle.controllers;

import com.giggle.exceptions.NotFoundException;
import com.giggle.models.*;
import com.giggle.repositories.act.ActRepository;
import com.giggle.repositories.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by enda on 01/03/16.
 */
@RestController
@RequestMapping("/acts/")
public class ActController {

    private final ActRepository repo;
    private final UserRepository userRepository;

    @Inject
    public ActController(ActRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    @RequestMapping
    public @ResponseBody List<Act> getActs() {
        List<Act> acts = repo.getActs();

        if (acts == null)
            throw new NotFoundException();
        else
            return acts;

    }

    @RequestMapping(value = "managed_by", method = GET)
    @PreAuthorize("hasRole('ROLE_ACT')")
    public @ResponseBody List<Act> getActsManagedByUser(@RequestParam long id) {
        List<Act> acts = repo.getActsManagedByUser(id);

        if (acts == null) {
            throw new NotFoundException();
        } else {
            return acts;
        }
    }

    @RequestMapping(value = "{id}", method = GET)
    public @ResponseBody Act getActById(@PathVariable long id) {
        Act act = repo.getActById(id);

        if (act == null) {
            throw new NotFoundException();
        } else {
            return act;
        }
    }

    @RequestMapping(value = "categories", method = GET)
    public @ResponseBody List<ActCategory> getActCategories() {
        List<ActCategory> types = repo.actCategories();

        if (types == null) {
            throw new NotFoundException();
        } else {
            return types;
        }
    }

    @RequestMapping(value = "create", method = POST)
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Act createAct(@RequestPart Act act, @RequestPart User user) {
        if (user.getRoles() != null && user.getRoles().stream()
                .filter(r -> r.getAuthority()
                        .equals("ROLE_VENUE")) == null) {
            user = updateUserRoles(user);
        }

        act.setDateCreated();
        act.setEnabled(true);
        act.setVisible(true);
        act = repo.createAct(act);

        ActUser actUser = new ActUser(user, act);
        actUser.setAdmin(true);
        actUser.setHidden(false);
        actUser.setFollowing(false);

        repo.createActUserRelationship(actUser);

        return act;
    }

    @RequestMapping(value = "act/{actId}/user/{userId}", method = GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody ActUser getActUserRelationship(@PathVariable long actId, @PathVariable long userId) {

        ActUser actUser = repo.getActUserRelationship(actId, userId);

        if (actUser == null)
            throw new NotFoundException();
        else
            return actUser;
    }

    @RequestMapping(value = "genres")
    public @ResponseBody List<Genre> getGenres() {
        List<Genre> genres = repo.getGenres();

        if (genres == null)
                throw new NotFoundException();
        else
            return genres;
    }

    private User updateUserRoles(User user) {
        // set role to default value -> user
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRepository.getRoleByName("ROLE_VENUE"));
        user.setRoles(roles);
        return userRepository.updateUser(user);
    }
}
