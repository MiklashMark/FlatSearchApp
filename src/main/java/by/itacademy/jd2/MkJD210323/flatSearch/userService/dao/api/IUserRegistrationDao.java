package by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.api;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserRegistrationDao extends JpaRepository<User, UUID> {

    boolean existsByMail(String mail);
    boolean existsByFio(String fio);
}