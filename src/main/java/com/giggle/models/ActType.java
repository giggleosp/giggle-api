package com.giggle.models;

import javax.persistence.*;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "act_types", indexes = { @Index(name = "IDX_ACT_TYPEX1", columnList = "id, name")})
public class ActType {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public ActType() {
    }

    public ActType(String name) {
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
