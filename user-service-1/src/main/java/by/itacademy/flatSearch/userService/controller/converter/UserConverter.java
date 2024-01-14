package by.itacademy.flatSearch.userService.controller.converter;

import by.itacademy.flatSearch.userService.controller.converter.api.IUserConverter;
import by.itacademy.flatSearch.userService.core.dto.PageOfUserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserUpdateDTO;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.user.api.IUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter implements IUserConverter {
    private final IUserService userService;

    public UserConverter(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void save(UserCreateDTO createDTO) {
        User user = EntityDTOMapper.INSTANCE.convertUserCreateDTOToUserEntity(createDTO);
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());
        user.setRole(createDTO.getRole());
        user.setStatus(createDTO.getStatus());
        userService.save(user);
    }

    @Override
    public UserDTO get(UUID uuid) {
       User user =  userService.get(uuid);
       return EntityDTOMapper.INSTANCE.userEntityToUserDTO(user);
    }

    @Override
    public void update(UserUpdateDTO updateDTO) {

    }

    @Override
    public PageOfUserDTO getPage(Pageable pageable) {
       return userService.getPage(pageable);
    }


}
