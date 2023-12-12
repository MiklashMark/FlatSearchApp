package by.itacademy.jd2.MkJD210323.flatSearch.userService.service;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserRole;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.api.IUserRegistrationDao;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.entity.User;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.service.validation.IValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserRegistrationService implements IUserRegistrationService {
    private final IValidationService validationService;
    private final IUserRegistrationDao userRegistrationDao;

    public UserRegistrationService(IValidationService validationService, IUserRegistrationDao userRegistrationDao) {
        this.validationService = validationService;
        this.userRegistrationDao = userRegistrationDao;
    }

    @Override
    public void register(UserRegistration userRegistration) {
        validationService.validateUser(userRegistration);

        User user = new User();
        user.setMail(userRegistration.getMail());
        user.setFio(userRegistration.getFio());
        user.setPassword(userRegistration.getPassword());
        user.setUuid(generateUUID());
        user.setDataCreate(323232322);
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.WAITING_ACTIVATION);

        userRegistrationDao.save(user);
  }


    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
