package by.itacademy.flatSearch.userService.controller;

import by.itacademy.exceptions.dto.UserCreateDTO;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.UserUpdateDTO;
import by.itacademy.exceptions.enums.messages.Messages;
import by.itacademy.flatSearch.userService.controller.converter.api.IUserConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final IUserConverter userConverter;


    public UserRestController(IUserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> add(@RequestBody UserCreateDTO userCreateDTO){
        userConverter.save(userCreateDTO);
        return ResponseEntity.status(201).body(Messages.USER_SUCCESSFULLY_ADDED.getMessage());
    }

    @GetMapping("/{uuid}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDTO> get(@PathVariable UUID uuid) {
        return new ResponseEntity<>(userConverter.get(uuid), HttpStatus.OK);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserPageDTO> getPage(
            @PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(userConverter.getPage(pageable), HttpStatus.OK);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> update (@PathVariable UUID uuid,
                                          @PathVariable(name = "dt_update") LocalDateTime dataUpdate,
                                          @RequestBody UserCreateDTO userCreateDTO) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(uuid, dataUpdate);
        userConverter.update(userUpdateDTO, userCreateDTO);
        return ResponseEntity.ok().body(Messages.SUCCESSFULLY_UPDATED.getMessage());
    }

}
