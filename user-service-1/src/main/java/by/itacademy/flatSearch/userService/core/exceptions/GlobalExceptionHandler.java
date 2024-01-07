package by.itacademy.flatSearch.userService.core.exceptions;

import by.itacademy.flatSearch.userService.core.exceptions.exceptions.AccountActivationException;
import by.itacademy.flatSearch.userService.core.exceptions.exceptions.InternalServerException;
import by.itacademy.flatSearch.userService.core.exceptions.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException e) {
        if (e.isStructuredError()) {
            return new ResponseEntity<>(e.getStructuredErrorResponse(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }
    @ExceptionHandler(AccountActivationException.class)
    public ResponseEntity<Object> handleValidationException(AccountActivationException e) {
        if (e.isStructuredError()) {
            return new ResponseEntity<>(e.getStructuredErrorResponse(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleInternalServerException(InternalServerException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception e) {
        return new ResponseEntity<>("Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
