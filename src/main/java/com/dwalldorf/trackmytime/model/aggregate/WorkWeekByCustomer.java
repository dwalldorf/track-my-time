package com.dwalldorf.trackmytime.model.aggregate;

import java.io.Serializable;
import org.joda.time.DateTime;

public class WorkWeekByCustomer implements Serializable {

    private String id;

    private String userId;

    private String customerId;

    private DateTime week;

    private Long duration;

    public String getId() {
        return id;
    }

    public WorkWeekByCustomer setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public WorkWeekByCustomer setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public WorkWeekByCustomer setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public DateTime getWeek() {
        return week;
    }

    public WorkWeekByCustomer setWeek(DateTime week) {
        this.week = week;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public WorkWeekByCustomer setDuration(Long duration) {
        this.duration = duration;
        return this;
    }
}