package by.itacademy.flatSearch.userService.core.exception.custom_exceptions;

import by.itacademy.flatSearch.userService.core.enums.ErrorsTypes;
import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import lombok.Getter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;

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
