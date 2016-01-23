package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "entertainer_reviews")
public class ActReview {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 1, max = 5)
    private int rating;
    @Column(length = 1000)
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "entertainer_id")
    private Act act;
    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewed_by_id")
    private User reviewedBy;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;

    public ActReview() {
    }

    public ActReview(int rating, Act act, User reviewedBy, Timestamp dateCreated) {
        this.rating = rating;
        this.act = act;
        this.reviewedBy = reviewedBy;
        this.dateCreated = dateCreated;
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

    public User getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
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
