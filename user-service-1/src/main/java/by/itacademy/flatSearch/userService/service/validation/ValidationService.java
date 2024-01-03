package by.itacademy.flatSearch.userService.service.validation;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.ValidationPattern;
import by.itacademy.flatSearch.userService.core.error.ErrorDetail;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.error.StructuredErrorResponse;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {
    private ICRUDUserDao crudUserDao;
    private StructuredErrorResponse errorsResponse;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int REQUIRED_WORDS_IN_FIO = 3;

    public ValidationService(ICRUDUserDao crudUserDao, StructuredErrorResponse errorsResponse) {
        this.crudUserDao = crudUserDao;
        this.errorsResponse = errorsResponse;
    }

    @Override
    public void validateUser(UserRegistrationDTO user) {
        errorsResponse = new StructuredErrorResponse();
        validateMail(user.getMail());
        validateFio(user.getFio());
        validatePassword(user.getPassword());
        ifExists(user);

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }
    }

    private void validateMail(String mail) {
        String regexPattern = ValidationPattern.EMAIL.getPattern();

        if (!mail.matches(regexPattern)) {
            addError("mail", Messages.INCORRECT_MAIL_FORMAT.getMessage());
        }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();
        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            addError("password", Messages.PASSWORD_LENGTH_REQUIREMENT.getMessage());
        }
    }

    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO) {
            addError("fio", Messages.INVALID_FIO.getMessage());
        }
    }

    private void ifExists(UserRegistrationDTO user) {
        if (crudUserDao.existsByMail(user.getMail())) {
            addError("mail", Messages.EMAIL_ALREADY_REGISTERED.getMessage());
        }

        if (crudUserDao.existsByFio(user.getFio())) {
            addError("fio", Messages.FIO_ALREADY_EXISTS.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}

