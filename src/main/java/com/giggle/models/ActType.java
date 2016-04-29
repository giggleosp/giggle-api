package com.giggle.models;

import javax.persistence.*;

/**
 * Created by enda on 16/03/16.
 */
@Entity
@Table(name = "act_types", indexes = { @Index(name = "IDX_ACT_TYPE_X1", columnList = "id, name")})
public class ActType {

    @Id
    @GeneratedValue
    private long id;
    private String name;

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
