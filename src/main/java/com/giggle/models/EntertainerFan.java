package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "entertainers_fans")
public class EntertainerFan {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "entertainer_id")
    private Entertainer entertainer;
    @Column(name = "is_following")
    private boolean isFollowing;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;

    public EntertainerFan() {
    }

    public EntertainerFan(User user, Entertainer entertainer, boolean isFollowing) {
        this.user = user;
        this.entertainer = entertainer;
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

    public Entertainer getEntertainer() {
        return entertainer;
    }

    public void setEntertainer(Entertainer entertainer) {
        this.entertainer = entertainer;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
