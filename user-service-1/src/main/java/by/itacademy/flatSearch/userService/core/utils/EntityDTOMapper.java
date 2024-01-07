package by.itacademy.flatSearch.userService.core.utils;

import by.itacademy.flatSearch.userService.core.dto.*;
import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
  EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

    VerificationDTO verificationEntityToDTO(VerificationEntity entity);
    VerificationEntity verificationDTOToEntity(VerificationDTO dto);


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

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserCreateDTO convertUserRegistrationDTOToUserCreateDTO(UserRegistrationDTO userDTO);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "fio", ignore = true)
    UserCreateDTO convertUserLoginDTOToUserCreateDTO(UserLoginDTO userDTO);
}
