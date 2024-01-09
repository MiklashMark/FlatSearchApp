package by.itacademy.flatSearch.userService.service.validation;

import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.enums.ErrorFieldNames;
import by.itacademy.flatSearch.userService.core.enums.ValidationPattern;
import by.itacademy.flatSearch.userService.core.error.ErrorDetail;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {
    private StructuredErrorResponse errorsResponse;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int REQUIRED_WORDS_IN_FIO = 3;

    public ValidationService(StructuredErrorResponse errorsResponse) {
        this.errorsResponse = errorsResponse;
    }

    @Override
    public void validateRegistration(User user) {
        errorsResponse = new StructuredErrorResponse();

        validateMail(user.getMail());
        validateFio(user.getFio());
        validatePassword(user.getPassword());

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }
    }

    @Override
    public void validateLogin(UserLoginDTO user) {
        errorsResponse = new StructuredErrorResponse();

        validateMail(user.getMail());
        validatePassword(user.getPassword());


        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }
    }

    @Override
    public void validateCreation(User user) {
        errorsResponse = new StructuredErrorResponse();
        validateMail(user.getMail());
        validateFio(user.getFio());
        validatePassword(user.getPassword());

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }

    }

    private void validateMail(String mail) {
        String regexPattern = ValidationPattern.EMAIL.getPattern();
        if (mail.isBlank()) {
            addError(ErrorFieldNames.MAIL.getField(), ErrorMessages.MAIL_IS_EMPTY.getMessage());
        }
        if (!mail.matches(regexPattern)) {
            addError(ErrorFieldNames.MAIL.getField(), ErrorMessages.INCORRECT_MAIL_FORMAT.getMessage());
        }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();
        if (password.isBlank()) {
            addError(ErrorFieldNames.PASSWORD.getField(), ErrorMessages.PASSWORD_IS_EMPTY.getMessage());
        }
        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            addError(ErrorFieldNames.PASSWORD.getField(), ErrorMessages.PASSWORD_LENGTH_REQUIREMENT.getMessage());
        }
    }

    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");
        if (fio.isBlank()) {
            addError(ErrorFieldNames.FIO.getField(), ErrorMessages.FIO_IS_EMPTY.getMessage());
        }

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO) {
            addError(ErrorFieldNames.FIO.getField(), ErrorMessages.INVALID_FIO.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}

