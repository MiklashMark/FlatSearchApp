package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import by.itacademy.flatSearch.userService.service.auth.api.ISendMailService;
import by.itacademy.flatSearch.userService.service.auth.api.IMailQueueService;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MailQueueService implements IMailQueueService {
    private IVerificationDao verificationDao;
    private ISendMailService sendMailService;
    public MailQueueService(IVerificationDao verificationDao,
                            ISendMailService sendMailService) {
        this.verificationDao = verificationDao;
        this.sendMailService = sendMailService;
    }

    @Override
    public void addInMailQueue(User user) {
        VerificationEntity verificationUser = new VerificationEntity();
        verificationUser.setUuid(UUID.randomUUID());
        verificationUser.setCode(generateCode());
        verificationUser.setMail(user.getMail());
        verificationUser.setSended(false);

        try {
            verificationDao.save(verificationUser);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage(), e);
        }
    }


    @Scheduled(fixedRate = 20000)
    private void sendMailMessage() {
        Optional<VerificationEntity> verificationEntity = verificationDao.findFirstBySendFlagFalse();

        if (verificationEntity.isPresent()) {
            VerificationDTO verificationDTO = EntityDTOMapper.instance.verificationEntityToDTO(verificationEntity.get());
            sendMailService.sendMailMessage(verificationDTO);
            verificationEntity.get().setSended(true);

            try {
                verificationDao.save(verificationEntity.get());
            } catch (DataAccessException e) {
                throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage(), e);
            }

        }
    }
    @Scheduled(cron = "0 50 23 * * ?")
    private void deleteMailMessage() {
        verificationDao.deleteAll();
    }

    private String generateCode() {
        Random random = new Random();
        return Integer.toString(random.nextInt(9000) + 1000);
    }
}
