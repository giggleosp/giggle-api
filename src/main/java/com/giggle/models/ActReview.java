package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "act_reviews")
public class ActReview {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 1, max = 5)
    private int rating;
    private String description;
    @ManyToOne
    private Act act;
    @ManyToOne
    private User user;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public ActReview() {
    }

    public ActReview(int rating, Act act, User user) {
        this.rating = rating;
        this.act = act;
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

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
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
