package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable {

    @Id
    private String id;

    @NotEmpty
    @Size(min = 5, max = 40)
    @Indexed(unique = true)
    private String username;

    @Email
    private String email;

    @NotEmpty
    private byte[] hashedPassword;

    @NotEmpty
    private byte[] salt;

    @NotEmpty
    private DateTime registration;

    private DateTime firstLogin;

    private DateTime lastLogin;

    private Boolean confirmedEmail = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public DateTime getRegistration() {
        return registration;
    }

    public void setRegistration(DateTime registration) {
        this.registration = registration;
    }

    public DateTime getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(DateTime firstLogin) {
        this.firstLogin = firstLogin;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getConfirmedEmail() {
        return confirmedEmail;
    }

    public void setConfirmedEmail(Boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
    }
}