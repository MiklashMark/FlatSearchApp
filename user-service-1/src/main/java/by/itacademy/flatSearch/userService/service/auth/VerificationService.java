package by.itacademy.flatSearch.userService.service.auth;

import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.ErrorsTypes;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.ICRUDUserDao;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import by.itacademy.flatSearch.userService.service.auth.api.IVerificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService implements IVerificationService {
    private IVerificationDao verificationDao;
    private ICRUDUserDao crudUserDao;

    public VerificationService(IVerificationDao verificationDao, ICRUDUserDao crudUserDao) {
        this.verificationDao = verificationDao;
        this.crudUserDao = crudUserDao;
    }

    @Override
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> storedEntity = verificationDao.findByMailAndCode(verificationDTO.getMail(),
                verificationDTO.getCode());

        if (storedEntity.isEmpty()) {
            throw new ValidationException(new ErrorResponse(ErrorsTypes.ERROR.getMessage(),
                    Messages.INCORRECT_VERIFICATION_CODE.getMessage()));
        }

        Optional<User> user = crudUserDao.findByMail(storedEntity.get().getMail());

        if (user.isPresent()) {
            user.get().setStatus(UserStatus.ACTIVATED);
            crudUserDao.save(user.get());
            verificationDao.delete(storedEntity.get());     // Delete user im mail Queue table
        }
    }
}
