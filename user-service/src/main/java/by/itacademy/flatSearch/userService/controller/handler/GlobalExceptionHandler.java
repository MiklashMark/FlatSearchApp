package by.itacademy.flatSearch.userService.controller.handler;


import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserLockException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(UserLockException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleInternalServerException(InternalServerException e) {
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception e) {
        return new ResponseEntity<>(ErrorMessages.UNKNOWN_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
