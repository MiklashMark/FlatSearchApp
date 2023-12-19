package by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.VerificationEntity;

public class ConvertEntityDTO {
    public static VerificationDTO convertEntityToDTO (VerificationEntity entity){
        VerificationDTO dto = new VerificationDTO();
        dto.setCode(entity.getCode());
        dto.setMail(entity.getMail());
        return dto;
    }
}
