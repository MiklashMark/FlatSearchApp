package by.itacademy.flatSearch.userService.controller.converter.api;

import by.itacademy.flatSearch.userService.core.dto.PageOfUserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserUpdateDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserConverter {
    void save(UserCreateDTO createDTO);
    UserDTO get(UUID uuid);

    void update(UserUpdateDTO updateDTO);
    PageOfUserDTO getPage(Pageable pageable);
}
