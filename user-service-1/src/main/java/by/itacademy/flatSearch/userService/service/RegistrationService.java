package by.itacademy.flatSearch.userService.service;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.dao.api.IRegistrationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.flatSearch.userService.service.api.IMailQueueService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegistrationService implements IUserRegistrationService {
    private final IValidationService validationService;
    private final IRegistrationDao userRegistrationDao;
    private final IMailQueueService mailQueueService;

    public RegistrationService(IValidationService validationService, IRegistrationDao userRegistrationDao, IMailQueueService verificationService) {
        this.validationService = validationService;
        this.userRegistrationDao = userRegistrationDao;
        this.mailQueueService = verificationService;
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
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage());
        }
        mailQueueService.addInMailQueue(user);
    }


    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
