package by.itacademy.audit.repository.api;

import by.itacademy.audit.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICrudUserRepository extends JpaRepository<User, UUID> {
    User findByMail(String mail);
}
