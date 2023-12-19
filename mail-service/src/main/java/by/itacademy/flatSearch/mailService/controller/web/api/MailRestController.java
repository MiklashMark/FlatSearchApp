package by.itacademy.flatSearch.mailService.controller.web.api;

import by.itacademy.flatSearch.mailService.core.dto.MailDTO;
import by.itacademy.flatSearch.mailService.service.api.IMailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class MailRestController {

    private IMailService mailService;

    public MailRestController(IMailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody MailDTO mailDTO) {
        mailService.send(mailDTO);
        return ResponseEntity.ok("Successfully");
    }
}
