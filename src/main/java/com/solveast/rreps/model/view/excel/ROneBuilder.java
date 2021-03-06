package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.one.Intro;
import com.solveast.rreps.model.queries.one.IntroData;
import com.solveast.rreps.model.queries.one.Report1;
import com.solveast.rreps.model.queries.one.ReportAdapter;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 29.10.2016.
 */
@Service
public class ROneBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport reportPattern = ForReport.ONE;

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return new WorkbookFactory(ForReport.ONE).getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        List<Family> families = (List<Family>) data.get("inputData");
        ReportAdapter reportAdapter = (ReportAdapter) data.get("report");
        Map<String, Report1> reports = reportAdapter.getReportMap();

        String title = (String) data.get("title");
        Integer unknown = (Integer) data.get("unknown");
        Integer total = (Integer) data.get("total");

        Sheet sheet = workbook.getSheet("Запрос 1");
        fillTitle(title, sheet);

        Row row;
        Cell cell;
        int rowShift = 6;
        int i = 0;
        int line = rowShift + i;

        CellStyle templStyle1 = sheet.getRow(rowShift).getCell(1).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(rowShift).getCell(2).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(rowShift).getCell(3).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(rowShift).getCell(4).getCellStyle();
        CellStyle templStyle5 = sheet.getRow(rowShift).getCell(5).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(rowShift).getCell(6).getCellStyle();

        for (Map.Entry<String, Report1> item : reports.entrySet()) {
            Report1 report = item.getValue();
            line = rowShift + i;

            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            cell = row.createCell(1);
            cell.setCellValue(i + 1);
            cell.setCellStyle(templStyle1);

            cell = row.createCell(2);
            cell.setCellValue(report.getIso3166_3());
            cell.setCellStyle(templStyle2);

            cell = row.createCell(3);
            cell.setCellValue(report.getMale());
            cell.setCellStyle(templStyle3);

            cell = row.createCell(4);
            cell.setCellValue(report.getFemale());
            cell.setCellStyle(templStyle4);

            cell = row.createCell(5);
            cell.setCellValue(report.getBoys());
            cell.setCellStyle(templStyle5);

            cell = row.createCell(6);
            cell.setCellValue(report.getGirls());
            cell.setCellStyle(templStyle6);

            i++;
        }
        fillTotal(line + 2, unknown, total, sheet);

        Date date;
        rowShift = 3;
        i = 0;
        line = rowShift + i;

        for (Family family : families) {
            fillPersonRow(family.getClient(), family.getFamilyPersonNumber(), sheet, line);
            line = rowShift + ++i;

            for (Person persona : family.getFamily()) {
                fillPersonRow(persona, 0, sheet, line);
                line = rowShift + ++i;
            }
        }
    }

    private void fillTitle(String title, Sheet sheet) {
        Row row = sheet.getRow(1);
        Cell rowCellMaleValue = row.getCell(1);
        rowCellMaleValue.setCellValue(title);
    }

    private void fillTotal(int line, int unproc, int total, Sheet sheet) {
        Row row = sheet.getRow(line);
        if (row == null)
            row = sheet.createRow(line);

        Cell cell = row.createCell(3);
        cell.setCellValue("Total:");
        cell = row.createCell(5);
        cell.setCellValue("Unproc!:");

        cell = row.createCell(4);
        cell.setCellValue(total);
        cell = row.createCell(6);
        cell.setCellValue(unproc);
    }

    private void fillPersonRow(Person person, int familyMembers, Sheet sheet, int line) {
        CellStyle templStyle1 = sheet.getRow(3).getCell(8).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(3).getCell(9).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(3).getCell(10).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(3).getCell(11).getCellStyle();
        CellStyle templStyle5 = sheet.getRow(3).getCell(12).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(3).getCell(13).getCellStyle();
        CellStyle templStyle7 = sheet.getRow(3).getCell(14).getCellStyle();
        CellStyle templStyle8 = sheet.getRow(3).getCell(15).getCellStyle();
        CellStyle templStyle9 = sheet.getRow(3).getCell(16).getCellStyle();
        CellStyle templStyle10 = sheet.getRow(3).getCell(17).getCellStyle();

        Row row;
        Cell cell;
        Date date;

        row = sheet.getRow(line);
        if (row == null) {
            row = sheet.createRow(line);
        }

        cell = row.createCell(8);
        cell.setCellValue(person.getClientId());
        cell.setCellStyle(templStyle1);

        cell = row.createCell(9);
        if (person.getApplicantId() != null && person.getApplicantId() > 0)
            cell.setCellValue(person.getApplicantId());
        else
            cell.setCellValue(0);
        cell.setCellStyle(templStyle2);

        cell = row.createCell(10);
        cell.setCellValue(familyMembers);
        cell.setCellStyle(templStyle3);

        if (person.getRegisterTime() != null) {
            cell = row.createCell(11);
            cell.setCellStyle(templStyle4);
            date = Date.from(person.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(date);
        }

        if (person.getUnhcrDate() != null) {
            cell = row.createCell(12);
            cell.setCellStyle(templStyle5);
            date = Date.from(person.getUnhcrDate().atZone(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(date);
        }

        if (person.getSexCd() != null) {
            cell = row.createCell(13);
            cell.setCellValue(person.getSexCd());
            cell.setCellStyle(templStyle6);
        }

        if (person.getIso3166_3() != null) {
            cell = row.createCell(14);
            cell.setCellValue(person.getIso3166_3());
            cell.setCellStyle(templStyle7);
        }

        cell = row.createCell(15);
        cell.setCellValue(person.getUnRelationshipCd());
        cell.setCellStyle(templStyle8);

        if (person.getBirthDate() != null) {
            cell = row.createCell(16);
            cell.setCellStyle(templStyle9);
            date = Date.from(person.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(date);
        }

        cell = row.createCell(17);
        cell.setCellValue(person.getAge());
        cell.setCellStyle(templStyle10);
    }
}