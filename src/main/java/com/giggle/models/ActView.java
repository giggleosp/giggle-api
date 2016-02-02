package com.giggle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "act_views")
public class ActView {

    @Id
    @GeneratedValue
    private long id;
    private Timestamp time;
    private double latitude;
    private double longitude;
    @ManyToOne
    private User user;
    @ManyToOne
    private Act act;

    public ActView() {
    }

    public ActView(Timestamp time, User user, Act act) {
        this.time = time;
        this.user = user;
        this.act = act;
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

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }
}
