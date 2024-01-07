package by.itacademy.flatSearch.userService.service.validation;


import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;

public interface IValidationService {
    void validateRegistration(UserRegistrationDTO registrationDTO);
    void validateLogin(UserLoginDTO loginDTO);
    void validateCreation(UserCreateDTO createDTO);

    String encodePassword(String password);
}
