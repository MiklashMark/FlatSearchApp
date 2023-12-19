package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String field;
    private String message;
}
