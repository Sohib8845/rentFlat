package com.company.olx.controller;

import com.company.olx.exceptions.BadRequestException;
import com.company.olx.exceptions.ForbiddenException;
import com.company.olx.exceptions.ItemNotFountException;
import com.company.olx.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandleController {
    @ExceptionHandler({ItemNotFountException.class,BadRequestException.class})
    public ResponseEntity<?> handleException(RuntimeException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<?> handleException(ForbiddenException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<?> handleException(UnauthorizedException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
