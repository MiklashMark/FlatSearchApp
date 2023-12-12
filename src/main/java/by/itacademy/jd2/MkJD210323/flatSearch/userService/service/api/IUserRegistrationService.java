package by.itacademy.jd2.MkJD210323.flatSearch.userService.service.api;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import org.springframework.stereotype.Service;

@Service
public interface IUserRegistrationService {
    void register(UserRegistration userRegistration);
}
