package by.itacademy.audit.repository.api;

import by.itacademy.audit.repository.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IAuditRepository extends JpaRepository<Audit, UUID>, PagingAndSortingRepository<Audit, UUID> {
    @Query("SELECT a FROM Audit a WHERE a.user.uuid IN :usersUuid AND a.dtCreate BETWEEN :startDate AND :endDate")
    List<Audit> findAuditsForUsersBetweenDates(
            @Param("usersUuid") List<UUID> usersUuid,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
