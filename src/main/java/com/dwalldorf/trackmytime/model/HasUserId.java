package com.dwalldorf.trackmytime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasUserId extends HasId {

    String getUserId();

    @JsonIgnore
    String getObjectType();
}