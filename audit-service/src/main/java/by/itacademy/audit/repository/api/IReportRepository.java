package by.itacademy.audit.repository.api;

import by.itacademy.audit.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface IReportRepository extends JpaRepository<Report, UUID> {
}
