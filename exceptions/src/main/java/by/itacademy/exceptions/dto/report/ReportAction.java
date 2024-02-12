package by.itacademy.exceptions.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
public enum ReportAction {
    LOADED("LOADED"),
    PROGRESS("PROGRESS"),
    ERROR("ERROR"),
    DONE("DONE"),
    REPORT_STATUS("REPORT STATUS:");

    private final String value;
}
