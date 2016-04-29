package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "counties", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class County implements Serializable {

    @Id @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @ManyToOne
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
