package by.itacademy.flatSearch.userService.core.utils;

import by.itacademy.flatSearch.userService.core.dto.*;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
  EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

    VerificationMailDTO verificationEntityToDTO(VerificationMailEntity entity);
    VerificationMailEntity verificationDTOToEntity(VerificationMailDTO dto);


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
        user.setPassword(userRegistrationDTO.getPassword());
        user.setDataUpdate(System.currentTimeMillis());
        user.setDataCreate(System.currentTimeMillis());
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

    default List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        return users.stream().map(INSTANCE :: userEntityToUserDTO).toList();
    }
}
