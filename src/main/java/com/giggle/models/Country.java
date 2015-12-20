package com.giggle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Enda on 18/11/2015.
 */

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private long id;
    @Column(columnDefinition = "char(2)")
    private String iso;
    private String name;
    private String niceName;
    @Column(columnDefinition = "char(3)")
    private String iso3;
    @JsonIgnore
    private Integer numCode;
    @JsonIgnore
    private Integer phoneCode;

    public Country() {
    }

    public long getId() {
        return id;
    }


    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }
}
