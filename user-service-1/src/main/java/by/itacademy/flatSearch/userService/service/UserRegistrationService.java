package by.itacademy.flatSearch.userService.service;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.enums.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.dao.api.IUserRegistrationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.flatSearch.userService.service.api.IUserVerificationService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserRegistrationService implements IUserRegistrationService {
    private final IValidationService validationService;
    private final IUserRegistrationDao userRegistrationDao;
    private final IUserVerificationService verificationService;

    public UserRegistrationService(IValidationService validationService, IUserRegistrationDao userRegistrationDao, IUserVerificationService verificationService) {
        this.validationService = validationService;
        this.userRegistrationDao = userRegistrationDao;
        this.verificationService = verificationService;
    }

    @Override
    @Transactional
    public void register(UserRegistrationDTO userRegistration) {
        validationService.validateUser(userRegistration);

        User user = new User();
        user.setMail(userRegistration.getMail());
        user.setFio(userRegistration.getFio());
        user.setPassword(userRegistration.getPassword());
        user.setUuid(generateUUID());
        user.setDataCreate(LocalDate.now());
        user.setDataUpdate(LocalDate.now());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.WAITING_ACTIVATION);

        try {
            userRegistrationDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
        verificationService.verify(user);
    }


    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
