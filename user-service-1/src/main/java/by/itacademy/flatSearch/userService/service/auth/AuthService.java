package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.enums.messages.Messages;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.AccountActivationException;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailQueueService;
import by.itacademy.flatSearch.userService.service.auth.holder.UserHolder;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class AuthService implements IAuthService {
    private final IValidationService validationService;
    private final IUserService userService;
    private final IMailQueueService mailQueueService;
    private final UserHolder holder;
    private final JwtTokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IValidationService validationService,
                       IUserService userService,
                       IMailQueueService mailQueueService,
                       UserHolder holder,
                       JwtTokenHandler tokenHandler,
                       PasswordEncoder passwordEncoder) {
        this.validationService = validationService;
        this.userService = userService;
        this.mailQueueService = mailQueueService;
        this.holder = holder;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        validationService.validateLogin(loginDTO);
        User user = userService.get(loginDTO);

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ValidationException(ErrorMessages.INCORRECT_MAIL_OR_PASSWORD.getMessage());
        }
        if (user.getStatus() != UserStatus.ACTIVATED) {
            throw new AccountActivationException(Messages.ACCOUNT_IS_NOT_ACTIVATED.getMessage());
        }
        return tokenHandler.generateAccessToken(loginDTO);
    }

    @Override
    public User get() {
        return holder.getUser();
    }


    @Override
    @Transactional
    public void save(User user) {
        validationService.validateRegistration(user);

        user.setPassword(encodePassword(user.getPassword()));
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setRole(UserRole.USER);

        userService.save(user);
        mailQueueService.addInMailQueue(user);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
