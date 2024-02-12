package by.itacademy.exceptions.exception.custom_exceptions;

import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;
import by.itacademy.exceptions.error.StructuredErrorResponse;

public class UnregisteredActionException extends RuntimeException{
    private final ErrorResponse errorResponse;
    public UnregisteredActionException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
