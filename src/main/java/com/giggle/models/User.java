package com.giggle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 50)
    private String name;
    @NotNull
    @Column(length = 50)
    private String username;
    @NotNull
    private String email;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "profile_is_public")
    private boolean profileIsPublic;
    @Column(columnDefinition = "char(60)")
    @NotNull @JsonIgnore
    private String password;
    @Column(name = "email_verified")
    private boolean emailVerified;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "county_id")
    private County county;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;
    private boolean enabled;

    public User() {
    }

    public User(String username, String email, String password, Timestamp dateCreated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isProfileIsPublic() {
        return profileIsPublic;
    }

    public void setProfileIsPublic(boolean profileIsPublic) {
        this.profileIsPublic = profileIsPublic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
