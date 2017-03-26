package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer implements Serializable {

    @Id
    private String id;

    @Indexed
    private String userId;

    @Indexed
    @NotEmpty(message = "name must not be empty")
    private String name;

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Customer setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }
}