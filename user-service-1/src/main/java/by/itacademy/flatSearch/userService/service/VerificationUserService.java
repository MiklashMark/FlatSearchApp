package by.itacademy.flatSearch.userService.service;

import by.itacademy.flatSearch.userService.core.dto.ConvertEntityDTO;
import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.ErrorMessages;
import by.itacademy.flatSearch.userService.core.exception.InternalServerException;
import by.itacademy.flatSearch.userService.dao.api.IUserVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import by.itacademy.flatSearch.userService.service.api.ISendMailService;
import by.itacademy.flatSearch.userService.service.api.IUserVerificationService;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VerificationUserService implements IUserVerificationService {
    private IUserVerificationDao verificationDao;
    private ISendMailService mailSendlerService;

    public VerificationUserService(IUserVerificationDao verificationDao,
                                   ISendMailService sendlerService) {
        this.verificationDao = verificationDao;
        this.mailSendlerService = sendlerService;
    }

    @Override
    public void verify(User user) {
        VerificationEntity verificationUser = new VerificationEntity();
        verificationUser.setUuid(UUID.randomUUID());
        verificationUser.setCode(generateCode());
        verificationUser.setMail(user.getMail());
        verificationUser.setSended(false);

        try {
            verificationDao.save(verificationUser);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
        sendMailMessage();

    }


    @Scheduled(fixedRate = 20000)
    public void sendMailMessage() {
        Optional<VerificationEntity> verificationEntity = verificationDao.findFirstBySendFlagFalse();

        if (verificationEntity.isPresent()) {
            VerificationDTO verificationDTO = ConvertEntityDTO.convertEntityToDTO(verificationEntity.get());
            mailSendlerService.sendMailMessage(verificationDTO);
            verificationEntity.get().setSended(true);
            verificationDao.save(verificationEntity.get());
        }
    }

    private String generateCode() {
        Random random = new Random();
        return Integer.toString(random.nextInt(9000) + 1000);
    }
}
