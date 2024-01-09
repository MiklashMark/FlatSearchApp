package by.itacademy.flatSearch.userService.controller.converter.api;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationMailDTO;

public interface IAuthConverter {
    UserDTO get();
    void save(UserRegistrationDTO user);
    String login(UserLoginDTO login);
    void verify(VerificationMailDTO verificationMailDTO);
}
