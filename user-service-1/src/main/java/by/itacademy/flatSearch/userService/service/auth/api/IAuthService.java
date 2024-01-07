package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;

public interface IAuthService {
    String login(UserLoginDTO login);
    UserDTO get();
    void save(UserRegistrationDTO userRegistration);
}
