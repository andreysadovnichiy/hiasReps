package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.Query3;
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
        List<Query3> data = (List<Query3>) map.get("model");
        Sheet sheet = wbFactory.getSheet(report);

        Row row = null;

        Cell cell1 = null;
        Cell cell2 = null;
        Cell cell3 = null;
        Cell cell4 = null;
        Cell cell5 = null;
        Cell cell6 = null;
        Cell cell8 = null;


        CellStyle templ;

        Row row0 = sheet.getRow(0);
        Row row1 = sheet.getRow(1);
        Row row2 = sheet.getRow(2);
        Row row3 = sheet.getRow(3);
        Row row4 = sheet.getRow(4);
        Row row5 = sheet.getRow(5);
        Row row6 = sheet.getRow(6);
        Row row7 = sheet.getRow(7);
        Row row8 = sheet.getRow(8);
        Row row9 = sheet.getRow(9);

        int rowShift = 7;
        CellStyle templStyle1 = sheet.getRow(rowShift).getCell(1).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(rowShift).getCell(2).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(rowShift).getCell(3).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(rowShift).getCell(4).getCellStyle();
        CellStyle templStyle5 = sheet.getRow(rowShift).getCell(5).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(rowShift).getCell(6).getCellStyle();

        int i = 0;
        for (Query3 item : data) {

            row = sheet.getRow(rowShift + i);
            if (row == null) {
                row = sheet.createRow(rowShift + i);
            }

            cell1 = row.createCell(1);
            cell1.setCellValue(i+1);
            cell1.setCellStyle(templStyle2);

            cell2 = row.createCell(2);
            cell2.setCellValue(item.getIso3166_3());
            cell2.setCellStyle(templStyle2);

            cell3 = row.createCell(3);
            cell3.setCellValue(item.getUnhcrNum());
            cell3.setCellStyle(templStyle2);

            cell4 = row.createCell(4);
            cell4.setCellValue(item.getName()+" "+item.getFamilyName());
            cell4.setCellStyle(templStyle2);

            cell5 = row.createCell(5);
            cell5.setCellValue(3);
            cell5.setCellStyle(templStyle2);

            cell6 = row.createCell(6);
            cell6.setCellValue(item.getFileStatusId());
            cell6.setCellStyle(templStyle2);

            i++;
        }
    }
}