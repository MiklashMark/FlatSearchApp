package by.itacademy.flatSearch.userService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfDTO {
    private UUID uuid;
    private long dt_create;
    private long dt_update;
    private String mail;
    private String fio;
    private String role;
    private String status;
}
