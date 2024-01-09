package by.itacademy.flatSearch.userService.dao.api;

import by.itacademy.flatSearch.userService.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICRUDUserDao extends JpaRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
    Optional<User> findByMail(String mail);
    Optional<User> findByUuid(UUID uuid);
}
