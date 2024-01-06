package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.core.dto.LoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthService {
    String login(LoginDTO login);
    UserDetails get();
    void save(UserRegistrationDTO userRegistration);
}
