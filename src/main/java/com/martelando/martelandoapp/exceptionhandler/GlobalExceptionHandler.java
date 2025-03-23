package com.martelando.martelandoapp.exceptionhandler;

import com.martelando.martelandoapp.exception.EmailInUseException;
import com.martelando.martelandoapp.exception.NotFoundException;
import com.martelando.martelandoapp.exception.PhoneInUseException;
import com.martelando.martelandoapp.exception.UserAlreadyMakeOfferException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyMakeOfferException.class)
    public ResponseEntity<String> handleUserAlreadyMakeOfferException(UserAlreadyMakeOfferException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneInUseException.class)
    public ResponseEntity<String> handleNotFoundException(PhoneInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<String> handleNotFoundException(EmailInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Erro interno do servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
