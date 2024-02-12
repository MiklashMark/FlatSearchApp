package by.itacademy.flatSearch.userService.controller.converter;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.dto.UserRegistrationDTO;
import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.flatSearch.userService.controller.converter.api.IAuthConverter;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IVerificationService;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter implements IAuthConverter {
    private final IAuthService authService;
    private final IVerificationService verificationService;

    public AuthConverter(IAuthService authService, IVerificationService verificationService) {
        this.authService = authService;
        this.verificationService = verificationService;
    }

    @Override
    public UserDTO get() {
        User user = authService.get();
        return EntityDTOMapper.INSTANCE.userEntityToUserDTO(user);
    }

    @Override
    public void save(UserRegistrationDTO registrationDTO) {
        User user = EntityDTOMapper.INSTANCE.userRegistrationDTOToUserEntity(registrationDTO);
        authService.save(user);
    }

    @Override
    public String login(UserLoginDTO login) {
        return authService.login(login);
    }

    @Override
    public void verify(VerificationMailDTO verificationMailDTO) {
        VerificationMailEntity verificationMailEntity = EntityDTOMapper.INSTANCE.verificationDTOToEntity(verificationMailDTO);
        verificationService.verify(verificationMailEntity);
    }
}
