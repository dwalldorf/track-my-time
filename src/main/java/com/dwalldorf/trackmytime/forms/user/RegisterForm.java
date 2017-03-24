package com.dwalldorf.trackmytime.forms.user;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm implements Serializable {

    @NotEmpty
    @Size(min = 5, max = 40)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public RegisterForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterForm setPassword(String password) {
        this.password = password;
        return this;
    }
}