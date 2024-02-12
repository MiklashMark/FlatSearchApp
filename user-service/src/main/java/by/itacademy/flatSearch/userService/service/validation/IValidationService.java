package by.itacademy.flatSearch.userService.service.validation;

import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;

public interface IValidationService {
    void validateRegistration(User user);
    void validateLogin(UserLoginDTO user);
    void validateCreation(User user);
}
