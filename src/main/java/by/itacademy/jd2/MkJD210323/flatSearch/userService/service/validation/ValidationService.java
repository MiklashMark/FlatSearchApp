package by.itacademy.jd2.MkJD210323.flatSearch.userService.service.validation;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.ValidationPattern;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.error.ErrorDetail;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.ErrorMessages;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.error.StructuredErrorResponse;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.api.IUserRegistrationDao;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {
    private IUserRegistrationDao userRegistrationDao;
    private StructuredErrorResponse errorsResponse;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int REQUIRED_WORDS_IN_FIO = 3;

    public ValidationService(IUserRegistrationDao userRegistrationDao) {
        this.userRegistrationDao = userRegistrationDao;
    }

    @Override
    public void validateUser(UserRegistration user) {
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
            addError("mail", ErrorMessages.INCORRECT_MAIL_FORMAT.getMessage());
        }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();
        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            addError("password", ErrorMessages.PASSWORD_LENGTH_REQUIREMENT.getMessage());
        }
    }

    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO) {
            addError("fio", ErrorMessages.INVALID_FIO.getMessage());
        }
    }

    private void ifExists(UserRegistration user) {
        if (userRegistrationDao.existsByMail(user.getMail())) {
            addError("mail", ErrorMessages.EMAIL_ALREADY_REGISTERED.getMessage());
        }

        if (userRegistrationDao.existsByFio(user.getFio())) {
            addError("fio", ErrorMessages.FIO_ALREADY_EXISTS.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}

