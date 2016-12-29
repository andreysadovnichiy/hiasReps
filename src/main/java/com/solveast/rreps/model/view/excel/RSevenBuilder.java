package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.seven.Query7;
import com.solveast.rreps.model.queries.seven.Report7;
import com.solveast.rreps.model.queries.seven.ReportSevenAdapter;
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
public class RSevenBuilder extends AbstractXlsxView {
    private final String excelFilePath = "patterns.xls";
    private final ForReport reportPattern = ForReport.SEVEN;
    private final WorkbookFactory wbFactory;

    public RSevenBuilder() {
        wbFactory = new WorkbookFactory(reportPattern);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        List<Query7> rawData = (List<Query7>) data.get("rawData");
        Map<String, Report7> reportMap = (Map<String, Report7>) data.get("report");

        Sheet sheet = wbFactory.getSheet(reportPattern);
        fillTitle((String) data.get("title"), sheet);

        Row row;
        Cell cell;
        int rowShift = 8;
        int i = 0;
        int line = 0;

        CellStyle cellStyle = sheet.getRow(rowShift).getCell(1).getCellStyle();

        Report7 rep = null;
        for (Map.Entry<String, Report7> item : reportMap.entrySet()) {
            line = rowShift + i;
            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            rep = item.getValue();

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(++i);

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(rep.getIso3166_3());

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            if (rep.getActiveT() > 0) {
                cell.setCellValue(rep.getActiveT());
            }

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            if (rep.getActiveM() > 0) {
                cell.setCellValue(rep.getActiveM());
            }

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            if (rep.getActiveF() > 0) {
                cell.setCellValue(rep.getActiveF());
            }

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            if (rep.getLegalArt5() > 0) {
                cell.setCellValue(rep.getLegalArt5());
            }

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            if (rep.getLegalArt8() > 0) {
                cell.setCellValue(rep.getLegalArt8());
            }

            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            if (rep.getLegalArt6() > 0) {
                cell.setCellValue(rep.getLegalArt6());
            }

            cell = row.createCell(9);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance1Pos() > 0) {
                cell.setCellValue(rep.getInstance1Pos());
            }

            cell = row.createCell(10);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance1Neg() > 0) {
                cell.setCellValue(rep.getInstance1Neg());
            }

            cell = row.createCell(11);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance1Pnd() > 0) {
                cell.setCellValue(rep.getInstance1Pnd());
            }

            cell = row.createCell(12);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance1Cls() > 0) {
                cell.setCellValue(rep.getInstance1Cls());
            }

            cell = row.createCell(13);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance2Pos() > 0) {
                cell.setCellValue(rep.getInstance2Pos());
            }

            cell = row.createCell(14);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance2Neg() > 0) {
                cell.setCellValue(rep.getInstance2Neg());
            }

            cell = row.createCell(15);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance2Pnd() > 0) {
                cell.setCellValue(rep.getInstance2Pnd());
            }

            cell = row.createCell(16);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance2ClsSsp() > 0) {
                cell.setCellValue(rep.getInstance2ClsSsp());
            }

            cell = row.createCell(17);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance3Pos() > 0) {
                cell.setCellValue(rep.getInstance3Pos());
            }

            cell = row.createCell(18);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance3Neg() > 0) {
                cell.setCellValue(rep.getInstance3Neg());
            }

            cell = row.createCell(19);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance3Pnd() > 0) {
                cell.setCellValue(rep.getInstance3Pnd());
            }

            cell = row.createCell(20);
            cell.setCellStyle(cellStyle);
            if (rep.getInstance3ClsSsp() > 0) {
                cell.setCellValue(rep.getInstance3ClsSsp());
            }

            cell = row.createCell(21);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusPnd1() > 0) {
                cell.setCellValue(rep.getStatusPnd1());
            }

            cell = row.createCell(22);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusPnd2() > 0) {
                cell.setCellValue(rep.getStatusPnd2());
            }

            cell = row.createCell(23);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusPnd3PosFin() > 0) {
                cell.setCellValue(rep.getStatusPnd3PosFin());
            }

            cell = row.createCell(24);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusPnd3NegFin() > 0) {
                cell.setCellValue(rep.getStatusPnd3NegFin());
            }

            cell = row.createCell(25);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusClosedPosFin() > 0) {
                cell.setCellValue(rep.getStatusClosedPosFin());
            }

            cell = row.createCell(26);
            cell.setCellStyle(cellStyle);
            if (rep.getStatusClosedExhausted() > 0) {
                cell.setCellValue(rep.getStatusClosedExhausted());
            }

            cell = row.createCell(27);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(28);
            cell.setCellStyle(cellStyle);
        }

        rowShift = 3;
        i = 0;
        Date date;
        CellStyle cellDateStyle = sheet.getRow(3).getCell(35).getCellStyle();
        for (Query7 item : rawData) {
            line = rowShift + i;
            row = sheet.getRow(line);
            if (row == null) {
                row = sheet.createRow(line);
            }

            cell = row.createCell(30);
            cell.setCellStyle(cellStyle);
            if (item.getClientId() > 0) {
                cell.setCellValue(item.getClientId());
            }

            cell = row.createCell(31);
            cell.setCellStyle(cellStyle);
            if (item.getCourtCaseLawsuitId() > 0) {
                cell.setCellValue(item.getCourtCaseLawsuitId());
            }

            cell = row.createCell(32);
            cell.setCellStyle(cellStyle);
            if (item.getCourtCaseId() > 0) {
                cell.setCellValue(item.getCourtCaseId());
            }

            cell = row.createCell(33);
            cell.setCellStyle(cellStyle);
            if (item.getCourtCaseStateId() > 0) {
                cell.setCellValue(item.getCourtCaseStateId());
            }

            cell = row.createCell(34);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getCourtCaseStateName());

            cell = row.createCell(35);
            cell.setCellStyle(cellDateStyle);
            if (item.getLadgetDate() != null) {
                date = Date.from(item.getLadgetDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            cell = row.createCell(36);
            cell.setCellStyle(cellDateStyle);
            if (item.getDecisionDate() != null) {
                date = Date.from(item.getDecisionDate().atZone(ZoneId.systemDefault()).toInstant());
                cell.setCellValue(date);
            }

            cell = row.createCell(37);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getMsRejectionCd());

            cell = row.createCell(38);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getIso3166_3());

            cell = row.createCell(39);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getSexCd());

            i++;
        }
    }

    private void fillTitle(String text, Sheet sheet) {
        Row row = sheet.getRow(1);
        Cell title = row.getCell(1);
        title.setCellValue(text);
    }

}