package by.itacademy.flatSearch.userService.service;

import by.itacademy.flatSearch.userService.controller.web.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.core.dto.LoginDTO;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.service.api.ILoginService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    private final ICRUDUserDao crudUserDao;
    private PasswordEncoder passwordEncoder;
    private JwtTokenHandler tokenHandler;

    public LoginService(ICRUDUserDao crudUserDao,
                        PasswordEncoder passwordEncoder,
                        JwtTokenHandler tokenHandler) {
        this.crudUserDao = crudUserDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        String correctPassword = getCorrectPassword(loginDTO.getMail());

        if (!passwordEncoder.matches(loginDTO.getPassword(), correctPassword)) {
            throw new ValidationException(Messages.INCORRECT_LOGIN_OR_PASSWORD.getMessage());
        }

        return tokenHandler.generateAccessToken(loginDTO);
    }

    private String getCorrectPassword(String mail) {

        try {
            return crudUserDao.findByMail(mail).getPassword();

        } catch (DataAccessException e) {
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage());
        }
    }
}
