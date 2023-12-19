package by.itacademy.flatSearch.userService.core.exception;

import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import lombok.Getter;
import org.springframework.dao.DataAccessException;

@Getter
public class InternalServerException extends DataAccessException {
    private ErrorResponse errorResponse;
    public InternalServerException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef("error");
    }
}
