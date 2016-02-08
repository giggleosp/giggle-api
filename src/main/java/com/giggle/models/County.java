package com.giggle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "counties", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class County {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @ManyToOne
    @JsonIgnore
    private Country country;

    public County() {
    }

    public County(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
