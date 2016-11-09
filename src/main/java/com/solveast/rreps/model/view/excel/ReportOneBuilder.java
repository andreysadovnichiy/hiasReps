package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.schemas.Query1;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Created by Андрей on 29.10.2016.
 */
@Service
public class ReportOneBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        FileInputStream inputStream = null;
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("patterns.xlsx").getFile());

            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<Long, Query1> model = (Map<Long, Query1>) map.get("model");

        Sheet sheet = workbook.getSheet("Запрос 1");

        Row row = null;
        Cell cell8 = null;
        Cell cell9 = null;
        Cell cell10 = null;
        Cell cell11 = null;
        Cell cell12 = null;
        Cell cell13 = null;
        Cell cell14 = null;

        int rowShift = 3;
        int i = 0;
        Date date;

        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));

        for (Map.Entry<Long, Query1> entry : model.entrySet()) {
            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell8 = row.createCell(8);
            cell8.setCellValue(entry.getValue().getClientId());

            cell9 = row.createCell(9);
            cell9.setCellValue(entry.getValue().getApplicantId());

            cell10 = row.createCell(10);
            cell10.setCellStyle(dateStyle);
            date = Date.from(entry.getValue().getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
            cell10.setCellValue(date);

            if (entry.getValue().getUnhcrDate() != null) {
                cell11 = row.createCell(11);
                cell11.setCellStyle(dateStyle);
                date = Date.from(entry.getValue().getUnhcrDate().atZone(ZoneId.systemDefault()).toInstant());
                cell11.setCellValue(date);
            }

            cell12 = row.createCell(12);
            cell12.setCellValue(entry.getValue().getSexCd());

            cell13 = row.createCell(13);
            cell13.setCellValue(entry.getValue().getIso3166_3());

            cell14 = row.createCell(14);
            cell14.setCellStyle(dateStyle);
            cell14.setCellValue(entry.getValue().getBirthDate());

            i++;
        }
    }
}

/*

Sheet sheet = workbook.createSheet("New fucking sheet");

        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Book Title");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Author");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("ISBN");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Published Date");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Price");
        header.getCell(4).setCellStyle(style);


 */
