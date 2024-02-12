package by.itacademy.audit.service;

import by.itacademy.audit.core.utils.EntityDTOMapper;
import by.itacademy.audit.repository.api.IReportRepository;
import by.itacademy.audit.repository.entity.Report;
import by.itacademy.audit.service.api.IAuditService;
import by.itacademy.audit.service.api.IExcelFileService;
import by.itacademy.audit.service.api.IReportService;
import by.itacademy.exceptions.dto.report.*;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.DuplicateEntityException;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.exceptions.exception.custom_exceptions.FileExistenceException;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Log4j2
public class ReportService implements IReportService {
    private final IReportRepository reportRepository;
    private final IAuditService auditService;
    private final IExcelFileService fileService;


    public ReportService(IReportRepository reportRepository, IAuditService auditService,
                         IExcelFileService fileService) {
        this.reportRepository = reportRepository;
        this.auditService = auditService;
        this.fileService = fileService;
    }

    @Override
    @Transactional
    public void add(ReportType reportType, UserActionAuditParamDTO actionAuditParamDTO) {
        try {
            Report report = EntityDTOMapper.INSTANCE
                    .createReport(actionAuditParamDTO, ReportAction.LOADED);
            report.setReportType(reportType);
            reportRepository.saveAndFlush(report);
            log.info(ReportAction.REPORT_STATUS.getValue()
                    + ReportAction.LOADED.getValue());
            getFile(report);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getMessage().contains("report_users_id_from__to__key")) { // constraint
                    throw new DuplicateEntityException(ErrorMessages.REPORT_ALREADY_CREATED.getMessage());
                }
            }
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public ReportPageDTO getPage(Pageable pageable) {
        Page<Report> reportPage = getReports(pageable);
        checkIfAuditPageIsEmpty(reportPage);

        return buildAuditPageDTO(reportPage);
    }

    @Transactional
    @Override
    public synchronized void getFile(Report report) { // add ExecutService with @Async
        if (report.getStatus().equals(ReportAction.LOADED)) {
            report.setStatus(ReportAction.PROGRESS);
            reportRepository.saveAndFlush(report);
        }

        if (report.getStatus().equals(ReportAction.PROGRESS)) {
            fileService.setName(report.getUuid().toString());
            fileService.create(auditService.get(report.getUsersId(),
                    report.getFrom().atStartOfDay(),
                    report.getTo().atTime(LocalTime.MAX)));
            report.setStatus(ReportAction.DONE);
            reportRepository.saveAndFlush(report);
        }
    }

    @Override
    public InputStreamResource download(UUID uuid) {
        exist(uuid);
        return new InputStreamResource(new ByteArrayInputStream(fileService.loadFile(String.valueOf(uuid))));
    }

    @Override
    public boolean exist(UUID uuid) {
        Report report = reportRepository.findById(uuid)
                .orElseThrow(() -> new FileExistenceException(ErrorMessages.RESOURCE_NOT_FOUND.getMessage()));
        return report.getStatus().equals(ReportAction.DONE);
    }

    private @NotNull Page<Report> getReports(Pageable pageable) {
        try {
            return reportRepository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private void checkIfAuditPageIsEmpty(Page<Report> reportPage) {
        if (reportPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private ReportPageDTO buildAuditPageDTO(Page<Report> reportPage) {
        ReportPageDTO reportPageDTO = new ReportPageDTO();

        reportPageDTO.setSize(reportPage.getSize());
        reportPageDTO.setNumber(reportPage.getNumber());
        reportPageDTO.setTotalPages(reportPage.getTotalPages());
        reportPageDTO.setTotalElements(reportPage.getTotalElements());
        reportPageDTO.setNumberOfElements(reportPage.getNumberOfElements());
        reportPageDTO.setFirst(reportPage.isFirst());
        reportPageDTO.setLast(reportPage.isLast());

        List<ReportDTO> reportDTOS = EntityDTOMapper.INSTANCE
                .reportListToReportDTOList(reportPage.getContent());
        reportPageDTO.setContent(reportDTOS);

        return reportPageDTO;
    }
}
