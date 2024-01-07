package by.itacademy.flatSearch.userService.core.exception.custom_exceptions;

import by.itacademy.flatSearch.userService.core.enums.ErrorsTypes;
import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public InternalServerException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }

}
