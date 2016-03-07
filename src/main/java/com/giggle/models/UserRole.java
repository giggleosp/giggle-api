package com.giggle.models;

import lombok.Getter;
import lombok.Setter;
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
    @Getter @Setter private long id;
    @Column(unique = true)
    @Setter private String authority;
    @Getter @Setter private String displayName;

    @Override
    public String getAuthority() {
        return authority.toUpperCase();
    }

}
