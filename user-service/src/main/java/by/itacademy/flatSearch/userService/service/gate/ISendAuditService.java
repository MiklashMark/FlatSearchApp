package by.itacademy.flatSearch.userService.service.gate;

import by.itacademy.exceptions.dto.VerificationMailDTO;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "audit-service",url = "${audit.service.url}")
public interface ISendAuditService {
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    void send(@RequestHeader String Authorization, @RequestBody AuditDTO auditDTO);
}
