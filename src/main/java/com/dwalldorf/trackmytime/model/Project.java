package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public Project setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }
}