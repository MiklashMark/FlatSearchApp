package by.itacademy.flatSearch.userService.controller;

import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.Messages;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.auth.api.IVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AuthRestController {
    private final IVerificationService verificationService;
    private final IAuthService authService;

    public AuthRestController(IVerificationService verificationService, IAuthService authService) {
        this.verificationService = verificationService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.login(userLoginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getPersonalInfo() {
        return ResponseEntity.ok().body(authService.get().toString());
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistration) {
        authService.save(userRegistration);
        return ResponseEntity.status(201).body(Messages.REGISTERED_SUCCESSFULLY.getMessage());
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verify(VerificationDTO verificationDTO) {
        verificationService.verify(verificationDTO);
        return ResponseEntity.ok(Messages.VERIFIED_SUCCESSFULLY.getMessage());
    }
}