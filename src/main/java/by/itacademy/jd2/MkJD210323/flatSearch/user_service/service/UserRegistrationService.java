package by.itacademy.jd2.MkJD210323.flatSearch.user_service.service;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.enums.UserRole;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.enums.UserStatus;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.enums.ErrorMessages;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.exception.InternalServerException;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.api.IUserRegistrationDao;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.User;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api.IUserRegistrationService;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api.IUserVerificationService;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.validation.IValidationService;
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
