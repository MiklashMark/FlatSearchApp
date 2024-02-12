package by.itacademy.exceptions.dto.report;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ReportDTO {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    @Enumerated(EnumType.STRING)
    private ReportAction status;
    @Enumerated(EnumType.STRING)
    private ReportType reportType;
    private String description;
    private List<String> usersId;
    private LocalDate from;
    private LocalDate to;
}
