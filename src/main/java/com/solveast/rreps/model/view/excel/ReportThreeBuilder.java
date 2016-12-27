package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.three.Query3;
import com.solveast.rreps.model.queries.three.Report3;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 28.11.2016.
 */
@Service
public class ReportThreeBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport report = ForReport.THREE;
    private final WorkbookFactory wbFactory;

    public ReportThreeBuilder() {
        wbFactory = new WorkbookFactory(report);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> model = (Map<String, Object>) map.get("model");

        List<Report3> data = (List<Report3>) model.get("report");
        List<Query3> rawData = (List<Query3>) model.get("rawData");
        List<Family> families = (List<Family>) model.get("families");
        String title = (String) model.get("title");


        Sheet sheet = wbFactory.getSheet(report);
        fillTitle(title, sheet);

        Row row;
        Cell cell;
        int rowShift = 7;

        CellStyle templStyle = sheet.getRow(rowShift).getCell(2).getCellStyle();

        int i = 0;
        for (Report3 item : data) {

            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell = row.createCell(1);
            cell.setCellValue(i + 1);
            cell.setCellStyle(templStyle);

            cell = row.createCell(2);
            cell.setCellValue(item.getIso3166_3());
            cell.setCellStyle(templStyle);

            cell = row.createCell(3);
            cell.setCellValue(item.getUnhcrNum());
            cell.setCellStyle(templStyle);

            cell = row.createCell(4);
            cell.setCellValue(item.getFio());
            cell.setCellStyle(templStyle);

            cell = row.createCell(5);
            if (item.getFamilyMembers() != null)
                cell.setCellValue(item.getFamilyMembers());
            cell.setCellStyle(templStyle);

            cell = row.createCell(6);
            cell.setCellValue(item.getFileStatusName());
            cell.setCellStyle(templStyle);

            i++;
        }

        rowShift = 8;
        i = 0;
        for (Query3 item : rawData) {
            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell = row.createCell(8);
            cell.setCellValue(item.getClientId());
            cell.setCellStyle(templStyle);

            cell = row.createCell(9);
            cell.setCellValue(item.getIso3166_3());
            cell.setCellStyle(templStyle);

            cell = row.createCell(10);
            cell.setCellValue(item.getUnhcrNum());
            cell.setCellStyle(templStyle);

            cell = row.createCell(11);
            cell.setCellValue(item.getFileStatusName());
            cell.setCellStyle(templStyle);

            cell = row.createCell(12);
            cell.setCellValue(item.getFamilyName());
            cell.setCellStyle(templStyle);

            cell = row.createCell(13);
            cell.setCellValue(item.getName());
            cell.setCellStyle(templStyle);

            i++;
        }

        rowShift = 8;
        i = 0;
        for (Family item : families) {
            row = sheet.getRow(rowShift + i);
            if (row == null)
                row = sheet.createRow(rowShift + i);
            fillPerson(item.getClient(), item.getClient().getIso3166_3(), row, templStyle);
            i++;

            for (Person member : item.getFamily()) {
                row = sheet.getRow(rowShift + i);
                if (row == null)
                    row = sheet.createRow(rowShift + i);
                fillPerson(member, item.getClient().getIso3166_3(), row, templStyle);
                i++;
            }
        }
    }

    private void fillPerson(Person item, String iso3166_3, Row row, CellStyle templStyle) {
        Cell cell = row.createCell(15);
        cell.setCellValue(item.getClientId());
        cell.setCellStyle(templStyle);

        cell = row.createCell(16);
        cell.setCellStyle(templStyle);
        if (item.getApplicantId() != null)
            cell.setCellValue(item.getApplicantId());
        else
            cell.setCellValue(0);

        if (iso3166_3 != null) {
            cell = row.createCell(17);
            cell.setCellValue(iso3166_3);
            cell.setCellStyle(templStyle);
        }
    }

    private void fillTitle(String title, Sheet sheet) {
        Row row = sheet.getRow(2);
        Cell rowCellMaleValue = row.getCell(1);
        rowCellMaleValue.setCellValue(title);
    }

}