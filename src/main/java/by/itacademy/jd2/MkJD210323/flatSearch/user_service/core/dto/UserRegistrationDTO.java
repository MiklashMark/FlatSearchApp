package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDTO {
    private String mail;
    private String fio;
    private String password;
}
