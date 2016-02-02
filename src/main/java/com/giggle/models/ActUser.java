package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "acts_users")
public class ActUser {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne @NotNull
    private User user;
    @ManyToOne @NotNull
    private Act act;
    private boolean isFollowing;
    private boolean isHidden;
    private boolean isAdmin;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public ActUser() {
    }

    public ActUser(User user, Act act, boolean isFollowing) {
        this.user = user;
        this.act = act;
        this.isFollowing = isFollowing;
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

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
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
