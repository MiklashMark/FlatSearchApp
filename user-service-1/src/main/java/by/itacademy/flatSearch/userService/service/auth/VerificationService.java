package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.messages.ErrorMessages;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import by.itacademy.flatSearch.userService.service.auth.api.IVerificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VerificationService implements IVerificationService {
    private IVerificationDao verificationDao;
    private ICRUDUserDao crudUserDao;

    public VerificationService(IVerificationDao verificationDao, ICRUDUserDao crudUserDao) {
        this.verificationDao = verificationDao;
        this.crudUserDao = crudUserDao;
    }

    @Override
    @Transactional
    public void verify(VerificationDTO verificationDTO) {
        VerificationEntity storedEntity = verificationDao.findByMailAndCode(verificationDTO.getMail(),
                verificationDTO.getCode()).orElseThrow(() ->
                new ValidationException(ErrorMessages.INCORRECT_VERIFICATION_CODE.getMessage()));

        User user = crudUserDao.findByMail(storedEntity.getMail())
                .orElseThrow(() -> new ValidationException(ErrorMessages.USER_NOT_FOUND.getMessage()));

        user.setStatus(UserStatus.ACTIVATED);
        crudUserDao.save(user);
        verificationDao.delete(storedEntity);     // Delete user im mail Queue table
    }
}
