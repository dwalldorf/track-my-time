package com.dwalldorf.trackmytime.exception;

import javax.validation.constraints.NotNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class InvalidInputException extends RuntimeException {

    private final String field;

    private final String rejectedValue;

    public InvalidInputException(String field, String message, String rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public ObjectError toFormError(@NotNull String objectName) {
        if (field != null) {
            return new FieldError(objectName, field, rejectedValue, false, null, null, getMessage());
        }
        return new ObjectError(objectName, getMessage());
    }
}