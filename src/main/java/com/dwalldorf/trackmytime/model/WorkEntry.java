package com.dwalldorf.trackmytime.model;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "work_entries")
public class WorkEntry implements HasUserId, Serializable {

    @Id
    private String id;

    private String userId;

    private String projectId;

    private String customerId;

    private DateTime start;

    private DateTime stop;

    private String comment;

    private WorkEntrySource source;

    public String getId() {
        return id;
    }

    public WorkEntry setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public WorkEntry setUserId(String userId) {
        this.userId = userId;
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

    public DateTime getStart() {
        return start;
    }

    public WorkEntry setStart(DateTime start) {
        this.start = start;
        return this;
    }

    public DateTime getStop() {
        return stop;
    }

    public WorkEntry setStop(DateTime stop) {
        this.stop = stop;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public WorkEntry setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public WorkEntrySource getSource() {
        return source;
    }

    public WorkEntry setSource(WorkEntrySource source) {
        this.source = source;
        return this;
    }
}