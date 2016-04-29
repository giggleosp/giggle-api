package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "event_types", indexes = { @Index(name = "IDX_ACTX1", columnList = "id, name")})
public class EventType implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;

    public EventType() {
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
