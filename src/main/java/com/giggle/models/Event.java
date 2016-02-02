package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean isEnabled; // is visible
    @ManyToOne @NotNull
    private EventType type;
    private String description;
    @ManyToOne
    private Act act;
    @ManyToOne
    private Venue venue;
    @OneToMany
    private List<Event> childEvents;
    private Date startDate;
    private Date endDate;
    private float price;
    @Max(150)
    private int minimumAge;
    private boolean actConfirmed;
    private boolean venueConfirmed;
    private Timestamp publishDate;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public Event() {
    }

    public Event(String name, Act act, Venue venue) {
        this.name = name;
        this.act = act;
        this.venue = venue;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Event> getChildEvents() {
        return childEvents;
    }

    public void setChildEvents(List<Event> childEvents) {
        this.childEvents = childEvents;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public boolean isActConfirmed() {
        return actConfirmed;
    }

    public void setActConfirmed(boolean actConfirmed) {
        this.actConfirmed = actConfirmed;
    }

    public boolean isVenueConfirmed() {
        return venueConfirmed;
    }

    public void setVenueConfirmed(boolean venueConfirmed) {
        this.venueConfirmed = venueConfirmed;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
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
