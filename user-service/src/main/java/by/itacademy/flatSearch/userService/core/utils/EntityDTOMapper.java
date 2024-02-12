package by.itacademy.flatSearch.userService.core.utils;

import by.itacademy.exceptions.dto.*;
import by.itacademy.exceptions.dto.audit.AuditUserDTO;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
  EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);
  TimeConverter timeConverter = new TimeConverter();


    VerificationMailDTO verificationEntityToDTO(VerificationMailEntity entity);
    VerificationMailEntity verificationDTOToEntity(VerificationMailDTO dto);



    @Mapping(target = "password", ignore = true)
    default UserDTO userEntityToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(user.getUuid());
        userDTO.setFio(user.getFio());
        userDTO.setMail(user.getMail());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());
        userDTO.setDataCreate(timeConverter.convert(user.getDtCreate()));
        userDTO.setDataUpdate(timeConverter.convert(user.getDtUpdate()));

        return userDTO;
    }

    @Mapping(target = "password", ignore = true)
    default User userDTOToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUuid(userDTO.getUuid());
        user.setMail(userDTO.getMail());
        user.setFio(userDTO.getFio());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setDtCreate(timeConverter.convert(String.valueOf(userDTO.getDataCreate())));
        user.setDtUpdate(timeConverter.convert(String.valueOf(userDTO.getDataUpdate())));
        return user;
    }

    default User userRegistrationDTOToUserEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setMail(userRegistrationDTO.getMail());
        user.setFio(userRegistrationDTO.getFio());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setDtUpdate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        user.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        return user;
    }
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    User userCreateDTOToUserEntity(UserCreateDTO user);
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    User userEntityToUserCreateDTO(UserDTO user);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserCreateDTO userRegistrationDTOToUserCreateDTO(UserRegistrationDTO userDTO);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "fio", ignore = true)
    UserCreateDTO userLoginDTOToUserCreateDTO(UserLoginDTO userDTO);


    @Mapping(target = "role")
    AuditUserDTO userEntityToAuditUserDTO(User user);

    default List<UserDTO> userListToUserDTOList(List<User> users) {
        return users.stream().map(INSTANCE :: userEntityToUserDTO).toList();
    }


    AuditUserDTO userLoginDTOToAuditUserDTO(UserLoginDTO userLoginDTO);
}
