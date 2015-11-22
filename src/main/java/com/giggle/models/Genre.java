package com.giggle.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToMany
    private List<EntertainmentType> entertainmentTypes;

    public Genre() {
    }

    public Genre(String name) {
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

    public List<EntertainmentType> getEntertainmentTypes() {
        return entertainmentTypes;
    }

    public void setEntertainmentTypes(List<EntertainmentType> entertainmentTypes) {
        this.entertainmentTypes = entertainmentTypes;
    }
}