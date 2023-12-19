package by.itacademy.flatSearch.userService.dao.api;

import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IRegistrationDao extends JpaRepository<User, UUID> {

    boolean existsByMail(String mail);
    boolean existsByFio(String fio);
}
