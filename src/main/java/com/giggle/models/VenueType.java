package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "venue_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class VenueType {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;

    public VenueType() {
    }

    public VenueType(String name) {
        this.name = name;
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
}
