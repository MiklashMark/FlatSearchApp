package by.itacademy.flatSearch.mailService.service;

import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatSearch.mailService.dao.api.IMailQueueDao;
import by.itacademy.flatSearch.mailService.dao.entity.VerificationMailEntity;
import by.itacademy.flatSearch.mailService.service.api.IMailQueueService;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class MailQueueService implements IMailQueueService {
    private final JavaMailSender mailSender;
    private final IMailQueueDao mailQueueDao;


    public MailQueueService(JavaMailSender mailSender, IMailQueueDao mailQueueDao) {
        this.mailSender = mailSender;
        this.mailQueueDao = mailQueueDao;
    }

    @Override
    @Transactional
    public void addInMailQueue(VerificationMailDTO verificationMailDTO) {
        VerificationMailEntity verificationMail = new VerificationMailEntity();
        verificationMail.setUuid(UUID.randomUUID());
        verificationMail.setCode(verificationMailDTO.getCode());
        verificationMail.setMail(verificationMailDTO.getMail());
        verificationMail.setSended(false);

        try {
            mailQueueDao.save(verificationMail);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }


    @Scheduled(fixedRate = 20000)
    @Transactional
    protected void sendMailMessage() {
        if (mailQueueDao.findFirstBySendFlagFalse().isPresent()) {
            VerificationMailEntity verificationMailEntity = mailQueueDao.findFirstBySendFlagFalse()
                    .orElseThrow(() ->
                            new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage()));

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(verificationMailEntity.getMail());
            message.setText("Verification code: " + verificationMailEntity.getCode()
                    + "or tap to the link http://localhost:8080/api/v1/users/verification?code="
                    + verificationMailEntity.getCode() + "&mail=" + verificationMailEntity.getMail());

            mailSender.send(message);
            VerificationMailDTO verificationMailDTO = new VerificationMailDTO();
            verificationMailDTO.setMail(verificationMailEntity.getMail());
            verificationMailDTO.setCode(verificationMailEntity.getCode());
            verificationMailEntity.setSended(true);

            try {
                mailQueueDao.save(verificationMailEntity);
            } catch (DataAccessException e) {
                throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
            }
        }
    }

    @Scheduled(cron = "0 50 23 * * ?")
    @Transactional
    protected void deleteMailMessage() {
        mailQueueDao.deleteAll();
    }

}