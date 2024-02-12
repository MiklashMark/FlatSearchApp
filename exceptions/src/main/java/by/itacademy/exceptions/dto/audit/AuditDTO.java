package by.itacademy.exceptions.dto.audit;

import by.itacademy.exceptions.enums.action.EssenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuditDTO {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private AuditUserDTO user;
    private String text;
    private EssenceType essenceType;
    private String essenceId;
}
