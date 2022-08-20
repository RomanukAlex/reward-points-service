package com.home.api.rewardpointsservice.controllers;

import com.home.api.rewardpointsservice.exeption.LanguageNotFoundException;
import com.home.api.rewardpointsservice.exeption.TransactionNotFoundException;
import com.home.api.rewardpointsservice.vo.ApiError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler
{
    @Value( "#{'${messages.languages}'.split(',')}" )
    private List<String> validLanguages;

    @ExceptionHandler( TransactionNotFoundException.class )
    public ResponseEntity<ApiError> handleTransactionNotFoundException( final TransactionNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                             .body( ApiError.builder()
                                            .status( HttpStatus.NOT_FOUND )
                                            .message( exception.getMessage() )
                                            .build() );
    }

    @ExceptionHandler( LanguageNotFoundException.class )
    public ResponseEntity<ApiError> handleLanguageNotFoundException( final LanguageNotFoundException exception )
    {
        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                             .body( ApiError.builder()
                                            .status( HttpStatus.BAD_REQUEST )
                                            .message( exception.getMessage().concat( validLanguages.toString() ))
                                            .build() );
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<ApiError> handleException( final Exception exception )
    {
        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                             .body( ApiError.builder()
                                            .status( HttpStatus.INTERNAL_SERVER_ERROR )
                                            .message( exception.getMessage() )
                                            .build() );
    }
}
