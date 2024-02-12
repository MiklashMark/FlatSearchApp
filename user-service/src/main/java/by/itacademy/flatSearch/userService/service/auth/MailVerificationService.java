package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.dao.api.IMailQueueDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;
import by.itacademy.flatSearch.userService.service.auth.api.IMailVerificationService;
import by.itacademy.flatSearch.userService.service.gate.ISendMailService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@Log4j2
public class MailVerificationService implements IMailVerificationService {
    private final IMailQueueDao verificationDao;
    private final ISendMailService sendMailService;
    private final JwtTokenHandler jwtTokenHandler;


    public MailVerificationService(IMailQueueDao verificationDao,
                                   ISendMailService sendMailService, JwtTokenHandler jwtTokenHandler) {
        this.verificationDao = verificationDao;
        this.sendMailService = sendMailService;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    @Transactional
    public void create(User user) {
        VerificationMailEntity verificationUser = new VerificationMailEntity();
        String generatedCode = generateCode();
        verificationUser.setCode(generatedCode);
        verificationUser.setMail(user.getMail());

        VerificationMailDTO verificationMailDTO = new VerificationMailDTO();
        verificationMailDTO.setCode(generatedCode);
        verificationMailDTO.setMail(user.getMail());

        String token = "Bearer " + jwtTokenHandler.
                generateAccessToken(new UserDTO().setRole(UserRole.SYSTEM));
        try {
            verificationDao.save(verificationUser);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        } finally {
           try {
               sendMailService.send(token, verificationMailDTO);
           } catch (FeignException e) {
               log.error("CONNECTION TO SENDER FAILED");
           }
        }
    }

    private String generateCode() {
        return Integer.toString(new Random().nextInt(9000) + 1000);
    }


}
