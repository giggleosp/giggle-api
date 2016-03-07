package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @NotNull
    @Getter @Setter private String name;

    public VenueType() {
    }
}
