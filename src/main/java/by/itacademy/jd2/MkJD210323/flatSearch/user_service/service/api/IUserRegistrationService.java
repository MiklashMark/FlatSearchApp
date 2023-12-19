package by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserRegistrationService {
    void register(UserRegistrationDTO userRegistration);
}
