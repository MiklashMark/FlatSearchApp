package by.itacademy.flatSearch.userService.controller.converter.api;


import by.itacademy.exceptions.dto.UserCreateDTO;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.UserUpdateDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserConverter {
    void save(UserCreateDTO createDTO);
    UserDTO get(UUID uuid);

    void update(UserUpdateDTO updateDTO, UserCreateDTO createDTO);
    UserPageDTO getPage(Pageable pageable);
}
