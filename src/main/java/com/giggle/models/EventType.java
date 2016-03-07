package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @NotNull
    @Getter @Setter private String name;

    public EventType() {
    }
}
