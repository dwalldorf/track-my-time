package com.dwalldorf.trackmytime.forms.user;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class UserForm implements Serializable {

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

}