package by.itacademy.exceptions.dto;

import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;
}
