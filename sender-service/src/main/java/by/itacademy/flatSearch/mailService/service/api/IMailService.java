package by.itacademy.flatSearch.mailService.service.api;


import by.itacademy.exceptions.dto.VerificationMailDTO;

public interface IMailService {
    void send(VerificationMailDTO mailDTO);
}
