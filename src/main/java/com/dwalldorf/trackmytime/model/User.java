package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    @NotEmpty
    @Size(min = 3, max = 40)
    @Indexed(unique = true)
    private String username;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private DateTime registration;

    private DateTime firstLogin;

    private DateTime lastLogin;

    private Boolean confirmedEmail = false;

    private String defaultCustomerId;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public DateTime getRegistration() {
        return registration;
    }

    public User setRegistration(DateTime registration) {
        this.registration = registration;
        return this;
    }

    public DateTime getFirstLogin() {
        return firstLogin;
    }

    public User setFirstLogin(DateTime firstLogin) {
        this.firstLogin = firstLogin;
        return this;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public User setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public Boolean getConfirmedEmail() {
        return confirmedEmail;
    }

    public User setConfirmedEmail(Boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
        return this;
    }

    public String getDefaultCustomerId() {
        return defaultCustomerId;
    }

    public User setDefaultCustomerId(String defaultCustomerId) {
        this.defaultCustomerId = defaultCustomerId;
        return this;
    }
}