package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private boolean isEnabled;
    @ManyToOne @NotNull
    @Getter @Setter private EventType type;
    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String description;
    @Getter @Setter private String backdropUri;
    @Getter @Setter private String imageUri;
    @ManyToOne
    @Getter @Setter private Act act;
    @ManyToOne
    @Getter @Setter private Venue venue;
    @ManyToOne
    @Getter @Setter private Event parentEvent;
    @Getter @Setter private Timestamp startDate;
    @Getter @Setter private Timestamp startTime;
    @Getter @Setter private Timestamp endDate;
    @Getter @Setter private Timestamp endTime;
    @Getter @Setter private float price;
    @Max(150)
    @Getter @Setter private int minimumAge;
    @Getter @Setter private boolean actConfirmed;
    @Getter @Setter private boolean venueConfirmed;
    @Getter @Setter private Timestamp publishDate;
    @Getter @Setter private Timestamp dateCreated;
    @Getter @Setter private Timestamp dateUpdated;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
        this.isEnabled = true;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

}
