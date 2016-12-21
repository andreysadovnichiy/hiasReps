package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.five.Query5;
import com.solveast.rreps.model.queries.five.Report5;
import com.solveast.rreps.model.queries.five.ReportFiveAdapter;
import com.solveast.rreps.model.queries.six.Query6;
import com.solveast.rreps.model.queries.six.Report6;
import com.solveast.rreps.model.queries.six.ReportSixAdapter;
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
 * Created by Андрей on 12.12.2016.
 */
@Service
public class RSixBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport reportPattern = ForReport.SIX;
    private final WorkbookFactory wbFactory;

    public RSixBuilder() {
        wbFactory = new WorkbookFactory(reportPattern);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        List<Query6> rawData = (List<Query6>) data.get("rawData");
        List<Family> rawDataFamilies = (List<Family>) data.get("families");
        Integer total = (Integer) data.get("total");
        Integer unknown = (Integer) data.get("unknown");
        ReportSixAdapter report = (ReportSixAdapter) data.get("report");
        Map<String, Report6> reportMap = report.getReportMap();

        Sheet sheet = wbFactory.getSheet(reportPattern);
        fillTitle((String) data.get("title"), sheet);

        Row row;
        Cell cell;
        int rowShift = 4;
        int i = 0;
        int line = 0;

        CellStyle cellStyle = sheet.getRow(rowShift).getCell(1).getCellStyle();

        Report6 rep = null;
        for (Map.Entry<String, Report6> item : reportMap.entrySet()) {
            line = rowShift + i;
            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            rep = item.getValue();

            cell = row.createCell(1);
            cell.setCellValue(++i);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(rep.getIso3166_3());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(rep.getFamilyNumber());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(rep.getTotalPersons());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(rep.getiUAC());
            cell.setCellStyle(cellStyle);
        }

        rowShift = 3;
        i = 0;
        Date date;
        CellStyle cellDateStyle = sheet.getRow(rowShift).getCell(9).getCellStyle();

        for (Query6 item : rawData) {
            line = rowShift + i;
            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            cell = row.createCell(7);
            cell.setCellValue(item.getClientId());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(8);
            cell.setCellValue(item.getApplicantId());
            cell.setCellStyle(cellStyle);

            if (item.getBirthDate() != null) {
                cell = row.createCell(9);
                cell.setCellStyle(cellDateStyle);
                date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            cell = row.createCell(10);
            cell.setCellValue(item.getAge());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(11);
            cell.setCellValue(item.getIso3166_3());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(12);
            cell.setCellValue(item.getSexCd());
            cell.setCellStyle(cellStyle);

            if (item.getRealTimeStart() != null) {
                cell = row.createCell(13);
                cell.setCellStyle(cellDateStyle);
                date = Date.from(item.getRealTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getRealTimeStop() != null) {
                cell = row.createCell(14);
                cell.setCellStyle(cellDateStyle);
                date = Date.from(item.getRealTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStart() != null) {
                cell = row.createCell(15);
                cell.setCellStyle(cellDateStyle);
                date = Date.from(item.getScheduledTimeStart().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            if (item.getScheduledTimeStop() != null) {
                cell = row.createCell(16);
                cell.setCellStyle(cellDateStyle);
                date = Date.from(item.getScheduledTimeStop().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            i++;
        }

        rowShift = 3;
        i = 0;
        line = rowShift + i;
        for (Family item : rawDataFamilies) {
            fillPerson(item.getClient(), line, sheet);
            line = rowShift + ++i;

            for (Person person : item.getFamily()) {
                fillPerson(person, line, sheet);
                line = rowShift + ++i;
            }
        }
    }

    private void fillPerson(Person item, int line, Sheet sheet) {
        Row row = sheet.getRow(line);
        if (row == null) {
            row = sheet.createRow(line);
        }

        CellStyle cellStyle = sheet.getRow(3).getCell(7).getCellStyle();
        CellStyle cellDateStyle = sheet.getRow(3).getCell(9).getCellStyle();

        Cell cell = row.createCell(18);
        cell.setCellValue(item.getClientId());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(19);
        cell.setCellValue(item.getApplicantId());
        cell.setCellStyle(cellStyle);

        Date date;
        if (item.getBirthDate() != null) {
            cell = row.createCell(20);
            cell.setCellStyle(cellDateStyle);
            date = Date.from(item.getBirthDate().atZone(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(date);
        }

        cell = row.createCell(21);
        cell.setCellValue(DateUtils.getAge(item.getBirthDate()));
        cell.setCellStyle(cellStyle);

        cell = row.createCell(22);
        cell.setCellValue(item.getSexCd());
        cell.setCellStyle(cellStyle);
    }

    private void fillTitle(String text, Sheet sheet) {
        Row row = sheet.getRow(1);
        Cell title = row.getCell(1);
        title.setCellValue(text);
    }

    private void fillTotal(int line, int unknown, int total, Sheet sheet) {
        Row row = sheet.getRow(line);
        if (row == null)
            row = sheet.createRow(line);

        Cell cell = row.createCell(11);
        cell.setCellValue("Total:");
        cell = row.createCell(12);
        cell.setCellValue(total);

        row = sheet.getRow(line + 1);
        if (row == null)
            row = sheet.createRow(line + 1);

        cell = row.createCell(11);
        cell.setCellValue("Unknown:");
        cell = row.createCell(12);
        cell.setCellValue(unknown);
    }
}