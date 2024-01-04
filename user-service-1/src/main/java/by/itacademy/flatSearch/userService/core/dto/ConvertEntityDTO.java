package by.itacademy.flatSearch.userService.core.dto;

import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;

public class ConvertEntityDTO {
    public static VerificationDTO convertVerificationEntityToDTO(VerificationEntity entity){
       VerificationDTO dto = new VerificationDTO();

        dto.setCode(entity.getCode());
        dto.setMail(entity.getMail());

        return dto;
    }
    public static VerificationEntity convertVerificationDTOToEntity(VerificationDTO dto) {
        VerificationEntity entity = new VerificationEntity();

        entity.setCode(dto.getCode());
        entity.setMail(dto.getMail());

        return entity;
    }

    public static UserDTO convertUserEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUuid(user.getUuid());
        userDTO.setMail(user.getMail());
        userDTO.setFio(user.getFio());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

}
