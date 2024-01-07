package by.itacademy.flatSearch.userService.service.user.api;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void save(UserCreateDTO userCreateDTO);
    List<UserDTO> getUsers();
    UserDTO get(UUID id);
    UserDTO get(String mail);
    UserDTO get(UserLoginDTO loginDTO);
    void update(UUID uuid, long dtUpdate);
}
