package by.itacademy.flatSearch.userService.controller.web.api;

import by.itacademy.flatSearch.userService.core.dto.LoginDTO;
import by.itacademy.flatSearch.userService.core.dto.UserRegistrationDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.service.api.ILoginService;
import by.itacademy.flatSearch.userService.service.api.IPersonalInformationService;
import by.itacademy.flatSearch.userService.service.api.IUserRegistrationService;
import by.itacademy.flatSearch.userService.service.api.IVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final IUserRegistrationService registrationService;
    private final IVerificationService verificationService;
    private final ILoginService loginService;
    private final IPersonalInformationService personalInformationService;

    public UserRestController(IUserRegistrationService registrationService,
                              IVerificationService verificationService,
                              ILoginService loginService,
                              IPersonalInformationService personalInformationService) {
        this.registrationService = registrationService;
        this.verificationService = verificationService;
        this.loginService = loginService;
        this.personalInformationService = personalInformationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getPersonalInfo() {
        return ResponseEntity.ok().body(personalInformationService.get().toString());
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