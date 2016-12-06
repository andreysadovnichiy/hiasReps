package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.one.Query1;
import com.solveast.rreps.model.queries.one.Report1;
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
 * Created by Андрей on 29.10.2016.
 */
@Service
public class ReportOneBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return new WorkbookFactory(ForReport.ONE).getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");

        List<Query1> rawData = (List<Query1>) data.get("rawData");
        Map<String, Report1> proccessedData = (Map<String, Report1>) data.get("proccessedData");

        Sheet sheet = workbook.getSheet("Запрос 1");

        Row row = null;

        Cell cell1 = null;
        Cell cell2 = null;
        Cell cell3 = null;
        Cell cell4 = null;
        Cell cell5 = null;
        Cell cell6 = null;
        Cell cell8 = null;
        Cell cell9 = null;
        Cell cell10 = null;
        Cell cell11 = null;
        Cell cell12 = null;
        Cell cell13 = null;
        Cell cell14 = null;
        Cell cell15 = null;

        int rowShift = 3;
        int i = 0;
        Date date;

        DataFormat format = workbook.createDataFormat();

        CellStyle templStyle8 = sheet.getRow(rowShift).getCell(8).getCellStyle();
        CellStyle templStyle9 = sheet.getRow(rowShift).getCell(9).getCellStyle();
        CellStyle templStyle10 = sheet.getRow(rowShift).getCell(10).getCellStyle();
        CellStyle templStyle11 = sheet.getRow(rowShift).getCell(11).getCellStyle();
        CellStyle templStyle12 = sheet.getRow(rowShift).getCell(12).getCellStyle();
        CellStyle templStyle13 = sheet.getRow(rowShift).getCell(13).getCellStyle();
        CellStyle templStyle14 = sheet.getRow(rowShift).getCell(14).getCellStyle();
        CellStyle templStyle15 = sheet.getRow(rowShift).getCell(15).getCellStyle();

        for (Query1 item : rawData) {
            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell8 = row.createCell(8);
            cell8.setCellValue(item.getClientId());
            cell8.setCellStyle(templStyle8);

            cell9 = row.createCell(9);
            cell9.setCellValue(item.getApplicantsNumber());
            cell9.setCellStyle(templStyle9);

            cell10 = row.createCell(10);
            cell10.setCellStyle(templStyle10);
            date = Date.from(item.getRegisterTime().atZone(ZoneId.systemDefault()).toInstant());
            cell10.setCellValue(date);

            if (item.getUnhcrDate() != null) {
                cell11 = row.createCell(11);
                cell11.setCellStyle(templStyle11);
                date = Date.from(item.getUnhcrDate().atZone(ZoneId.systemDefault()).toInstant());
                cell11.setCellValue(date);
            }

            cell12 = row.createCell(12);
            cell12.setCellValue(item.getSexCd());
            cell12.setCellStyle(templStyle12);

            cell13 = row.createCell(13);
            cell13.setCellValue(item.getIso3166_3());
            cell13.setCellStyle(templStyle13);

            cell14 = row.createCell(14);
            cell14.setCellStyle(templStyle14);
            cell14.setCellValue(item.getUn_relationship_cd());

            if (item.getBirthDate() != null) {
                cell15 = row.createCell(15);
                cell15.setCellStyle(templStyle15);
                date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
                cell15.setCellValue(date);
            }

            i++;
        }

        rowShift = 6;
        i = 0;
        for (Map.Entry<String, Report1> entry : proccessedData.entrySet()) {
            Report1 item = (Report1) entry.getValue();

            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell1 = row.createCell(1);
            item.setId(i+1);
            cell1.setCellValue(item.getId());
//            cell8.setCellStyle(templStyle8);

            cell2 = row.createCell(2);
            cell2.setCellValue(item.getIso3166_3());
//            cell8.setCellStyle(templStyle8);

            cell3 = row.createCell(3);
            cell3.setCellValue(item.getMale());
//            cell8.setCellStyle(templStyle8);

            cell4 = row.createCell(4);
            cell4.setCellValue(item.getFemale());
//            cell8.setCellStyle(templStyle8);

            cell5 = row.createCell(5);
            cell5.setCellValue(item.getBoys());
//            cell8.setCellStyle(templStyle8);

            cell6 = row.createCell(6);
            cell6.setCellValue(item.getGirls());
//            cell8.setCellStyle(templStyle8);

            i++;
        }
    }
}