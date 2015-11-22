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
    @NotNull
    private String name;
    @ManyToOne @NotNull
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    @Column(name = "is_visible")
    private boolean isVisible;
    @ManyToOne @NotNull
    @JoinColumn(name = "type_id")
    private EntertainmentType type;
    @ManyToOne @NotNull
    @JoinColumn(name = "entertainer_id")
    private Entertainer entertainer;
    @ManyToOne @NotNull
    @JoinColumn(name = "venue_id")
    private Venue venue;
    @OneToMany
    private List<EntertainmentType> entertainmentTypes;
    @ManyToOne
    @JoinColumn(name = "parent_event_id")
    private Event parentEvent;
    @NotNull
    @Column(name = "start_date")
    private Date startDate;
    @NotNull
    @Column(name = "end_date")
    private Date endDate;
    private float price;
    @Max(100)
    @Column(name = "minimum_age")
    private int minimumAge;
    @Column(name = "confirmed_by_entertainer")
    private boolean confirmedByEntertainer;
    @Column(name = "confirmed_by_venue")
    private boolean confirmedByVenue;
    @Column(name = "publish_date")
    private Timestamp publishDate;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;

    public Event() {
    }

    public Event(String name, Entertainer entertainer, Venue venue) {
        this.name = name;
        this.entertainer = entertainer;
        this.venue = venue;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }

    public Entertainer getEntertainer() {
        return entertainer;
    }

    public void setEntertainer(Entertainer entertainer) {
        this.entertainer = entertainer;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<EntertainmentType> getEntertainmentTypes() {
        return entertainmentTypes;
    }

    public void setEntertainmentTypes(List<EntertainmentType> entertainmentTypes) {
        this.entertainmentTypes = entertainmentTypes;
    }

    public Event getParentEvent() {
        return parentEvent;
    }

    public void setParentEvent(Event parentEvent) {
        this.parentEvent = parentEvent;
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

    public boolean isConfirmedByEntertainer() {
        return confirmedByEntertainer;
    }

    public void setConfirmedByEntertainer(boolean confirmedByEntertainer) {
        this.confirmedByEntertainer = confirmedByEntertainer;
    }

    public boolean isConfirmedByVenue() {
        return confirmedByVenue;
    }

    public void setConfirmedByVenue(boolean confirmedByVenue) {
        this.confirmedByVenue = confirmedByVenue;
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
