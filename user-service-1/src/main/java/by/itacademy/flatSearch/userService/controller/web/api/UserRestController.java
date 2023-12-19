package by.itacademy.flatSearch.userService.controller.web.api;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.service.api.IUserRegistrationService;
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
        public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistration) {
            userRegistrationService.register(userRegistration);
            return ResponseEntity.ok("User registered successfully");
        }

        public ResponseEntity<String> verification(VerificationDTO verificationDTO) {
            return null;
        }
}