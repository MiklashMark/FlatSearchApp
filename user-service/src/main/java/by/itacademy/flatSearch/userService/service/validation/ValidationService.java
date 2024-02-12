package by.itacademy.flatSearch.userService.service.validation;

import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.enums.FieldNames;
import by.itacademy.exceptions.enums.ValidationPattern;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.error.ErrorDetail;
import by.itacademy.exceptions.error.StructuredErrorResponse;
import by.itacademy.exceptions.exception.custom_exceptions.ValidationException;
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
            addError(FieldNames.MAIL.getField(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }
        if (!mail.matches(regexPattern)) {
            addError(FieldNames.MAIL.getField(), ErrorMessages.INCORRECT_MAIL_FORMAT.getMessage());
        }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();
        if (password.isBlank()) {
            addError(FieldNames.PASSWORD.getField(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }
        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            addError(FieldNames.PASSWORD.getField(), ErrorMessages.PASSWORD_LENGTH_REQUIREMENT.getMessage());
        }
    }


    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");
        if (fio.isBlank()) {
            addError(FieldNames.FIO.getField(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO) {
            addError(FieldNames.FIO.getField(), ErrorMessages.INVALID_FIO.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}

