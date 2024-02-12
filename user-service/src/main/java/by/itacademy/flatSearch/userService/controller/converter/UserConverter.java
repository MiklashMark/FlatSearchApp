package by.itacademy.flatSearch.userService.controller.converter;

import by.itacademy.exceptions.dto.UserCreateDTO;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.UserUpdateDTO;
import by.itacademy.flatSearch.userService.controller.converter.api.IUserConverter;

import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserConverter implements IUserConverter {
    private final IUserService userService;

    public UserConverter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void save(UserCreateDTO createDTO) {
        User user = EntityDTOMapper.INSTANCE.userCreateDTOToUserEntity(createDTO);
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(LocalDateTime.now());
        user.setRole(createDTO.getRole());
        user.setStatus(createDTO.getStatus());
        userService.addByAdmin(user);
    }

    @Override
    public UserDTO get(UUID uuid) {
        User user = userService.get(uuid);
        return EntityDTOMapper.INSTANCE.userEntityToUserDTO(user);
    }

    @Override
    public void update(UserUpdateDTO updateDTO, UserCreateDTO userCreateDTO) {
        userService.update(updateDTO, userCreateDTO);
    }

    @Override
    public UserPageDTO getPage(Pageable pageable) {
       return userService.getPage(pageable);
    }


}
