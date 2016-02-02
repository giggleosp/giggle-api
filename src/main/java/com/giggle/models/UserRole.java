package com.giggle.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by enda on 17/12/15.
 */
@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {

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

    @Override
    public String getAuthority() {
        return name.toUpperCase();
    }

}
