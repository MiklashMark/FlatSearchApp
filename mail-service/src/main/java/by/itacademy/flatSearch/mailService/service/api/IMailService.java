package by.itacademy.flatSearch.mailService.service.api;


import by.itacademy.flatSearch.mailService.core.dto.MailDTO;

public interface IMailService {
    void send(MailDTO mailDTO);
}
