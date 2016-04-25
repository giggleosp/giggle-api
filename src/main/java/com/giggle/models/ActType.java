package com.giggle.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by enda on 16/03/16.
 */
@Entity
@Table(name = "act_type_categories", indexes = { @Index(name = "IDX_ACT_TYPE_CATEGORYX1", columnList = "id, name")})
public class ActType {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany
    private List<Genre> genres;

    public ActType() {
    }

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
