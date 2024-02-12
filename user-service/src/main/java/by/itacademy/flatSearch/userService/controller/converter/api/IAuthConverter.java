package by.itacademy.flatSearch.userService.controller.converter.api;


import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.dto.UserRegistrationDTO;
import by.itacademy.exceptions.dto.VerificationMailDTO;

public interface IAuthConverter {
    UserDTO get();
    void save(UserRegistrationDTO user);
    String login(UserLoginDTO login);
    void verify(VerificationMailDTO verificationMailDTO);
}
