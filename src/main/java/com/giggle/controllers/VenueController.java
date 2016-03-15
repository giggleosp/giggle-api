package com.giggle.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.models.User;
import com.giggle.models.UserVenue;
import com.giggle.models.Venue;
import com.giggle.models.VenueType;
import com.giggle.repositories.venue.VenueRepository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by enda on 12/02/16.
 */
@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueRepository repo;

    @Inject public VenueController(VenueRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/managed_by", method = GET)
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

    @RequestMapping(value = "/new", method = POST)
    @Transactional
    public @ResponseBody Venue createVenue(@RequestBody String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        if (json.get("venue") == null || json.get("user") == null) {
            throw new BadRequestException();
        }

        Venue venue = mapper.convertValue(json.get("venue"), Venue.class);
        User user = mapper.convertValue(json.get("user"), User.class);

        venue = repo.createVenue(venue);

        UserVenue userVenue = new UserVenue(user, venue);
        userVenue.setAdmin(true);
        userVenue.setHidden(false);
        userVenue.setFollowing(false);

        repo.createUserVenueRelationship(userVenue);

        return venue;

    }

    @RequestMapping(value = "/venue/{venueId}/user/{userId}", method = GET)
    public @ResponseBody UserVenue getUserVenueRelationship(@PathVariable long venueId,
                                                            @PathVariable long userId) {

        UserVenue userVenue = repo.getUserVenueRelationship(venueId, userId);

        if (userVenue == null)
            throw new NotFoundException();
        else
            return userVenue;
    }

}
