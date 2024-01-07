package by.itacademy.flatSearch.userService.service.auth.validation;

import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.ErrorFieldNames;
import by.itacademy.flatSearch.userService.core.enums.ValidationPattern;
import by.itacademy.flatSearch.userService.core.error.ErrorDetail;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
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
    public void registrationValidation(UserRegistrationDTO registrationDTO) {
        errorsResponse = new StructuredErrorResponse();
        validateMail(registrationDTO.getMail());
        validateFio(registrationDTO.getFio());
        validatePassword(registrationDTO.getPassword());
        ifExists(registrationDTO);

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }
    }

    @Override
    public void loginValidation(UserLoginDTO loginDTO) {
        errorsResponse = new StructuredErrorResponse();

    }

    private void validateMail(String mail) {
        String regexPattern = ValidationPattern.EMAIL.getPattern();
        if (mail.isBlank()) {
            addError(ErrorFieldNames.MAIL.getField(), ErrorMessages.INCORRECT_MAIL_FORMAT.getMessage());
        }

        if (!mail.matches(regexPattern)) {
            addError(ErrorFieldNames.MAIL.getField(), ErrorMessages.INCORRECT_MAIL_FORMAT.getMessage());
        }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();
        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            addError(ErrorFieldNames.PASSWORD.getField(), ErrorMessages.PASSWORD_LENGTH_REQUIREMENT.getMessage());
        }
    }

    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO) {
            addError(ErrorFieldNames.FIO.getField(), ErrorMessages.INVALID_FIO.getMessage());
        }
    }

    private void ifExists(UserRegistrationDTO user) {
        if (crudUserDao.existsByMail(user.getMail())) {
            addError(ErrorFieldNames.MAIL.getField(), ErrorMessages.EMAIL_ALREADY_REGISTERED.getMessage());
        }

        if (crudUserDao.existsByFio(user.getFio())) {
            addError(ErrorFieldNames.FIO.getField(), ErrorMessages.FIO_ALREADY_EXISTS.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}

