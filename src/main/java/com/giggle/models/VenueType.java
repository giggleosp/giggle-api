package com.giggle.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "venue_types")
public class VenueType implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;

    public VenueType() {
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
}
