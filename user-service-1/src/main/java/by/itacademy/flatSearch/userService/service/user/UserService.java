package by.itacademy.flatSearch.userService.service.user;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserInfDTO;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements IUserService {
    private final ICRUDUserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserService(ICRUDUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        user.setFio(userDTO.getFio());
        user.setMail(userDTO.getMail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());
        user.setUuid(UUID.randomUUID());

        try {
            userDao.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage(), e);
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public UserInfDTO get(UUID id) {
        return null;
    }

    @Override
    public User get(String mail) {
        try {
            return userDao.findByMail(mail)
                    .orElseThrow(() -> new ValidationException(Messages.USER_NOT_FOUND.getMessage()));
        }  catch (DataAccessException e) {
            throw new InternalServerException(Messages.SERVER_ERROR.getMessage(), e);
        }
    }

    @Override
    public UserDTO update(UUID uuid, long dtUpdate) {
        return null;
    }
}
