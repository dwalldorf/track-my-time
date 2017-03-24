package com.dwalldorf.trackmytime.forms.user;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm implements Serializable {

    @Size(min = 3, max = 40, message = "length must be between 3 and 40 characters")
    private String username;

    @NotEmpty(message = "email is mandatory")
    @Email(message = "not a valid email")
    private String email;

    @Size(min = 6, message = "please use at least 6 characters")
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