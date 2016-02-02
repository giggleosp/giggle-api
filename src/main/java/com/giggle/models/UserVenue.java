package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "users_venues")
public class UserVenue {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne @NotNull
    private User user;
    @ManyToOne @NotNull
    private Venue venue;
    private boolean isFollowing;
    private boolean isHidden;
    private boolean isAdmin;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public UserVenue() {
    }

    public UserVenue(User user, Venue venue) {
        this.user = user;
        this.venue = venue;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
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

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
