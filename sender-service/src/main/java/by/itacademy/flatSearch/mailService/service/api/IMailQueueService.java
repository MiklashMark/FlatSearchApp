package by.itacademy.flatSearch.mailService.service.api;


import by.itacademy.exceptions.dto.VerificationMailDTO;

public interface IMailQueueService {
    void addInMailQueue(VerificationMailDTO verificationMailDTO);
}
