package by.itacademy.audit.service;

import by.itacademy.audit.core.utils.EntityDTOMapper;
import by.itacademy.audit.repository.api.IAuditRepository;
import by.itacademy.audit.repository.api.ICrudUserRepository;
import by.itacademy.audit.repository.entity.Audit;
import by.itacademy.audit.repository.entity.User;
import by.itacademy.audit.service.api.IAuditService;
import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.UserPageDTO;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import by.itacademy.exceptions.dto.audit.AuditPageDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final ICrudUserRepository userRepository;

    public AuditService(IAuditRepository auditRepository, ICrudUserRepository userRepository) {
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void add(AuditDTO auditDTO) {
        Audit audit = EntityDTOMapper.INSTANCE.auditDTOToAuditEntity(auditDTO);
        auditRepository.saveAndFlush(audit);
    }

    @Override
    public AuditPageDTO getPage(Pageable pageable) {
        Page<Audit> auditPage = getAudits(pageable);
        checkIfAuditPageIsEmpty(auditPage);

        return buildAuditPageDTO(auditPage);
    }

    @Override
    public AuditDTO get(UUID uuid) {
        return EntityDTOMapper.INSTANCE.auditEntityToAuditDTO(auditRepository.
                findById(uuid).orElseThrow(() ->
                        new EntityNotFoundException(ErrorMessages.AUDIT_NOT_FOUND.getMessage())));

    }

    @Override
    public List<Audit> get(List<String> usersID, LocalDateTime from, LocalDateTime to) {
        List<UUID> uuids = usersID.stream().map(UUID::fromString).toList();
        List<Audit> audits =  auditRepository.findAuditsForUsersBetweenDates(uuids, from, to);
        return audits;
    }

    private @NotNull Page<Audit> getAudits(Pageable pageable) {
        try {
            return auditRepository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private void checkIfAuditPageIsEmpty(Page<Audit> auditPage) {
        if (auditPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private AuditPageDTO buildAuditPageDTO(Page<Audit> auditPage) {
        AuditPageDTO auditPageDTO = new AuditPageDTO();

        auditPageDTO.setSize(auditPage.getSize());
        auditPageDTO.setNumber(auditPage.getNumber());
        auditPageDTO.setTotalPages(auditPage.getTotalPages());
        auditPageDTO.setTotalElements(auditPage.getTotalElements());
        auditPageDTO.setNumberOfElements(auditPage.getNumberOfElements());
        auditPageDTO.setFirst(auditPage.isFirst());
        auditPageDTO.setLast(auditPage.isLast());

        List<AuditDTO> usersDTO = EntityDTOMapper.INSTANCE
                .auditListToAuditDTOList(auditPage.getContent());
        auditPageDTO.setContent(usersDTO);

        return auditPageDTO;
    }
}
