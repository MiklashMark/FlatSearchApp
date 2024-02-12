package by.itacademy.exceptions.dto.audit;

import by.itacademy.exceptions.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditUserDTO {
    private UUID uuid;
    private String mail;
    private String fio;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
