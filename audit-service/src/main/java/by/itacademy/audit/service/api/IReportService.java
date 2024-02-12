package by.itacademy.audit.service.api;

import by.itacademy.audit.repository.entity.Report;
import by.itacademy.exceptions.dto.report.ReportPageDTO;
import by.itacademy.exceptions.dto.report.ReportType;
import by.itacademy.exceptions.dto.report.UserActionAuditParamDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IReportService {
    void add(ReportType reportType, UserActionAuditParamDTO actionAuditParamDTO);
    ReportPageDTO getPage(Pageable pageable);
    void  getFile(Report report);

    InputStreamResource download(UUID uuid);

    boolean exist(UUID uuid);
}
