package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "entertainers")
public class Act {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne @NotNull
    @JoinColumn(name = "user_id")
    private User user;
    @NotNull @ManyToMany
    private List<Genre> genres;
    private String description;
    @NotNull
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @Column(name = "date_updated")
    private Timestamp dateUpdated;




}
