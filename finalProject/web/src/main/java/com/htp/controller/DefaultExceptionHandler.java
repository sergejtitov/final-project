package com.htp.controller;

import com.htp.controller.errors.ErrorMessage;
import com.htp.exceptions.CustomValidationException;
import com.htp.exceptions.EntityAlreadyExists;
import com.htp.exceptions.NoSuchEntityException;
import com.htp.exceptions.NoSuchValueInDictionary;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger LOG = Logger.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(NoSuchEntityException e){
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchValueInDictionary.class)
    public ResponseEntity<ErrorMessage> handleNoSuchValueInDictionary(NoSuchValueInDictionary e){
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<ErrorMessage> handleEntityAlreadyExists(EntityAlreadyExists e){
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorMessage> handleCustomValidationException(CustomValidationException e){
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleTokenProcessingException(AuthenticationException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UnsupportedJwtException.class,
            MalformedJwtException.class,
            SignatureException.class,
            ExpiredJwtException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNPException(NullPointerException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
