package by.itacademy.flatSearch.userService.service.user.api;

import by.itacademy.flatSearch.userService.core.dto.PageOfUserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService {
    void save(User user);
    PageOfUserDTO getPage(Pageable pageable);
    User get(UUID id);
    User get(String mail);
    User get(UserLoginDTO loginDTO);
    void update(UUID uuid, long dtUpdate);
}
