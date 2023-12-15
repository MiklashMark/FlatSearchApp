package by.itacademy.jd2.MkJD210323.flatSearch.userService.service;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserRole;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.ErrorMessages;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.api.IUserRegistrationDao;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.entity.User;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
        user.setDataCreate(32323);
        user.setDataUpdate(3223);
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.WAITING_ACTIVATION);

        try {
            userRegistrationDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }


    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
