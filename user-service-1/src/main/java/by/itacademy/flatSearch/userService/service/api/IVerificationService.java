package by.itacademy.flatSearch.userService.service.api;

import by.itacademy.flatSearch.userService.core.dto.VerificationDTO;
import org.springframework.stereotype.Service;

public interface IVerificationService {
    void verify(VerificationDTO verificationDTO);
}
