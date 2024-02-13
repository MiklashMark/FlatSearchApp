package by.itacademy.flatSearch.mailService.service.api;


import by.itacademy.exceptions.dto.VerificationMailDTO;

public interface IMessageService {
    void createMessage(VerificationMailDTO mailDTO);
}
