package by.itacademy.exceptions.exception.custom_exceptions;


import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;
import by.itacademy.exceptions.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class AccountActivationException extends RuntimeException {
    private StructuredErrorResponse structuredErrorResponse;
    private ErrorResponse errorResponse;
    private boolean isStructuredError;

    public AccountActivationException(StructuredErrorResponse errorResponse) {
        isStructuredError = true;
        this.structuredErrorResponse = errorResponse;
        errorResponse.setLogRef(ErrorsTypes.STRUCTURED_ERROR.getMessage());
    }

    public AccountActivationException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}