package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.exception;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.error.ErrorResponse;
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
