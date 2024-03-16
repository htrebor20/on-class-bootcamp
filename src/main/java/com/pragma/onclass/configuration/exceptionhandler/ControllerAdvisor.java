package com.pragma.onclass.configuration.exceptionhandler;

import com.pragma.onclass.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.onclass.domain.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
