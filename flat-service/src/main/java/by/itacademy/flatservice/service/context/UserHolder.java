package by.itacademy.flatservice.service.context;

import by.itacademy.exceptions.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    public UserDTO getUserInfo() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
