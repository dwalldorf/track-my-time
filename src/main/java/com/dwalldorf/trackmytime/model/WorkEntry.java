package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "work_entries")
public class WorkEntry implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}