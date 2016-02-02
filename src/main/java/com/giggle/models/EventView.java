package com.giggle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "event_views")
public class EventView {

    @Id
    @GeneratedValue
    private long id;
    private Timestamp time;
    private double latitude;
    private double longitude;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;

    public EventView() {

    }

    public EventView(User user, Event event) {
        this.user = user;
        this.event = event;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
