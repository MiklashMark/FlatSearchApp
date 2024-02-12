package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;

public interface IAuthService {
    String login(UserLoginDTO login);
    User get();

    User getFromContext();

    void save(User user);
}
