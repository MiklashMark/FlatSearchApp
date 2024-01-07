package by.itacademy.flatSearch.userService.service.user;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exceptions.exceptions.InternalServerException;
import by.itacademy.flatSearch.userService.core.exceptions.exceptions.ValidationException;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final ICRUDUserDao userDao;
    private PasswordEncoder passwordEncoder;
    private IValidationService validationService;

    public UserService(ICRUDUserDao userDao, PasswordEncoder passwordEncoder, IValidationService validationService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    @Override
    public void save(UserCreateDTO userCreateDTO) {
        validationService.validateCreation(userCreateDTO);

        User user = EntityDTOMapper.instance.convertUserCreateDTOToUserEntity(userCreateDTO);

        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        try {
            userDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO get(UUID uuid) {
        try {
            User user = userDao.findByUuid(uuid)
                    .orElseThrow(() -> new ValidationException(ErrorMessages.USER_NOT_FOUND.getMessage()));
            return EntityDTOMapper.instance.userEntityToUserDTO(user);

        }  catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public UserDTO get(String mail) {
        try {
            User user = userDao.findByMail(mail)
                    .orElseThrow(() -> new ValidationException(ErrorMessages.USER_NOT_FOUND.getMessage()));
          return EntityDTOMapper.instance.userEntityToUserDTO(user);

        }  catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public void update(UUID uuid, long dtUpdate) {

    }
}
