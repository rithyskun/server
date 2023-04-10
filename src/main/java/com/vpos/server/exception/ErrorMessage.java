package com.vpos.server.exception;

/*
 * @created 11/04/2023 - 2:31 AM
 * @project server
 * @author Rithy SKUN
 */

public class ErrorMessage {

    private String object;
    private String field;
    private String message;
    private Object rejectedValue;

    public ErrorMessage() {
    }

    public ErrorMessage(String object, String field, String message, Object rejectedValue) {
        this.object = object;
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public ErrorMessage(String object, String field, String message) {
        this.object = object;
        this.field = field;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String code) {
        this.object = code;
    }

    public String getFields() {
        return field;
    }

    public void setFields(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
