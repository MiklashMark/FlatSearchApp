package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailQueueService;
import by.itacademy.flatSearch.userService.service.auth.holder.UserHolder;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements IAuthService {
    private final IValidationService validationService;
    private final ICRUDUserDao userDao;
    private final IMailQueueService mailQueueService;
    private IVerificationDao verificationDao;
    private ICRUDUserDao crudUserDao;
    private final UserHolder holder;
    private JwtTokenHandler tokenHandler;

    public AuthService(IValidationService validationService,
                       ICRUDUserDao userDao,
                       IMailQueueService mailQueueService,
                       IVerificationDao verificationDao,
                       UserHolder holder,
                       ICRUDUserDao crudUserDao,
                       JwtTokenHandler tokenHandler) {
        this.validationService = validationService;
        this.userDao = userDao;
        this.mailQueueService = mailQueueService;
        this.verificationDao = verificationDao;
        this.holder = holder;
        this.crudUserDao = crudUserDao;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public String login(UserLoginDTO LoginDTO) {
        validationService.validateLogin(LoginDTO);
        return tokenHandler.generateAccessToken(LoginDTO);
    }
    @Override
    public UserDTO get() {
        return holder.getUser();
    }


    @Override
    public void save(UserRegistrationDTO registrationDTO) {
        validationService.validateRegistration(registrationDTO);

        User user = EntityDTOMapper.instance.userRegistrationDTOToUserEntity(registrationDTO);
        user.setPassword(validationService.encodePassword(registrationDTO.getPassword()));
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());

        try {
            userDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage(), e);
        }

        mailQueueService.addInMailQueue(user);
    }
}
