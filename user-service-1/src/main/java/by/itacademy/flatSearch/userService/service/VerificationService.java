package by.itacademy.flatSearch.userService.service;

import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import by.itacademy.flatSearch.userService.core.enums.ErrorsTypes;
import by.itacademy.flatSearch.userService.core.enums.Messages;
import by.itacademy.flatSearch.userService.core.error.ErrorResponse;
import by.itacademy.flatSearch.userService.core.exception.ValidationException;
import by.itacademy.flatSearch.userService.dao.api.IVerificationDao;
import by.itacademy.flatSearch.userService.dao.entity.VerificationEntity;
import by.itacademy.flatSearch.userService.service.api.IVerificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService implements IVerificationService {
    private IVerificationDao verificationDao;

    public VerificationService(IVerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    @Override
    public void verify(VerificationDTO verificationDTO) {
        Optional<VerificationEntity> storedEntity = verificationDao.findByMail(verificationDTO.getMail());

        if (storedEntity.isEmpty()) {
            throw new ValidationException(new ErrorResponse(ErrorsTypes.ERROR.getMessage(),
                    Messages.INCORRECT_VERIFICATION_CODE.getMessage()));
        }

    }
}
