package by.itacademy.flatSearch.userService.core.exception;

import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse structuredErrorResponse;
    private ErrorResponse errorResponse;

    public ValidationException(StructuredErrorResponse errorResponse) {
        this.structuredErrorResponse = errorResponse;
        errorResponse.setLogRef("structured_error");
    }

    public ValidationException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        errorResponse.setLogRef("error");
    }

    public ValidationException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef("error");
    }
}
