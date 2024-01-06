package by.itacademy.flatSearch.userService.core.utils;

import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface EntityDTOMapper {

    public VerificationDTO convertVerificationEntityToDTO(VerificationEntity entity);
    public  VerificationEntity convertVerificationDTOToEntity(VerificationDTO dto);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "dataCreate", ignore = true)
    @Mapping(target = "dataUpdate", ignore = true)
    UserDTO convertUserEntityToDTO(User user);


    default User convertUserRegistrationDTOToUserEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUuid(UUID.randomUUID());
        user.setMail(userRegistrationDTO.getMail());
        user.setFio(userRegistrationDTO.getFio());
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setRole(UserRole.USER);
        user.setDataCreate(System.currentTimeMillis());
        user.setDataUpdate(System.currentTimeMillis());
        return user;
    }



}
