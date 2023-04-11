package com.vpos.server.exception;

/*
 * @created 11/04/2023 - 3:56 AM
 * @project server
 * @author Rithy SKUN
 */

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime dateTime;
    private String message ;
    private String errorDetails;

    public ErrorDetails(LocalDateTime dateTime, String message, String errorDetails) {
        this.dateTime = dateTime;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
