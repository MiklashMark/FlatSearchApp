package by.itacademy.flatSearch.userService.dao.api;


import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface IMailQueueDao extends JpaRepository<VerificationMailEntity, UUID>{
    Optional<VerificationMailEntity> findByMailAndCode(String mail, String code);
    void delete(@NotNull VerificationMailEntity verificationMailEntity);
}
