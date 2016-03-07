package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "venues")
public class Venue implements Serializable {

    @Id
    @GeneratedValue
    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private String addressLine1;
    @Getter @Setter private String addressLine2;
    @Getter @Setter private String addressLine3;
    @Getter @Setter private String longDescription;
    @Getter @Setter private String shortDescription;
    @Getter @Setter private String zipCode;
    @ManyToOne
    @Getter @Setter private VenueType type;
    @Getter @Setter private int capacity;
    @Max(5)
    @Getter @Setter private float rating;
    @Max(150)
    @Getter @Setter private int minimumAge;
    @Getter @Setter private String email;
    @Getter @Setter private String phone;
    @Getter @Setter private String twitter;
    @Getter @Setter private String facebook;
    @Getter @Setter private String googlePlus;
    @Getter @Setter private String website;
    @Getter @Setter private String imageUri;
    @Getter @Setter private String backdropUri;
    @Getter @Setter private boolean isVisible;
    @Getter @Setter private boolean isEnabled;
    @ManyToOne
    @Getter @Setter private Country country;
    @ManyToOne
    @Getter @Setter private County county;
    @ManyToOne
    @Getter @Setter private City city;
    @Getter @Setter private Timestamp dateCreated;
    @Getter @Setter private Timestamp dateUpdated;

    public Venue() { }

    public Venue(String name) {
        this.name = name;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.isEnabled = true;
    }

}
