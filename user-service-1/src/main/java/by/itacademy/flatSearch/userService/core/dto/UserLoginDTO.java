package by.itacademy.flatSearch.userService.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    private String mail;
    private String password;
}