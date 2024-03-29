package by.itacademy.exceptions.exception.custom_exceptions;


import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;


@Getter
public class DuplicateEntityException extends DataIntegrityViolationException {
    private final ErrorResponse errorResponse;
    public DuplicateEntityException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
