package by.itacademy.flatSearch.mailService.service;

import by.itacademy.flatSearch.mailService.core.dto.MailDTO;
import by.itacademy.flatSearch.mailService.service.api.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Verification code: " + mailDTO.getCode() + "or tap to the link http://localhost:8080/api/v1/users/verification?code=" + mailDTO.getCode() + "&mail=" + mailDTO.getMail());
        message.setTo(mailDTO.getMail());
        try {
            mailSender.send(message);
        } catch () {

        }

    }
}
