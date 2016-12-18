package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.five.Query5;
import com.solveast.rreps.model.queries.five.Report5;
import com.solveast.rreps.model.queries.five.ReportFiveAdapter;
import com.solveast.rreps.model.queries.one.Report1;
import com.solveast.rreps.model.queries.one.ReportAdapter;
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
 * Created by Андрей on 12.12.2016.
 */
@Service
public class RFiveBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport reportPattern = ForReport.FIVE;
    private final WorkbookFactory wbFactory;

    public RFiveBuilder() {
        wbFactory = new WorkbookFactory(reportPattern);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        List<Query5> rawData = (List<Query5>) data.get("rawData");
        Integer total = (Integer) data.get("total");
        Integer unknown = (Integer) data.get("unknown");
        ReportFiveAdapter report = (ReportFiveAdapter) data.get("report");
        Map<String, Report5> reportMap = report.getReportMap();

        Sheet sheet = wbFactory.getSheet(reportPattern);
        fillTitle((String) data.get("title"), sheet);

        Row row;
        Cell cell;
        int rowShift = 6;
        int i = 0;
        int line = 0;

        CellStyle cellStyle = sheet.getRow(rowShift).getCell(1).getCellStyle();
        CellStyle cellProcentStyle = sheet.getRow(rowShift).getCell(17).getCellStyle();
        
        for (Map.Entry<String, Report5> item : reportMap.entrySet()) {
            line = rowShift + i;
            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            cell = row.createCell(1);
            cell.setCellValue(i + 1);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(item.getValue().getIso3166_3());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(item.getValue().getmUAC());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(item.getValue().getfUAC());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(item.getValue().getMale_18_59());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6);
            cell.setCellValue(item.getValue().getMale_60_());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7);
            cell.setCellValue(item.getValue().getFemale_18_59());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(8);
            cell.setCellValue(item.getValue().getFemale_60_());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(9);
            cell.setCellValue(item.getValue().getMale_0_4());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(10);
            cell.setCellValue(item.getValue().getFemale_0_4());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(11);
            cell.setCellValue(item.getValue().getMale_5_12());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(12);
            cell.setCellValue(item.getValue().getFemale_5_12());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(13);
            cell.setCellValue(item.getValue().getMale_13_17());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(14);
            cell.setCellValue(item.getValue().getFemale_13_17());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(15);
            cell.setCellValue(item.getValue().getInds());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(16);
            cell.setCellValue(item.getValue().getCases());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(17);
            cell.setCellValue((float)item.getValue().getTotalByIso() / total);
            cell.setCellStyle(cellProcentStyle);

            i++;
        }

    }

    private void fillTitle(String title, Sheet sheet) {
        Row row = sheet.getRow(1);
        Cell rowCellMaleValue = row.getCell(2);
        rowCellMaleValue.setCellValue(title);
    }

}