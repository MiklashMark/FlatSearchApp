package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.enums.messages.Messages;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.AccountActivationException;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailQueueService;
import by.itacademy.flatSearch.userService.service.auth.holder.UserHolder;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService implements IAuthService {
    private final IValidationService validationService;
    private final IUserService userService;
    private final ICRUDUserDao userDao;
    private final IMailQueueService mailQueueService;
    private final UserHolder holder;
    private JwtTokenHandler tokenHandler;

    public AuthService(IValidationService validationService,
                       IUserService userService, ICRUDUserDao userDao,
                       IMailQueueService mailQueueService,
                       UserHolder holder,
                       JwtTokenHandler tokenHandler) {
        this.validationService = validationService;
        this.userService = userService;
        this.userDao = userDao;
        this.mailQueueService = mailQueueService;
        this.holder = holder;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        validationService.validateLogin(loginDTO);
        UserDTO userDTO = userService.get(loginDTO);
        if (!userDTO.getStatus().equals(UserStatus.ACTIVATED)) {
            throw new AccountActivationException(Messages.ACCOUNT_IS_NOT_ACTIVATED.getMessage());
        }
        return tokenHandler.generateAccessToken(loginDTO);
    }
    @Override
    public UserDTO get() {
        return holder.getUser();
    }


    @Override
    @Transactional
    public void save(UserRegistrationDTO registrationDTO) {
        validationService.validateRegistration(registrationDTO);

        User user = EntityDTOMapper.INSTANCE.userRegistrationDTOToUserEntity(registrationDTO);
        user.setPassword(validationService.encodePassword(registrationDTO.getPassword()));
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setRole(UserRole.USER);
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());

        UserCreateDTO userCreateDTO = EntityDTOMapper
                .INSTANCE.convertUserRegistrationDTOToUserCreateDTO(registrationDTO);
        userCreateDTO.setRole(UserRole.USER);
        userCreateDTO.setStatus(UserStatus.WAITING_ACTIVATION);
        userService.save(userCreateDTO);

        mailQueueService.addInMailQueue(user);
    }
}
