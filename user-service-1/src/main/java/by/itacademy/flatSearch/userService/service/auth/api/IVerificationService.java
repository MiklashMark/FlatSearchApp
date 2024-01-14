package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.dao.entity.VerificationMailEntity;

public interface IVerificationService {
    void verify(VerificationMailEntity verificationMailEntity);
}

