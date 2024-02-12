package by.itacademy.exceptions.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserActionAuditParamDTO {
    private List<String> usersId;
    private LocalDate from;
    private LocalDate to;
}
