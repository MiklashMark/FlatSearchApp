package by.itacademy.flatSearch.mailService.controller;

import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.flatSearch.mailService.service.api.IMailQueueService;
import by.itacademy.flatSearch.mailService.service.api.IMailService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class MailRestController {
    private final IMailQueueService mailQueueService;

    public MailRestController(IMailQueueService mailQueueService) {
        this.mailQueueService = mailQueueService;
    }

    @PostMapping("/send")
    @Secured("ROLE_SYSTEM")
    public void send(@RequestBody VerificationMailDTO mailDTO) {
        mailQueueService.addInMailQueue(mailDTO);
    }
}
