package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailQueueService;
import by.itacademy.flatSearch.userService.service.auth.holder.UserHolder;
import by.itacademy.flatSearch.userService.service.auth.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private final IValidationService validationService;
    private final ICRUDUserDao userDao;
    private final IMailQueueService mailQueueService;
    private PasswordEncoder passwordEncoder;
    private IVerificationDao verificationDao;
    private ICRUDUserDao crudUserDao;
    private final UserHolder holder;
    private JwtTokenHandler tokenHandler;

    public AuthService(IValidationService validationService,
                       ICRUDUserDao userDao,
                       IMailQueueService mailQueueService,
                       PasswordEncoder passwordEncoder,
                       IVerificationDao verificationDao,
                       ICRUDUserDao crudUserDao,
                       UserHolder holder,
                       ICRUDUserDao crudUserDao1,
                       JwtTokenHandler tokenHandler) {
        this.validationService = validationService;
        this.userDao = userDao;
        this.mailQueueService = mailQueueService;
        this.passwordEncoder = passwordEncoder;
        this.verificationDao = verificationDao;
        this.holder = holder;
        this.crudUserDao = crudUserDao1;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        String correctPassword = getCorrectPassword(userLoginDTO.getMail());

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), correctPassword)) {
            throw new ValidationException(Messages.INCORRECT_MAIL_OR_PASSWORD.getMessage());
        }

        return tokenHandler.generateAccessToken(userLoginDTO);
    }
    @Override
    public UserDTO get() {
        return holder.getUser();
    }


    @Override
    public void save(UserRegistrationDTO userRegistration) {
        validationService.validateUser(userRegistration);

        User user = EntityDTOMapper.instance.userRegistrationDTOToUserEntity(userRegistration);
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());

        try {
            userDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage(), e);
        }

        mailQueueService.addInMailQueue(user);
    }

    private String getCorrectPassword(String mail) {
        try {
            Optional<User> user = crudUserDao.findByMail(mail);
            if (user.isEmpty()) {
                throw new ValidationException(Messages.USER_NOT_FOUND.getMessage());
            }
            return user.get().getPassword();
        } catch (DataAccessException e) {
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage(), e);
        }
    }

}
