package com.giggle.repositories.venue;

import com.giggle.JinqSource;
import com.giggle.models.UserVenue;
import com.giggle.models.Venue;
import com.giggle.models.VenueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by enda on 12/02/16.
 */
@Repository
public class VenueRepositoryImpl implements VenueRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public List<Venue> getVenuesManagedByUser(long id) {
        List<Venue> venues = source.userVenues(em)
                .filter(UserVenue::isAdmin)
                .filter(uv -> uv.getUser().getId() == id)
                .map(UserVenue::getVenue)
                .collect(Collectors.toList());

        return venues.isEmpty() ? null : venues;

    }

    @Override
    public Venue getVenueById(long id) {
        List<Venue> venues = source.venues(em)
                .where(v -> v.getId() == id)
                .toList();
        return venues.isEmpty() ? null : venues.get(0);
    }

    @Override
    public List<VenueType> venueTypes() {
        List<VenueType> types = source.venueTypes(em)
                .toList();
        return types.isEmpty() ? null : types;
    }

    @Override
    public Venue createVenue(Venue venue) {
        em.persist(venue);
        return venue;
    }

    @Override
    public void createUserVenueRelationship(UserVenue userVenue) {
        em.persist(userVenue);
    }

    @Override
    public UserVenue getUserVenueRelationship(long venueId, long userId) {
        List<UserVenue> userVenueList = source.userVenues(em)
                .where(v -> v.getVenue().getId() == venueId)
                .where(u -> u.getUser().getId() == userId)
                .toList();

        return userVenueList.isEmpty() ? null : userVenueList.get(0);
    }
}
