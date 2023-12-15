package by.itacademy.jd2.MkJD210323.flatSearch.userService.core.exception;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse errorResponse;

    public ValidationException(StructuredErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        errorResponse.setLogRef("structured_error");
    }

}
