package com.giggle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "venue_pictures")
public class VenuePictures {

    @Id
    @GeneratedValue
    private long id;
    private String imageUri;
    private Timestamp dateUploaded;
    @ManyToOne
    private Venue venue;

    public VenuePictures() {
    }

    public VenuePictures(String imageUri, Venue venue) {
        this.imageUri = imageUri;
        this.venue = venue;
    }

    public long getId() {
        return id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Timestamp getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Timestamp dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
