package by.itacademy.flatSearch.userService.controller;

import by.itacademy.exceptions.dto.UserLoginDTO;
import by.itacademy.exceptions.dto.UserRegistrationDTO;
import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.exceptions.enums.messages.Messages;
import by.itacademy.flatSearch.userService.controller.converter.api.IAuthConverter;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class AuthRestController {
    private final IAuthConverter authConverter;

    public AuthRestController(IAuthConverter authConverter) {
        this.authConverter = authConverter;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return authConverter.login(userLoginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok().body(authConverter.get().toString());
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistration) {
        authConverter.save(userRegistration);
        return ResponseEntity.status(201).body(Messages.REGISTERED_SUCCESSFULLY.getMessage());
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verify(VerificationMailDTO verificationMailDTO) {
        authConverter.verify(verificationMailDTO);
        return ResponseEntity.ok(Messages.VERIFIED_SUCCESSFULLY.getMessage());
    }
}