package by.itacademy.jd2.MkJD210323.flatSearch.userService.controller.api;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @PostMapping("/registration")
    public ResponseEntity<String> registrate(UserRegistration userRegistration)
}
