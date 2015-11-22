package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Enda on 18/11/2015.
 */

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @Column(name = "iso_code")
    @NotNull
    private String isoCode;

    public Country() {
    }

    public Country(String name, String isoCode) {
        this.name = name;
        this.isoCode = isoCode;
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

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
