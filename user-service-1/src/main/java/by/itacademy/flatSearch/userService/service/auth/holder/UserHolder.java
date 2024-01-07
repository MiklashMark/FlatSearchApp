package by.itacademy.flatSearch.userService.service.auth.holder;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDTO getUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
