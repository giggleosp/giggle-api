package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "venue_reviews")
public class VenueReview {

    @Id
    @GeneratedValue
    private long id;
    @Size(min = 1, max = 5)
    private int rating;
    @Column(length = 1000)
    private String description;
    @ManyToOne(optional = false)
    private Venue venue;
    @ManyToOne
    private User user;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public VenueReview() {
    }

    public VenueReview(int rating, Venue venue, User user) {
        this.rating = rating;
        this.venue = venue;
        this.user = user;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
