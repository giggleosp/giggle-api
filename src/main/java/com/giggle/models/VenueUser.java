package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity(name = "users_venues")

public class VenueUser {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne @NotNull
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne @NotNull
    @JoinColumn(name = "venue_id")
    private Venue venue;
    @Column(name = "is_following")
    private boolean isFollowing;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;

    public VenueUser() {
    }

    public VenueUser(User user, Venue venue, boolean isFollowing) {
        this.user = user;
        this.venue = venue;
        this.isFollowing = isFollowing;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
