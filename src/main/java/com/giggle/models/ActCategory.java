package com.giggle.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "act_categories", indexes = { @Index(name = "IDX_ACT_CATEGORIESX1", columnList = "id, name")})
public class ActCategory {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    private List<ActType> types;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Genre> genres;

    public ActCategory() {
    }

    public ActCategory(String name) {
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

    public void setId(long id) {
        this.id = id;
    }

    public List<ActType> getTypes() {
        return types;
    }

    public void setTypes(List<ActType> types) {
        this.types = types;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
