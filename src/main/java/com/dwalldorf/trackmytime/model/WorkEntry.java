package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "work_entries")
public class WorkEntry implements Serializable {

    @Id
    private String id;

    private String projectId;

    private String customerId;

    public String getId() {
        return id;
    }

    public WorkEntry setId(String id) {
        this.id = id;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public WorkEntry setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public WorkEntry setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }
}