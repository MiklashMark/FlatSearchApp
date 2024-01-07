package by.itacademy.flatSearch.userService.core.exception.custom_exceptions;

import by.itacademy.flatSearch.userService.core.enums.ErrorsTypes;
import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse structuredErrorResponse;
    private ErrorResponse errorResponse;
    private boolean isStructuredError;

    public ValidationException(StructuredErrorResponse errorResponse) {
        isStructuredError = true;
        this.structuredErrorResponse = errorResponse;
        errorResponse.setLogRef(ErrorsTypes.STRUCTURED_ERROR.getMessage());
    }

    public ValidationException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
