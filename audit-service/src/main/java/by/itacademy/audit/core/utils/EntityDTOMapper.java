package by.itacademy.audit.core.utils;

import by.itacademy.audit.repository.entity.Audit;
import by.itacademy.audit.repository.entity.Report;
import by.itacademy.audit.repository.entity.User;
import by.itacademy.exceptions.dto.audit.AuditDTO;
import by.itacademy.exceptions.dto.audit.AuditUserDTO;
import by.itacademy.exceptions.dto.report.ReportAction;
import by.itacademy.exceptions.dto.report.ReportDTO;
import by.itacademy.exceptions.dto.report.UserActionAuditParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
    EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

    Audit auditDTOToAuditEntity(AuditDTO auditUserDTO);

    AuditDTO auditEntityToAuditDTO(Audit auditUserDTO);
    ReportDTO reportEntityToReportDTO(Report report);

    User auditUserDTOToUserEntity(AuditUserDTO auditUserDTO);


    default Report createReport(UserActionAuditParamDTO actionAuditParamDTO, ReportAction reportAction) {
        Report report = new Report();
        report.setDtCreate(LocalDateTime.now());
        report.setDtUpdate(LocalDateTime.now());
        report.setStatus(reportAction);
        report.setDescription("FROM " + actionAuditParamDTO.getFrom()
                + " TO " + actionAuditParamDTO.getFrom());
        report.setUsersId(actionAuditParamDTO.getUsersId());
        report.setFrom(actionAuditParamDTO.getFrom());
        report.setTo(actionAuditParamDTO.getTo());

        return report;
    }

    default List<ReportDTO> reportListToReportDTOList(List<Report> reports) {
        return reports.stream().map(INSTANCE::reportEntityToReportDTO).toList();
    }

    default List<AuditDTO> auditListToAuditDTOList(List<Audit> audits) {
        return audits.stream().map(INSTANCE::auditEntityToAuditDTO).toList();
    }
}