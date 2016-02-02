package com.giggle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "venue_views")
public class VenueView {

    @Id
    @GeneratedValue
    private long id;
    private Timestamp time;
    private double latitude;
    private double longitude;
    @ManyToOne
    private User user;
    @ManyToOne
    private Venue venue;

    public VenueView() {
    }

    public VenueView(User user, Venue venue) {
        this.user = user;
        this.venue = venue;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
}
