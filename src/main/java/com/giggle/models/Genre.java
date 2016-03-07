package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @Column(unique = true, nullable = false)
    @Getter @Setter private String name;
    @ManyToMany
    @Getter @Setter private List<EventType> eventTypes;

    public Genre() {
    }

}
