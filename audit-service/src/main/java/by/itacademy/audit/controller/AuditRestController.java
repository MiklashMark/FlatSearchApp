package by.itacademy.audit.controller;

import by.itacademy.audit.service.api.IAuditService;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import by.itacademy.exceptions.dto.audit.AuditPageDTO;
import by.itacademy.exceptions.dto.audit.AuditUserDTO;
import by.itacademy.exceptions.enums.messages.Messages;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditRestController {

    private final IAuditService auditService;

    public AuditRestController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AuditDTO> get(@PathVariable UUID uuid) {
        return new ResponseEntity<>(auditService.get(uuid), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<AuditPageDTO> getPage(
            @PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(auditService.getPage(pageable), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody AuditDTO auditDto) {
        auditService.add(auditDto);
        return ResponseEntity.ok(Messages.AUDIT_SUCCESSFULLY_ADDED.getMessage());
    }

}
