package by.itacademy.flatSearch.userService.controller;

import by.itacademy.flatSearch.userService.controller.converter.api.IAuthConverter;
import by.itacademy.flatSearch.userService.core.dto.UserLoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationMailDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.Messages;
import by.itacademy.flatSearch.userService.service.auth.api.IVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AuthRestController {
    private final IVerificationService verificationService;
    private final IAuthConverter authConverter;

    public AuthRestController(IVerificationService verificationService, IAuthConverter authConverter) {
        this.verificationService = verificationService;
        this.authConverter = authConverter;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return authConverter.login(userLoginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getPersonalInfo() {
        return ResponseEntity.ok().body(authConverter.get().toString());
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistration) {
        authConverter.save(userRegistration);
        return ResponseEntity.status(201).body(Messages.REGISTERED_SUCCESSFULLY.getMessage());
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verify(@RequestBody VerificationMailDTO verificationMailDTO) {
        authConverter.verify(verificationMailDTO);
        return ResponseEntity.ok(Messages.VERIFIED_SUCCESSFULLY.getMessage());
    }
}