package com.dwalldorf.trackmytime.config;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User implements Serializable {

    /**
     * User id
     */
    private String id;

    public <T> SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
        super(
                username,
                password,
                true,
                true,
                true,
                true,
                authorities
        );
        this.id = id;
    }

    public String getId() {
        return id;
    }
}