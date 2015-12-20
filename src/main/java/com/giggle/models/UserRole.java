package com.giggle.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by enda on 17/12/15.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;

    public UserRole() {
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
}
