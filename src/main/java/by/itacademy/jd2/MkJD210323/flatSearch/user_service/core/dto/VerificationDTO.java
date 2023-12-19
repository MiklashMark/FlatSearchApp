package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationDTO {
    private String code;
    private String mail;
}
