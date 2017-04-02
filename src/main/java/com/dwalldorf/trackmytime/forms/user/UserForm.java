package com.dwalldorf.trackmytime.forms.user;

import com.dwalldorf.trackmytime.model.HasUserId;
import com.dwalldorf.trackmytime.model.User;
import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class UserForm implements HasUserId, Serializable {

    private String id;

    @Size(min = 3, max = 40, message = "length must be between 3 and 40 characters")
    private String username;

    @NotEmpty(message = "email is mandatory")
    @Email(message = "not a valid email")
    private String email;

    private String password;

    private DateTime registration;

    private DateTime firstLogin;

    private DateTime lastLogin;

    private Boolean confirmedEmail = false;

    private String defaultCustomerId;

    public static UserForm fromEntry(User user) {
        return new UserForm()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRegistration(user.getRegistration())
                .setFirstLogin(user.getFirstLogin())
                .setDefaultCustomerId(user.getDefaultCustomerId());
    }

    @Override
    public String getUserId() {
        return id;
    }

    @Override
    public String getObjectType() {
        return "UserForm";
    }

    public String getId() {
        return id;
    }

    public UserForm setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public DateTime getRegistration() {
        return registration;
    }

    public UserForm setRegistration(DateTime registration) {
        this.registration = registration;
        return this;
    }

    public DateTime getFirstLogin() {
        return firstLogin;
    }

    public UserForm setFirstLogin(DateTime firstLogin) {
        this.firstLogin = firstLogin;
        return this;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public UserForm setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public Boolean getConfirmedEmail() {
        return confirmedEmail;
    }

    public UserForm setConfirmedEmail(Boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
        return this;
    }

    public String getDefaultCustomerId() {
        return defaultCustomerId;
    }

    public UserForm setDefaultCustomerId(String defaultCustomerId) {
        this.defaultCustomerId = defaultCustomerId;
        return this;
    }
}