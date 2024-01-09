package by.itacademy.flatSearch.userService.service.user;

import by.itacademy.flatSearch.userService.core.dto.PageOfUserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.DuplicateEntityException;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public UserService(ICRUDUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userDao.saveAndFlush(user); // Is used for executing catch code before closing if transaction
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getMessage().contains("mail")) {
                    throw new DuplicateEntityException(ErrorMessages.
                            ALREADY_REGISTERED.getMessage());
                }
            }
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public PageOfUserDTO getPage(Pageable pageable) {
        Page<User> usersPage;

        try {
         usersPage = userDao.findAll(pageable);
         if (usersPage.isEmpty()) {
             throw new
         }
        }  catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }

        PageOfUserDTO pageOfUserDTO = new PageOfUserDTO();

        pageOfUserDTO.setSize(usersPage.getSize());
        pageOfUserDTO.setNumber(usersPage.getNumber());
        pageOfUserDTO.setTotalPages(usersPage.getTotalPages());
        pageOfUserDTO.setTotalElements(usersPage.getTotalElements());
        pageOfUserDTO.setNumberOfElements(usersPage.getNumberOfElements());
        pageOfUserDTO.setFirst(usersPage.isFirst());
        pageOfUserDTO.setLast(usersPage.isLast());

        List<UserDTO> usersDTO = EntityDTOMapper.INSTANCE
                .convertUserListToUserDTOList(usersPage.getContent());
        pageOfUserDTO.setContent(usersDTO);

        return pageOfUserDTO;
    }

    @Override
    public void update(UUID uuid, long dtUpdate) {
    }

    @Override
    public User get(UUID uuid) {
        return getUserByAttribute(() -> userDao.findByUuid(uuid),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User get(String mail) {
        return getUserByAttribute(() -> userDao.findByMail(mail),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User get(UserLoginDTO loginDTO) {
        return getUserByAttribute(() -> userDao.findByMail(loginDTO.getMail()),
                ErrorMessages.USER_NOT_FOUND.getMessage());
    }

    private User getUserByAttribute(Supplier<Optional<User>> userSupplier, String errorMessage) {
        try {
            return userSupplier.get()
                    .orElseThrow(() -> new ValidationException(errorMessage));
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }
}
