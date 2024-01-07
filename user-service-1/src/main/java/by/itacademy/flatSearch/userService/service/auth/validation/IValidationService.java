package by.itacademy.flatSearch.userService.service.auth.validation;


import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;

public interface IValidationService {
    void validateRegistration(UserRegistrationDTO registrationDTO);
    void validateLogin(UserLoginDTO loginDTO);

    public String encodePassword(String password);

}
