package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id") @NotNull
    private County county;

    public City() {
    }

    public City(String name, County county) {
        this.name = name;
        this.county = county;
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

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
