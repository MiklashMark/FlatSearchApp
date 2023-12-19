package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.error;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StructuredErrorResponse {
    private String logRef;
    private List<ErrorDetail> errors = new ArrayList<>();


    public void addError(ErrorDetail error) {
        errors.add(error);
    }
}
