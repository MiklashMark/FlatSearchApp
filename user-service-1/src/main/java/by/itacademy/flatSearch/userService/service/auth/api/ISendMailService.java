package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.core.dto.VerificationMailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "my-mail-service", url = "http://localhost:8081/api/v1/mail")
public interface ISendMailService {
    @RequestMapping(method = RequestMethod.POST, value = "/send")
    void sendMailMessage(VerificationMailDTO firstWithFlagFalse);
}
