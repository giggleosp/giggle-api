package com.giggle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Enda on 04/11/2015.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Getter private long id;
    @Column(length = 30)
    @Getter @Setter private String firstName;
    @Column(length = 30)
    @Getter @Setter private String lastName;
    @NotNull
    @Column(length = 30)
    @Getter @Setter private String username;
    @NotNull
    @Getter @Setter private String email;
    @Getter @Setter private String imageUri;
    @Column(columnDefinition = "char(60)")
    @NotNull @JsonIgnore
    @Getter @Setter private String password;
    @Getter @Setter private boolean emailVerified;
    @Getter @Setter private Date dateOfBirth;
    @ManyToOne
    @Getter @Setter private County county;
    @ManyToOne
    @Getter @Setter private City city;
    @ManyToOne
    @Getter @Setter private Country country;
    @Getter @Setter private Timestamp dateCreated;
    @Getter @Setter private Timestamp dateUpdated;
    @Getter @Setter private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @Getter @Setter private List<UserRole> roles;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = true;
        this.emailVerified = false;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

}
