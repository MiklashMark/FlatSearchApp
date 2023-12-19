package by.itacademy.flatSearch.userService.dao.api;


import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IVerificationDao extends JpaRepository<VerificationEntity, UUID> {

    Optional<VerificationEntity> findFirstBySendFlagFalse();
    Optional<VerificationEntity> findByMail(String mail);
}
