package by.itacademy.flatSearch.userService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDTO {
    private String mail;
    private String fio;
    private String password;
}
