package by.itacademy.exceptions.exception.custom_exceptions;

import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;

public class AccessDeniedException extends RuntimeException{
    private final ErrorResponse errorResponse;
    public AccessDeniedException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}

