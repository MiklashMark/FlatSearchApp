package by.itacademy.flatSearch.userService.core.dto;

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
