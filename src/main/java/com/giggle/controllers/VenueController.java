package com.giggle.controllers;

import com.giggle.exceptions.NotFoundException;
import com.giggle.models.*;
import com.giggle.repositories.user.UserRepository;
import com.giggle.repositories.venue.VenueRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by enda on 12/02/16.
 */
@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueRepository repo;
    private final UserRepository userRepository;

    @Inject
    public VenueController(VenueRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    @RequestMapping
    public @ResponseBody List<Venue> getVenues() {
        List<Venue> acts = repo.getVenues();

        if (acts == null)
            throw new NotFoundException();
        else
            return acts;

    }

    @RequestMapping(value = "/managed_by", method = GET)
    @PreAuthorize("hasRole('ROLE_VENUE')")
    public @ResponseBody List<Venue> getVenuesManagedByUser(@RequestParam long id) {
        List<Venue> venues = repo.getVenuesManagedByUser(id);

        if (venues == null) {
            throw new NotFoundException();
        } else {
            return venues;
        }
    }

    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody Venue getVenueById(@PathVariable long id) {
        Venue venue = repo.getVenueById(id);

        if (venue == null) {
            throw new NotFoundException();
        } else {
            return venue;
        }
    }

    @RequestMapping(value = "/types", method = GET)
    public @ResponseBody List<VenueType> getVenueTypes() {
        List<VenueType> types = repo.venueTypes();

        if (types == null) {
            throw new NotFoundException();
        } else {
            return types;
        }
    }

    @RequestMapping(value = "/create", method = POST)
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Venue createVenue(@RequestPart Venue venue, @RequestPart User user) throws IOException {
        venue = repo.createVenue(venue);

        if (user.getRoles() != null && user.getRoles().stream()
                .filter(r -> r.getAuthority()
                        .equals("ROLE_VENUE")) == null) {
            user = updateUserRoles(user);
        }
        UserVenue userVenue = new UserVenue(user, venue);
        userVenue.setAdmin(true);
        userVenue.setHidden(false);
        userVenue.setFollowing(false);

        repo.createUserVenueRelationship(userVenue);

        return venue;

    }

    private User updateUserRoles(User user) {
        // set role to default value -> user
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRepository.getRoleByName("ROLE_VENUE"));
        user.setRoles(roles);
        return userRepository.updateUser(user);
    }

    @RequestMapping(value = "/venue/{venueId}/user/{userId}", method = GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody UserVenue getUserVenueRelationship(@PathVariable long venueId,
                                                            @PathVariable long userId) {
        UserVenue userVenue = repo.getUserVenueRelationship(venueId, userId);

        if (userVenue == null)
            throw new NotFoundException();
        else
            return userVenue;
    }

    @RequestMapping(value = "/update", method = PUT)
    @PreAuthorize("hasRole('ROLE_VENUE')")
    public @ResponseBody Venue updateVenue(@RequestBody Venue venue) {
        if (getVenueById(venue.getId()) == null) {
            throw new NotFoundException();
        }

        venue.setDateUpdated();
        return repo.updateVenue(venue);
    }

}
