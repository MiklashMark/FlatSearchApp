package by.itacademy.flatSearch.userService.core.exception;

import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse structuredErrorResponsesponse;
    private ErrorResponse errorResponse;

    public ValidationException(StructuredErrorResponse errorResponse) {
        this.structuredErrorResponsesponse = errorResponse;
        errorResponse.setLogRef("structured_error");
    }

    public ValidationException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        errorResponse.setLogRef("error");
    }
}
