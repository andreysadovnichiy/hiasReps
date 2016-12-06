package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.two.Query21;
import com.solveast.rreps.model.queries.two.Query22;
import com.solveast.rreps.model.queries.two.Query23;
import com.solveast.rreps.model.queries.two.Report4;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 28.11.2016.
 */
@Service
public class ReportFourBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport report = ForReport.FOUR;
    private final WorkbookFactory wbFactory;

    public ReportFourBuilder() {
        wbFactory = new WorkbookFactory(report);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        Report4 report4 = (Report4) data.get("processedData");
        List<Query21> rawData21 = (List<Query21>) data.get("rawData21");
        List<Query22> rawData22 = (List<Query22>) data.get("rawData22");
        List<Query23> rawData23 = (List<Query23>) data.get("rawData23");

        Sheet sheet = wbFactory.getSheet(report);

        int indexRow_0_4 = 8;
        int indexRow_5_17 = 9;
        int indexRow_18_59 = 10;
        int indexRow_60_ = 11;

        int indexCellMaleValue = 3;
        int indexCellFemaleValue = 5;
        int indexCellTotal = 7;

        fillReportTable(sheet, indexRow_0_4, indexCellMaleValue, report4.getMale_0_4(), indexCellFemaleValue, report4.getFemale_0_4(), indexCellTotal, report4.getTotal());
        fillReportTable(sheet, indexRow_5_17, indexCellMaleValue, report4.getMale_5_17(), indexCellFemaleValue, report4.getFemale_5_17(), indexCellTotal, report4.getTotal());
        fillReportTable(sheet, indexRow_18_59, indexCellMaleValue, report4.getMale_18_59(), indexCellFemaleValue, report4.getFemale_18_59(), indexCellTotal, report4.getTotal());
        fillReportTable(sheet, indexRow_60_, indexCellMaleValue, report4.getMale_60_(), indexCellFemaleValue, report4.getFemale_60_(), indexCellTotal, report4.getTotal());

        fillQuery21Table(sheet, rawData21);
        fillQuery22Table(sheet, rawData22);
        fillQuery23Table(sheet, rawData23);

    }

    private void fillQuery22Table(Sheet sheet, List<Query22> rawData22) {
        int initRow = 16;
        int initCell = 20;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(initRow).getCell(initCell + 2).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(initRow).getCell(initCell + 3).getCellStyle();

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

            cell = row.createCell(initCell + 1);
            cell.setCellValue(item.getSexCd());
            cell.setCellStyle(templStyle2);

            if (item.getBirthDate() != null) {
                cell = row.createCell(initCell + 2);
                cell.setCellStyle(templStyle3);
                date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getRegisterTime() != null) {
                cell = row.createCell(initCell + 3);
                cell.setCellStyle(templStyle4);
                date = Date.from(item.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            i++;
        }
    }

    private void fillQuery23Table(Sheet sheet, List<Query23> rawData23) {
        int initRow = 16;
        int initCell = 25;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(initRow).getCell(initCell + 2).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(initRow).getCell(initCell + 3).getCellStyle();

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

            cell = row.createCell(initCell + 1);
            cell.setCellValue(item.getSexCd());
            cell.setCellStyle(templStyle2);

            if (item.getBirthDate() != null) {
                cell = row.createCell(initCell + 2);
                cell.setCellStyle(templStyle3);
                date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getRegisterTime() != null) {
                cell = row.createCell(initCell + 3);
                cell.setCellStyle(templStyle4);
                date = Date.from(item.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            i++;
        }
    }

    private void fillQuery21Table(Sheet sheet, List<Query21> rawData21) {
        int initRow = 16;
        int initCell = 10;

        CellStyle templStyle1 = sheet.getRow(initRow).getCell(initCell).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(initRow).getCell(initCell + 1).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(initRow).getCell(initCell + 2).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(initRow).getCell(initCell + 3).getCellStyle();
        CellStyle templStyle5 = sheet.getRow(initRow).getCell(initCell + 4).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(initRow).getCell(initCell + 5).getCellStyle();
        CellStyle templStyle7 = sheet.getRow(initRow).getCell(initCell + 6).getCellStyle();
        CellStyle templStyle8 = sheet.getRow(initRow).getCell(initCell + 7).getCellStyle();
        CellStyle templStyle9 = sheet.getRow(initRow).getCell(initCell + 8).getCellStyle();

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

            if (item.getBirthDate() != null) {
                cell = row.createCell(initCell + 3);
                cell.setCellStyle(templStyle4);
                date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            cell = row.createCell(initCell + 4);
            cell.setCellValue(item.getSexCd());
            cell.setCellStyle(templStyle5);

            if (item.getRealTimeStart() != null) {
                cell = row.createCell(initCell + 5);
                cell.setCellStyle(templStyle6);
                date = Date.from(item.getRealTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getRealTimeStop() != null) {
                cell = row.createCell(initCell + 6);
                cell.setCellStyle(templStyle7);
                date = Date.from(item.getRealTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStart() != null) {
                cell = row.createCell(initCell + 7);
                cell.setCellStyle(templStyle8);
                date = Date.from(item.getScheduledTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStop() != null) {
                cell = row.createCell(initCell + 8);
                cell.setCellStyle(templStyle9);
                date = Date.from(item.getScheduledTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }
            i++;
        }
    }

    private void fillReportTable(Sheet sheet, int indexRow,
                                 int indexCellMale, int maleValue,
                                 int indexCellFemale, int femaleValue,
                                 int indexTotalValue, int total) {
        Row row = sheet.getRow(indexRow);
        Cell rowCellMaleValue = row.getCell(indexCellMale);
        rowCellMaleValue.setCellValue(maleValue);
        Cell rowCellMalePercent = row.getCell(indexCellMale + 1);
        rowCellMalePercent.setCellValue((float) maleValue / total);

        Cell rowCellFemaleValue = row.getCell(indexCellFemale);
        rowCellFemaleValue.setCellValue(femaleValue);
        Cell rowCellFemalePercent = row.getCell(indexCellFemale + 1);
        rowCellFemalePercent.setCellValue((float) femaleValue / total);

        Cell rowCellTotalValue = row.getCell(indexTotalValue);
        rowCellTotalValue.setCellValue(maleValue + femaleValue);
        Cell rowCellTotalPercent = row.getCell(indexTotalValue + 1);
        rowCellTotalPercent.setCellValue((float) (maleValue + femaleValue) / total);
    }
}