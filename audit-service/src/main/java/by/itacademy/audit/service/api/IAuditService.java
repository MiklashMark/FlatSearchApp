package by.itacademy.audit.service.api;

import by.itacademy.audit.repository.entity.Audit;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import by.itacademy.exceptions.dto.audit.AuditPageDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAuditService {
    void add(AuditDTO auditDTO);

    AuditPageDTO getPage(Pageable pageable);

    AuditDTO get(UUID uuid);
    List<Audit> get(List<String> usersID, LocalDateTime from, LocalDateTime to);
}
