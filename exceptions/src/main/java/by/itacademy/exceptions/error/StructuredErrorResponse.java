package by.itacademy.exceptions.error;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StructuredErrorResponse {
    private String logRef;
    private List<ErrorDetail> errors = new ArrayList<>();


    public void addError(ErrorDetail error) {
        errors.add(error);
    }
}
