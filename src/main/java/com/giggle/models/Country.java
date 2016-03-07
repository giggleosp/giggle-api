package com.giggle.models;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private long id;
    @Column(columnDefinition = "char(2)")
    @Getter @Setter private String iso;
    @Getter @Setter private String name;
    @Getter @Setter private String niceName;
    @Column(columnDefinition = "char(3)")
    @Getter @Setter private String iso3;
    @Getter @Setter private Integer numCode;
    @Getter @Setter private Integer phoneCode;
    @Getter @Setter private String currency;
    @Getter @Setter private String iso4217;

    public Country() {
    }

}
