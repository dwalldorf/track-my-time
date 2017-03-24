package com.dwalldorf.trackmytime.forms.user;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm implements Serializable {

    @NotEmpty
    @Size(min = 5, max = 40)
    private String username;

    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginForm setPassword(String password) {
        this.password = password;
        return this;
    }
}