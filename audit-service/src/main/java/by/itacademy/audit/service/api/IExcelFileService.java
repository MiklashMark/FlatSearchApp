package by.itacademy.audit.service.api;

import by.itacademy.audit.repository.entity.Audit;

import java.util.List;

public interface IExcelFileService {
    void create(List<Audit> audits);

    void setName(String string);
    byte[] loadFile(String fileName);
}
