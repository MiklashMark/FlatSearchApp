package by.itacademy.flatSearch.userService.service.gate;
import by.itacademy.exceptions.dto.VerificationMailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "mail-service", url = "http://localhost:8081/api/v1/mail")
public interface ISendMailService {
    @RequestMapping(method = RequestMethod.POST, value = "/send")
    void send(@RequestHeader String Authorization, VerificationMailDTO firstWithFlagFalse);
}
