package com.vpos.server.exception;

/*
 * @created 11/04/2023 - 2:11 AM
 * @project server
 * @author Rithy SKUN
 */

import com.vpos.server.utils.AppUtils;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorDetails handleExceptions(Exception ex, WebRequest request) {
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(), ex.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"ConstraintViolationException", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = AppUtils.format(dateTime, "dd/MM/yyyy HH:mm:ss a");
        body.put("timestamp", formattedDate);
        body.put("status", status.value());

        List<ErrorMessage> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> mapToErrorMessage(error))
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }


    private ErrorMessage mapToErrorMessage(ObjectError error) {
        ConstraintViolationImpl<?> source =  (ConstraintViolationImpl)error.unwrap(ConstraintViolationImpl.class);
        String fieldError = "";
        String rejectedValue = "";
        if(error instanceof FieldError) {
            fieldError = ((FieldError) error).getField();
            rejectedValue = (String)((FieldError) error).getRejectedValue();
        }
        return new ErrorMessage(error.getObjectName(),fieldError,error.getDefaultMessage(),rejectedValue);
    }


}