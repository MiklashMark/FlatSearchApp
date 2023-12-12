package by.itacademy.jd2.MkJD210323.flatSearch.userService.service.validation;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.ValidationPattern;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.error.ErrorDetail;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.error.StructuredErrorResponse;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class ValidationService implements IValidationService {
    private final StructuredErrorResponse errorsResponse = new StructuredErrorResponse();
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int REQUIRED_WORDS_IN_FIO = 3;

    @Override
    public void validateUser(UserRegistration user) {
        validateMail(user.getMail());
        validateFio(user.getFio());
        validatePassword(user.getPassword());

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
        }

    }

    private void validateMail(String mail) {
        String regexPattern = ValidationPattern.EMAIL.getPattern();

       if (!mail.matches(regexPattern)) {
           ErrorDetail error = new ErrorDetail("mail", "Incorrect mail format.");
           errorsResponse.addError(error);
       }
    }

    private void validatePassword(String password) {
        String regex = ValidationPattern.PASSWORD.getPattern();

        if (password.length() < MIN_PASSWORD_LENGTH && !password.matches(regex)) {
            ErrorDetail error = new ErrorDetail("password",
                    "Password length should be at least 8 characters. " +
                            "The password must include at least letters, digits, and one or more special characters.");
            errorsResponse.addError(error);
        }
    }

    private void validateFio(String fio) {
        String regex = ValidationPattern.FIO.getPattern();
        String[] words = fio.split("[ -]");

        if (!fio.matches(regex) && words.length != REQUIRED_WORDS_IN_FIO ) {
            ErrorDetail error = new ErrorDetail("fio",
                    "Fio" +
                            " must consist of 3 words starting with a capital letter." +
                            " Only letters are used.");
            errorsResponse.addError(error);
        }
    }


}
