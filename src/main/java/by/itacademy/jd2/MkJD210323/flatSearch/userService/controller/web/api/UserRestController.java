package by.itacademy.jd2.MkJD210323.flatSearch.userService.controller.web.api;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.dto.UserRegistration;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.service.api.IUserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final IUserRegistrationService userRegistrationService;

    public UserRestController(IUserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }


        @PostMapping("/registration")
        public ResponseEntity<String> register(@RequestBody UserRegistration userRegistration) {
            userRegistrationService.register(userRegistration);
            return ResponseEntity.ok("User registered successfully");
        }
}
