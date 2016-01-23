package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "entertainment_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class EventType {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;

    public EventType() {
    }

    public EventType(String name) {
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
