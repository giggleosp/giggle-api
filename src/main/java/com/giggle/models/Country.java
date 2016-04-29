package com.giggle.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Enda on 18/11/2015.
 */

@Entity
@Table(name = "countries", indexes = { @Index(name = "IDX_COUNTRYX1", columnList = "id, name")})
public class Country implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(columnDefinition = "char(2)")
    private String iso;
    private String name;
    private String niceName;
    @Column(columnDefinition = "char(3)")
    private String iso3;
    private Integer numCode;
    private Integer phoneCode;
    private String currency;
    private String iso4217;

    public Country() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIso4217() {
        return iso4217;
    }

    public void setIso4217(String iso4217) {
        this.iso4217 = iso4217;
    }
}
