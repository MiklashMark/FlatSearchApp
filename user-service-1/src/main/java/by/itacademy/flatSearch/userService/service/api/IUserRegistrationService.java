package by.itacademy.flatSearch.userService.service.api;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserRegistrationService {
    void register(UserRegistrationDTO userRegistration);
}
