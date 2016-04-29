package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean isEnabled;
    @ManyToOne @NotNull
    private EventType type;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String backdropUri;
    private String imageUri;
    @ManyToOne
    private Act act;
    @ManyToOne
    private Venue venue;
    @ManyToOne
    private Event parentEvent;
    private Timestamp startDate;
    private Timestamp startTime;
    private Timestamp endDate;
    private Timestamp endTime;
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

    public Event(String name) {
        this.name = name;
        this.isEnabled = true;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getBackdropUri() {
        return backdropUri;
    }

    public void setBackdropUri(String backdropUri) {
        this.backdropUri = backdropUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public Event getParentEvent() {
        return parentEvent;
    }

    public void setParentEvent(Event parentEvent) {
        this.parentEvent = parentEvent;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
