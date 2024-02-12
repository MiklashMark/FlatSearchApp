package by.itacademy.flatSearch.userService.service.user;

import by.itacademy.exceptions.dto.*;
import by.itacademy.exceptions.enums.FieldNames;
import by.itacademy.exceptions.enums.UserStatus;
import by.itacademy.exceptions.enums.action.Actions;
import by.itacademy.exceptions.enums.action.EssenceType;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.*;
import by.itacademy.flatSearch.userService.aop.Audited;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.auth.api.IPasswordEncoders;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final ICRUDUserDao userDao;
    private final IPasswordEncoders passwordEncoder;


    public UserService(ICRUDUserDao userDao, IPasswordEncoders passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    @Audited(action = Actions.ADD_USER, essenceType = EssenceType.USER)
    public void addByAdmin(User user) {
        save(user);
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
                if (constraintViolationException.getMessage().contains(FieldNames.MAIL.getField())) {
                    throw new DuplicateEntityException(ErrorMessages.ALREADY_REGISTERED.getMessage());
                }
            }
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    @Audited(action = Actions.GET_USERS, essenceType = EssenceType.USER)
    public UserPageDTO getPage(Pageable pageable) {
        Page<User> usersPage = getUsers(pageable);
        checkIfUsersPageIsEmpty(usersPage);

        return buildPageOfUserDTO(usersPage);
    }

    @Override
    @Transactional
    @Audited(action = Actions.UPDATE_USER, essenceType = EssenceType.USER)
    public void update(UserUpdateDTO updateDTO, UserCreateDTO createDTO) {
        User user = get(updateDTO.getUuid());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMail(createDTO.getMail());
        user.setFio(createDTO.getFio());
        user.setStatus(createDTO.getStatus());
        user.setRole(createDTO.getRole());

        LocalDateTime lastUserUpdate = user.getDtUpdate().truncatedTo(ChronoUnit.MILLIS);

        if (!updateDTO.getDtUpdate().equals(lastUserUpdate)) {
            throw new UserLockException(ErrorMessages.LOCK_ERROR.getMessage());
        }
        addByAdmin(user);
    }

    @Override
    @Transactional
    public void updateField(UserStatus status, String mail) {
        userDao.updateUserStatusByEmail(status, mail);
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

    private @NotNull Page<User> getUsers(Pageable pageable) {
        try {
            return userDao.findAll(pageable);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private void checkIfUsersPageIsEmpty(Page<User> usersPage) {
        if (usersPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private UserPageDTO buildPageOfUserDTO(Page<User> usersPage) {
        UserPageDTO pageOfUserDTO = new UserPageDTO();

        pageOfUserDTO.setSize(usersPage.getSize());
        pageOfUserDTO.setNumber(usersPage.getNumber());
        pageOfUserDTO.setTotalPages(usersPage.getTotalPages());
        pageOfUserDTO.setTotalElements(usersPage.getTotalElements());
        pageOfUserDTO.setNumberOfElements(usersPage.getNumberOfElements());
        pageOfUserDTO.setFirst(usersPage.isFirst());
        pageOfUserDTO.setLast(usersPage.isLast());

        List<UserDTO> usersDTO = EntityDTOMapper.INSTANCE
                .userListToUserDTOList(usersPage.getContent());
        pageOfUserDTO.setContent(usersDTO);

        return pageOfUserDTO;
    }
}
