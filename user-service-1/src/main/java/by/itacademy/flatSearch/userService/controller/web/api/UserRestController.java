package by.itacademy.flatSearch.userService.controller.web.api;

import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.flatSearch.userService.service.api.IVerificationService;
import by.itacademy.user.controller.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final IUserRegistrationService registrationService;
    private final IVerificationService verificationService;
    public UserRestController(IUserRegistrationService userRegistrationService, IVerificationService verificationService) {
        this.registrationService = userRegistrationService;
        this.verificationService = verificationService;
    }
        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        }
        @PostMapping("/registration")
        public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistration) {
            registrationService.register(userRegistration);
            return ResponseEntity.status(201).body(Messages.REGISTERED_SUCCESSFULLY.getMessage());
        }
        @GetMapping("/verification")
        public ResponseEntity<String> verify(VerificationDTO verificationDTO) {
                verificationService.verify(verificationDTO);
                return ResponseEntity.ok(Messages.VERIFIED_SUCCESSFULLY.getMessage());
        }
}