package com.giggle.models;

import javax.persistence.*;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "genres", indexes = { @Index(name = "IDX_GENREX1", columnList = "id, name")})
public class Genre {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
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
