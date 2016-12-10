package com.solveast.rreps.model.view.excel;

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
        List<Report3> data = (List<Report3>) map.get("model");
        Sheet sheet = wbFactory.getSheet(report);

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
            cell.setCellValue(i+1);
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
            if(item.getFamilyMembers() != null)
                cell.setCellValue(item.getFamilyMembers());
            cell.setCellStyle(templStyle);

            cell = row.createCell(6);
            cell.setCellValue(item.getFileStatusName());
            cell.setCellStyle(templStyle);

            i++;
        }
    }
}