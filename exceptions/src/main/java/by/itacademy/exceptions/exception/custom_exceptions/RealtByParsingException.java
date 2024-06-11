package by.itacademy.exceptions.exception.custom_exceptions;

import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;

public class RealtByParsingException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public RealtByParsingException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }

    public RealtByParsingException(String message, Throwable cause) {
        super(message, cause);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
