package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;

public interface IAuthService {
    String login(UserLoginDTO login);
    User get();
    void save(User user);
    String encodePassword(String password);
}
