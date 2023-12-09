package by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistration {
    private String mail;
    private String fio;
    private String password;

}
