package com.giggle.models;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "acts", indexes = { @Index(name = "IDX_ACTX1", columnList = "id, name")})
public class Act implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private String description;
    private boolean isEnabled;
    private boolean isVerified;
    private boolean isVisible;
    private String imageUri;
    private String backdropUri;
    @Max(5)
    private float rating;
    @ManyToMany
    private List<Genre> genres;
    @ManyToMany
    private List<ActType> types;
    @ManyToOne
    private ActCategory category;
    @ManyToOne
    private City city;
    @ManyToOne
    private County county;
    @ManyToOne
    private Country country;
    @NotNull
    @Getter private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public Act() {
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public Act(String name) {
        this.name = name;
        this.isEnabled = true;
        this.isVerified = false;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public void setDateCreated() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getBackdropUri() {
        return backdropUri;
    }

    public void setBackdropUri(String backdropUri) {
        this.backdropUri = backdropUri;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ActType> getTypes() {
        return types;
    }

    public void setTypes(List<ActType> types) {
        this.types = types;
    }

    public ActCategory getCategory() {
        return category;
    }

    public void setCategory(ActCategory category) {
        this.category = category;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
