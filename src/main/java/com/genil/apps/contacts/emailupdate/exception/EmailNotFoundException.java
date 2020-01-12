package com.genil.apps.contacts.emailupdate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Antony Genil Gregory on 1/12/2020 12:42 PM
 * For project : email-update
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException() {
        this("Could not find any cars !");
    }
    public EmailNotFoundException(Long id) {
        this("Could not find any email with the id "+id);
    }
    public EmailNotFoundException(String message) {
        this(message, null);
    }
    public EmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
