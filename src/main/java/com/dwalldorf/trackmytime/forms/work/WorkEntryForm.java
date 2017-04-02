package com.dwalldorf.trackmytime.forms.work;

import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.model.WorkEntrySource;
import java.io.Serializable;
import java.util.Date;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class WorkEntryForm implements Serializable {

    private final static org.joda.time.format.DateTimeFormatter DATE_TIME_FORMATTER = org.joda.time.format.DateTimeFormat.forPattern("YYYY-MM-dd hh:mm");

    private String id;

    private String userId;

    private String projectId;

    private String customerId;

    @DateTimeFormat(pattern = "YYYY-MM-dd hh:mm")
    private Date start;

    @DateTimeFormat(pattern = "YYYY-MM-dd hh:mm")
    private Date stop;

    private String comment;

    private WorkEntrySource source;

    public static WorkEntry toWorkEntry(WorkEntryForm form) {
        WorkEntry workEntry = new WorkEntry()
                .setId(form.getId())
                .setUserId(form.getUserId())
                .setCustomerId(form.getCustomerId())
                .setProjectId(form.getProjectId())
                .setComment(form.getComment());

        if (form.getStart() == null) {
            workEntry.setStart(null);
        } else {
            workEntry.setStart(new DateTime(form.getStart()));
        }

        if (form.getStop() == null) {
            workEntry.setStop(null);
        } else {
            workEntry.setStop(new DateTime(form.getStop()));
        }

        return workEntry;
    }

    public WorkEntry toWorkEntry() {
        return WorkEntryForm.toWorkEntry(this);
    }

    public static WorkEntryForm fromWorkEntry(WorkEntry workEntry) {
        WorkEntryForm form = new WorkEntryForm()
                .setId(workEntry.getId())
                .setUserId(workEntry.getUserId())
                .setCustomerId(workEntry.getCustomerId())
                .setProjectId(workEntry.getProjectId())
                .setComment(workEntry.getComment());

        if (workEntry.getStart() == null) {
            form.setStart(null);
        } else {
            form.setStart(workEntry.getStart().toDate());
        }
        if (workEntry.getStop() == null) {
            form.setStop(null);
        } else {
            form.setStop(workEntry.getStop().toDate());
        }

        return form;
    }

    public String getId() {
        return id;
    }

    public WorkEntryForm setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public WorkEntryForm setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public WorkEntryForm setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public WorkEntryForm setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public WorkEntryForm setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getStop() {
        return stop;
    }

    public WorkEntryForm setStop(Date stop) {
        this.stop = stop;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public WorkEntryForm setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public WorkEntrySource getSource() {
        return source;
    }

    public WorkEntryForm setSource(WorkEntrySource source) {
        this.source = source;
        return this;
    }

    public String getStartString() {
        if (start == null) {
            return null;
        }
        return DATE_TIME_FORMATTER.print(new DateTime(start));
    }

    public String getStopString() {
        if (stop == null) {
            return null;
        }
        return DATE_TIME_FORMATTER.print(new DateTime(stop));
    }

    public boolean missingStop() {
        return start != null && stop == null;
    }
}