package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.UserStatus;
import by.itacademy.exceptions.enums.action.Actions;
import by.itacademy.exceptions.enums.action.EssenceType;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.enums.messages.Messages;
import by.itacademy.exceptions.exception.custom_exceptions.AccountActivationException;
import by.itacademy.exceptions.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.aop.Audited;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailVerificationService;
import by.itacademy.flatSearch.userService.service.auth.api.IPasswordEncoders;
import by.itacademy.flatSearch.userService.service.auth.holder.UserHolder;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class AuthService implements IAuthService {
    private final IValidationService validationService;
    private final IUserService userService;
    private  final IMailVerificationService mailVerificationService;
    private final UserHolder holder;
    private final JwtTokenHandler tokenHandler;
    private final IPasswordEncoders passwordEncoder;
    public AuthService(IValidationService validationService,
                       IUserService userService,
                       IMailVerificationService mailVerificationService, UserHolder holder,
                       JwtTokenHandler tokenHandler, IPasswordEncoders passwordEncoder) {
        this.validationService = validationService;
        this.userService = userService;
        this.mailVerificationService = mailVerificationService;
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

        UserDTO userDTO = EntityDTOMapper.INSTANCE.userEntityToUserDTO(user);
        return tokenHandler.generateAccessToken(userDTO);
    }

    @Override
    @Audited(action = Actions.ME, essenceType = EssenceType.USER)
    public User get() {
        return getFromContext();
    }

    @Override
    public User getFromContext() {
        return holder.getUser();
    }


    @Override
    @Transactional
    public void save(User user) {
        validationService.validateRegistration(user);
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setRole(UserRole.USER);
        userService.save(user);
        mailVerificationService.create(user);
    }

    private void addUserInSecurityContext(UserDTO userDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDTO, null, userDTO.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
