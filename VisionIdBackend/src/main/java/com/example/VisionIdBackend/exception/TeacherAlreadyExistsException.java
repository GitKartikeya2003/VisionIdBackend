package com.example.VisionIdBackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeacherAlreadyExistsException extends RuntimeException {

    public TeacherAlreadyExistsException(String message) {

        super(message);
    }

}
