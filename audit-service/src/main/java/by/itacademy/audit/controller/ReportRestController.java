package by.itacademy.audit.controller;

import by.itacademy.audit.service.api.IReportService;
import by.itacademy.exceptions.dto.report.ReportAction;
import by.itacademy.exceptions.dto.report.ReportPageDTO;
import by.itacademy.exceptions.dto.report.ReportType;
import by.itacademy.exceptions.dto.report.UserActionAuditParamDTO;
import jakarta.annotation.security.RolesAllowed;
import org.apache.coyote.Response;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/report")
public class ReportRestController {
    private final IReportService reportService;

    public ReportRestController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@PathVariable String type,
                                      @RequestBody UserActionAuditParamDTO actionAuditParamDTO) {
        reportService.add(ReportType.valueOf(type), actionAuditParamDTO);
        return ResponseEntity.status(201).body(
                ReportAction.REPORT_STATUS.getValue() + ReportAction.LOADED.getValue());
    }

    @GetMapping()
    public ResponseEntity<ReportPageDTO> getPage(@PageableDefault(size = 20)
                                                 Pageable pageable) {
        return new ResponseEntity<>(reportService.getPage(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}/export")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID uuid) {
        Resource resource = reportService.download(uuid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + uuid.toString() + ".xlsx\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @RequestMapping(value = "/{uuid}/export", method = RequestMethod.HEAD)
    public ResponseEntity<String> check(@PathVariable UUID uuid) {
        reportService.exist(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
