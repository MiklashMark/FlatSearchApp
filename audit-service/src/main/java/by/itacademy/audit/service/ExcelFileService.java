package by.itacademy.audit.service;

import by.itacademy.audit.repository.entity.Audit;
import by.itacademy.audit.service.api.IExcelFileService;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.FileCreationException;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Log4j2
@Service
public class ExcelFileService implements IExcelFileService {
    private String fileName;
    @Value("${app.reports.dir}")
    private String DIR;

    private final String EXTENSION = ".xlsx";
    public ExcelFileService() {
    }

    @Override
    public byte[] loadFile(String fileName)  {
        try{
            return Files.readAllBytes(Path.of(DIR + File.separator + fileName + EXTENSION));
        } catch (IOException e){
            throw new FileCreationException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }
    @Override
    public void create(List<Audit> audits) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("AuditData");
        Row headerRow = sheet.createRow(0);
        String[] columns = {"uuid", "dtCreate", "user_uuid",
                "text", "essenceType", "id", "mail", "fio", "role"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        int rowNum = 1;
        for (Audit audit : audits){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(audit.getUuid().toString());
            row.createCell(1).setCellValue(audit.getDtCreate().toString());
            row.createCell(2).setCellValue(audit.getUser().getUuid().toString());
            row.createCell(3).setCellValue(audit.getText());
            row.createCell(4).setCellValue(audit.getEssenceType().toString());
            row.createCell(5).setCellValue(audit.getEssenceId());
            row.createCell(6).setCellValue(audit.getUser().getMail());
            row.createCell(7).setCellValue(audit.getUser().getFio());
            row.createCell(8).setCellValue(audit.getUser().getRole().toString());
        }


        try (FileOutputStream fileOut = new FileOutputStream(DIR + File.separator + fileName + EXTENSION)) {
            workbook.write(fileOut);
            log.info("Файл успешно создан с именем - " + fileName);
        } catch (IOException e) {
            log.error("Ошибка в создании файла");
            throw new FileCreationException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public void setName(String name) {
        this.fileName = name;
    }
}
