package by.itacademy.flatSearch.userService.service.auth.validation;


import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;

public interface IValidationService {
    void validateUser(UserRegistrationDTO user);
}
