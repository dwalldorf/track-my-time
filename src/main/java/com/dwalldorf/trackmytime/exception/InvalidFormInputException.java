package com.dwalldorf.trackmytime.exception;

import javax.validation.constraints.NotNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Thrown when data submitted via forms is invalid. Creates {@link ObjectError} / {@link FieldError} for binding
 * the validation info to the form again.
 */
public class InvalidFormInputException extends RuntimeException {

    /**
     * The validated field
     */
    private final String field;

    /**
     * The rejected value
     */
    private final String rejectedValue;

    /**
     * @param field         field with validation error
     * @param message       validation message
     * @param rejectedValue rejected value
     */
    public InvalidFormInputException(String field, String message, String rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    /**
     * Creates {@link ObjectError} or {@link FieldError}
     *
     * @param objectName name of object
     * @return ObjectError | FieldError
     */
    public ObjectError toFormError(@NotNull String objectName) {
        if (field != null) {
            return new FieldError(objectName, field, rejectedValue, false, null, null, getMessage());
        }
        return new ObjectError(objectName, getMessage());
    }
}