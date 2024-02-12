package by.itacademy.flatSearch.userService.service.auth.holder;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public User getUser(){
        UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return EntityDTOMapper.INSTANCE.userDTOToUserEntity(userDTO);
    }

}
