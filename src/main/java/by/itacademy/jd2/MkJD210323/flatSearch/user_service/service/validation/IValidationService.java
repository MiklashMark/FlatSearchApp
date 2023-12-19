package by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.validation;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.UserRegistrationDTO;

public interface IValidationService {
    void validateUser(UserRegistrationDTO user);
}
