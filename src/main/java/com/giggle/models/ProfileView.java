package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */

@Entity
@Table(name = "profile_views")
public class ProfileView {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "viewed_by_id")
    private User viewedBy;
    @NotNull
    @Column(name = "view_time")
    private Timestamp viewTime;
    private double latitude;
    private double longitude;

    public ProfileView() { }

    public ProfileView(User user, User viewedBy, Timestamp viewTime) {
        this.user = user;
        this.viewedBy = viewedBy;
        this.viewTime = viewTime;
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

    public User getViewedBy() {
        return viewedBy;
    }

    public void setViewedBy(User viewedBy) {
        this.viewedBy = viewedBy;
    }

    public Timestamp getViewTime() {
        return viewTime;
    }

    public void setViewTime(Timestamp viewTime) {
        this.viewTime = viewTime;
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
}
