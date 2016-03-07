package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @NotNull
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private boolean isEnabled;
    @Getter @Setter private boolean isVerified;
    @Getter @Setter private boolean isVisible;
    @Getter @Setter private String imageUri;
    @Max(5)
    @Getter @Setter private float rating;
    @OneToMany
    @Getter @Setter private List<Genre> genres;
    @ManyToOne
    @Getter @Setter private ActType type;
    @ManyToOne
    @Getter @Setter private City city;
    @ManyToOne
    @Getter @Setter private County county;
    @ManyToOne
    @Getter @Setter private Country country;
    @NotNull
    @Getter @Setter private Timestamp dateCreated;
    @Getter @Setter private Timestamp dateUpdated;

    public Act() {
    }

    public Act(String name) {
        this.name = name;
        this.isEnabled = true;
        this.isVerified = false;
    }

}
