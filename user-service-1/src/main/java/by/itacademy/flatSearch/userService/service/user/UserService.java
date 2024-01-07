package by.itacademy.flatSearch.userService.service.user;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import by.itacademy.flatSearch.userService.service.validation.IValidationService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final ICRUDUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final IValidationService validationService;

    public UserService(ICRUDUserDao userDao,
                       PasswordEncoder passwordEncoder,
                       IValidationService validationService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    @Override
    public void save(UserCreateDTO userCreateDTO) {
        validationService.validateCreation(userCreateDTO);

        User user = EntityDTOMapper.INSTANCE.convertUserCreateDTOToUserEntity(userCreateDTO);

        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());
        user.setRole(userCreateDTO.getRole());
        user.setStatus(userCreateDTO.getStatus());

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
    public void update(UUID uuid, long dtUpdate) {
    }

    @Override
    public UserDTO get(UUID uuid) {
        return getUserByAttribute(() -> userDao.findByUuid(uuid),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    @Override
    public UserDTO get(String mail) {
        return getUserByAttribute(() -> userDao.findByMail(mail),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    @Override
    public UserDTO get(UserLoginDTO loginDTO) {
        return getUserByAttribute(() -> userDao.findByMail(loginDTO.getMail()),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    private UserDTO getUserByAttribute(Supplier<Optional<User>> userSupplier, String errorMessage) {
        try {
            User user = userSupplier.get()
                    .orElseThrow(() -> new ValidationException(errorMessage));
            return EntityDTOMapper.INSTANCE.userEntityToUserDTO(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

}
