package by.itacademy.flatSearch.userService.service.user.api;

import by.itacademy.exceptions.dto.UserCreateDTO;
import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.UserUpdateDTO;
import by.itacademy.exceptions.enums.UserStatus;
import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService {
    void addByAdmin(User user);

    void save(User user);

    UserPageDTO getPage(Pageable pageable);

    User get(UUID id);

    User get(String mail);

    User get(UserLoginDTO loginDTO);

    void update(UserUpdateDTO updateDTO, UserCreateDTO createDTO);
    void updateField(UserStatus status, String mail);
}
