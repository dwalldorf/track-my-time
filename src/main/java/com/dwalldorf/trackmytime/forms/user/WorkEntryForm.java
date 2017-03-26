package com.dwalldorf.trackmytime.forms.user;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class WorkEntryForm implements Serializable {

    private String id;

    @NotEmpty(message = "customer must not be empty")
    private String customer;

    private String project;

    private String comment;

    private DateTime start;

    private DateTime stop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getStop() {
        return stop;
    }

    public void setStop(DateTime stop) {
        this.stop = stop;
    }
}