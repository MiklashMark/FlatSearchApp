package by.itacademy.flatSearch.userService.core.exception;

import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse errorResponse;

    public ValidationException(StructuredErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        errorResponse.setLogRef("structured_error");
    }

}
