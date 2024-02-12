package by.itacademy.audit.repository.entity;

import by.itacademy.exceptions.dto.report.ReportAction;
import by.itacademy.exceptions.dto.report.ReportType;
import jakarta.persistence.*;
import org.hibernate.annotations.OptimisticLock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "audit", name = "report", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usersId", "from_", "to_"})
})
public class Report {
    @GeneratedValue
    @Id
    @Column(unique = true)
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Enumerated(EnumType.STRING)
    private ReportAction status;
    @Enumerated(EnumType.STRING)
    private ReportType reportType;
    private String description;

    private List<String> usersId;

    @Column(name = "from_")
    private LocalDate from;
    @Column(name = "to_")
    private LocalDate to;

    public Report() {
    }

    public Report(UUID uuid, LocalDateTime dtCreate,
                  LocalDateTime dtUpdate, ReportAction status,
                  ReportType reportType, String description,
                  List<String> usersId, LocalDate from,
                  LocalDate to) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.reportType = reportType;
        this.description = description;
        this.usersId = usersId;
        this.from = from;
        this.to = to;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ReportAction getStatus() {
        return status;
    }

    public void setStatus(ReportAction status) {
        this.status = status;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<String> usersId) {
        this.usersId = usersId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(uuid, report.uuid) && Objects.equals(dtCreate, report.dtCreate) && Objects.equals(dtUpdate, report.dtUpdate) && status == report.status && reportType == report.reportType && Objects.equals(description, report.description) && Objects.equals(usersId, report.usersId) && Objects.equals(from, report.from) && Objects.equals(to, report.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, status, reportType, description, usersId, from, to);
    }

    @Override
    public String toString() {
        return "Report{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", status=" + status +
                ", reportType=" + reportType +
                ", description='" + description + '\'' +
                ", usersId=" + usersId +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}