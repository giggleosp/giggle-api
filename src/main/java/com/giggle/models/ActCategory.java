package com.giggle.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "act_types", indexes = { @Index(name = "IDX_ACT_TYPEX1", columnList = "id, name")})
public class ActCategory {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    private List<ActType> categories;

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

    public List<ActType> getCategories() {
        return categories;
    }

    public void setCategories(List<ActType> categories) {
        this.categories = categories;
    }
}
