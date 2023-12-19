package by.itacademy.jd2.MkJD210323.flatSearch.user_service.service;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.ConvertEntityDTO;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.dto.VerificationDTO;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.enums.ErrorMessages;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.core.exception.InternalServerException;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.api.IUserVerificationDao;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.User;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.VerificationEntity;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api.ISendlerService;
import by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api.IUserVerificationService;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VerificationUserService implements IUserVerificationService {
    private IUserVerificationDao verificationDao;
    private ISendlerService mailSendlerService;

    public VerificationUserService(IUserVerificationDao verificationDao,
                                   ISendlerService sendlerService) {
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
