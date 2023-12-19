package by.itacademy.flatSearch.userService.core.dto;

import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;

public class ConvertEntityDTO {
    public static VerificationDTO convertEntityToDTO (VerificationEntity entity){
       VerificationDTO dto = new VerificationDTO();
        dto.setCode(entity.getCode());
        dto.setMail(entity.getMail());
        return dto;
    }
}
