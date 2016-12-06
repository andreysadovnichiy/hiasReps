package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.two.Query21;
import com.solveast.rreps.model.queries.two.Query22;
import com.solveast.rreps.model.queries.two.Query23;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Андрей on 29.11.2016.
 */
@Service
public class ReportTwoBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport report = ForReport.TWO;
    private final WorkbookFactory wbFactory;

    public ReportTwoBuilder() {
        wbFactory = new WorkbookFactory(report);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        Integer clientsNumber = (Integer) data.get("clientsNumber");
        Set<Long> clientsList = (Set<Long>) data.get("clientsList");

        List<Query21> rawData21 = (List<Query21>) data.get("rawData21");
        List<Query22> rawData22 = (List<Query22>) data.get("rawData22");
        List<Query23> rawData23 = (List<Query23>) data.get("rawData23");

        Sheet sheet = wbFactory.getSheet(report);

        Row row = sheet.createRow(4);
        Cell cell = row.createCell(1);
        cell.setCellValue(clientsNumber);

        int initRow = 8;
        int initCell = 1;
        CellStyle templStyle = sheet.getRow(initRow).getCell(1).getCellStyle();

        int i=0;
        for (Long client : clientsList) {
            row = sheet.getRow(initRow + i);
            if (row == null) {
                row = sheet.createRow(initRow + i);
            }

            cell = row.createCell(initCell);
            cell.setCellValue(client);
            cell.setCellStyle(templStyle);
            i++;
        }

        fillQuery21Table(sheet, rawData21);
        fillQuery22Table(sheet, rawData22);
        fillQuery23Table(sheet, rawData23);
    }

    private void fillQuery22Table(Sheet sheet, List<Query22> rawData22) {
        int initRow = 8;
        int initCell = 11;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();

        Row row;
        Cell cell;
        Date date;
        int i = 0;

        for (Query22 item : rawData22) {

            row = sheet.getRow(initRow + i);
            if (row == null) {
                row = sheet.createRow(initRow + i);
            }

            cell = row.createCell(initCell);
            cell.setCellValue(item.getClientId());
            cell.setCellStyle(templStyle1);

            if (item.getRegisterTime() != null) {
                cell = row.createCell(initCell + 1);
                cell.setCellStyle(templStyle4);
                date = Date.from(item.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            i++;
        }
    }

    private void fillQuery23Table(Sheet sheet, List<Query23> rawData23) {
        int initRow = 8;
        int initCell = 14;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();

        Row row;
        Cell cell;
        Date date;
        int i = 0;

        for (Query23 item : rawData23) {

            row = sheet.getRow(initRow + i);
            if (row == null) {
                row = sheet.createRow(initRow + i);
            }

            cell = row.createCell(initCell);
            cell.setCellValue(item.getClientId());
            cell.setCellStyle(templStyle1);

            if (item.getRegisterTime() != null) {
                cell = row.createCell(initCell + 1);
                cell.setCellStyle(templStyle4);
                date = Date.from(item.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            i++;
        }
    }

    private void fillQuery21Table(Sheet sheet, List<Query21> rawData21) {
        int initRow = 8;
        int initCell = 3;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(initRow).getCell(initCell + 2).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(initRow).getCell(initCell + 3).getCellStyle();
        CellStyle templStyle7 = sheet.getRow(initRow).getCell(initCell + 4).getCellStyle();
        CellStyle templStyle8 = sheet.getRow(initRow).getCell(initCell + 5).getCellStyle();
        CellStyle templStyle9 = sheet.getRow(initRow).getCell(initCell + 6).getCellStyle();

        Row row;
        Cell cell;
        Date date;
        int i = 0;

        for (Query21 item : rawData21) {

            row = sheet.getRow(initRow + i);
            if (row == null) {
                row = sheet.createRow(initRow + i);
            }

            cell = row.createCell(initCell);
            cell.setCellValue(item.getClientId());
            cell.setCellStyle(templStyle1);

            cell = row.createCell(initCell + 1);
            cell.setCellValue(item.getActionType());
            cell.setCellStyle(templStyle2);

            cell = row.createCell(initCell + 2);
            cell.setCellValue(item.getActionStateCd());
            cell.setCellStyle(templStyle3);

            if (item.getRealTimeStart() != null) {
                cell = row.createCell(initCell + 3);
                cell.setCellStyle(templStyle6);
                date = Date.from(item.getRealTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getRealTimeStop() != null) {
                cell = row.createCell(initCell + 4);
                cell.setCellStyle(templStyle7);
                date = Date.from(item.getRealTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStart() != null) {
                cell = row.createCell(initCell + 5);
                cell.setCellStyle(templStyle8);
                date = Date.from(item.getScheduledTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStop() != null) {
                cell = row.createCell(initCell + 6);
                cell.setCellStyle(templStyle9);
                date = Date.from(item.getScheduledTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }
            i++;
        }
    }
}