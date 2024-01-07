package by.itacademy.flatSearch.userService.core.utils;

import by.itacademy.flatSearch.userService.core.dto.UserCreateDTO;
import by.itacademy.flatSearch.userService.core.dto.UserDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
  EntityDTOMapper instance = Mappers.getMapper(EntityDTOMapper.class);

    VerificationDTO verificationEntityToDTO(VerificationEntity entity);
    VerificationEntity verificationDTOToEntity(VerificationDTO dto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    UserDTO userEntityToUserDTO(User user);
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dataCreate", ignore = true)
    @Mapping(target = "dataUpdate", ignore = true)
    @Mapping(target = "password", ignore = true)
    User convertUserDTOToUserEntity(UserDTO user);

    default User userRegistrationDTOToUserEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setMail(userRegistrationDTO.getMail());
        user.setFio(userRegistrationDTO.getFio());
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setRole(UserRole.USER);
        return user;
    }
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dataCreate", ignore = true)
    @Mapping(target = "dataUpdate", ignore = true)
    User convertUserCreateDTOToUserEntity(UserCreateDTO user);
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dataCreate", ignore = true)
    @Mapping(target = "dataUpdate", ignore = true)
    User convertUserEntityToUserCreateDTO(UserDTO user);

}
