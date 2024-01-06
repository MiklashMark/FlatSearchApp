package by.itacademy.flatSearch.userService.dao.api;

import by.itacademy.flatSearch.userService.core.dto.UserInfDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICRUDUserDao extends CrudRepository<User, UUID> {
    Optional<User> findByMail(String mail);
    Optional<UserInfDTO> findByUuid(UUID uuid);
    boolean existsByMail(String mail);
    boolean existsByFio(String fio);
}
