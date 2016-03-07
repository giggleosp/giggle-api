package com.giggle.repositories.venue;

import com.giggle.models.UserVenue;
import com.giggle.models.Venue;
import com.giggle.models.VenueType;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 12/02/16.
 */
@Singleton
public interface VenueRepository {
    List<Venue> getVenuesManagedByUser(long id);
    Venue getVenueById(long id);
    List<VenueType> venueTypes();
    Venue createVenue(Venue venue);
    void createUserVenueRelationship(UserVenue userVenue);
    UserVenue getUserVenueRelationship(long venueId, long userId);
}
