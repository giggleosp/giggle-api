package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "venues")
public class Venue implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    private String shortDescription;
    private String zipCode;
    @ManyToMany
    private List<VenueType> types;
    private int capacity;
    @Max(5)
    private float rating;
    @Max(150)
    private int minimumAge;
    private String email;
    private String phone;
    private String twitter;
    private String facebook;
    private String googlePlus;
    private String website;
    private String imageUri;
    private String backdropUri;
    private boolean isVisible;
    private boolean isEnabled;
    @ManyToOne
    private Country country;
    @ManyToOne
    private County county;
    @ManyToOne
    private City city;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public Venue() {
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        setDefaults();
    }

    private void setDefaults() {
        this.dateCreated = new Timestamp(System.currentTimeMillis());
        this.isEnabled = true;
    }

    public Venue(String name) {
        this.name = name;
        setDefaults();
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<VenueType> getTypes() {
        return types;
    }

    public void setTypes(List<VenueType> types) {
        this.types = types;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public void setDateUpdated() {
        this.dateUpdated = new Timestamp(System.currentTimeMillis());
    }
}
