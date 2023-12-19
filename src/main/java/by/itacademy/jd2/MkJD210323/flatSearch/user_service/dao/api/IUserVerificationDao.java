package by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.api;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface IUserVerificationDao extends JpaRepository<VerificationEntity, UUID> {

    Optional<VerificationEntity> findFirstBySendFlagFalse();
}
