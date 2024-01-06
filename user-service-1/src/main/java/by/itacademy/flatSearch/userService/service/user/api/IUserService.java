package by.itacademy.flatSearch.userService.service.user.api;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserInfDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    void save(UserDTO userDTO);
    List<User> getUsers();
    UserInfDTO get(UUID id);
    User get(String mail);

    UserDTO update(UUID uuid, long dtUpdate);
}
