package by.itacademy.exceptions.exception.custom_exceptions;

import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;
import lombok.Getter;

import java.io.IOException;

@Getter
public class FileCreationException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public FileCreationException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
