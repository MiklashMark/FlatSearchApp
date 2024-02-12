package by.itacademy.flatSearch.userService.aop;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import by.itacademy.exceptions.dto.audit.AuditUserDTO;
import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.action.EssenceType;
import by.itacademy.flatSearch.userService.aop.api.IAuditedAspect;
import by.itacademy.flatSearch.userService.core.utils.EntityDTOMapper;
import by.itacademy.flatSearch.userService.core.utils.JwtTokenHandler;
import by.itacademy.flatSearch.userService.service.auth.api.IAuthService;
import by.itacademy.flatSearch.userService.service.gate.ISendAuditService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
@Log4j2
public class AuditedAspect implements IAuditedAspect {

    private final IAuthService authService;
    private final JwtTokenHandler jwtTokenHandler;
    private final ISendAuditService sendAuditService;

    public AuditedAspect(IAuthService authService,
                         JwtTokenHandler jwtTokenHandler,
                         ISendAuditService sendAuditService) {
        this.authService = authService;
        this.jwtTokenHandler = jwtTokenHandler;
        this.sendAuditService = sendAuditService;
    }

    @Override
    @Around("@annotation(Audited)")
    public Object getAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Audited annotation = ((MethodSignature) joinPoint.getSignature())
                .getMethod().getAnnotation(Audited.class);
        Object object;
        AuditDTO auditDTO = new AuditDTO();

        try {
            object = joinPoint.proceed();
            auditDTO.setText("SUCCESS: ");
        } catch (RuntimeException e) {
            auditDTO.setText("FAILED: ");
            throw e;
        } finally {
            auditDTO = buildAudit(annotation, auditDTO);
            String token = "Bearer " + jwtTokenHandler
                    .generateAccessToken(new UserDTO().setRole(UserRole.SYSTEM));
            try {
                sendAuditService.send(token, auditDTO);
            } catch (FeignException ex) {
                log.error("CONNECTION TO AUDIT FAILED");
            }
        }
        return object;
    }


    public AuditDTO buildAudit(Audited annotation, AuditDTO auditDTO) {
        return createUserAuditDTO(annotation, auditDTO);
    }


    private AuditDTO createUserAuditDTO(Audited audited, AuditDTO auditDTO) {
        return createAuditDTO(audited, getUserFromSecurityContext(), auditDTO);
    }


    private AuditDTO createAuditDTO(Audited audited, AuditUserDTO auditUserDTO, AuditDTO auditDTO) {

        return auditDTO
                .setDtCreate(LocalDateTime.now())
                .setUuid(UUID.randomUUID())
                .setUser(auditUserDTO)
                .setEssenceType(EssenceType.USER)
                .setText(auditDTO.getText() + audited.action().getValue())
                .setEssenceId(auditUserDTO.getUuid().toString());
    }

    private AuditUserDTO getUserFromSecurityContext() {
        return EntityDTOMapper.INSTANCE.userEntityToAuditUserDTO(authService.getFromContext());
    }

}
