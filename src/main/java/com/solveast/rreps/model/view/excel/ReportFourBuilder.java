package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.*;
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

        fillTableRaw(sheet,indexRow_0_4, indexCellMaleValue, report4.getMale_0_4(),indexCellFemaleValue, report4.getFemale_0_4(),indexCellTotal, report4.getTotal());
        fillTableRaw(sheet,indexRow_5_17, indexCellMaleValue, report4.getMale_5_17(),indexCellFemaleValue, report4.getFemale_5_17(),indexCellTotal, report4.getTotal());
        fillTableRaw(sheet,indexRow_18_59, indexCellMaleValue, report4.getMale_18_59(),indexCellFemaleValue, report4.getFemale_18_59(),indexCellTotal, report4.getTotal());
        fillTableRaw(sheet,indexRow_60_, indexCellMaleValue, report4.getMale_60_(),indexCellFemaleValue, report4.getFemale_60_(),indexCellTotal, report4.getTotal());

        Row row = null;

        Cell cell1 = null;
        Cell cell2 = null;
        Cell cell3 = null;
        Cell cell4 = null;
        Cell cell5 = null;
        Cell cell6 = null;
        Cell cell8 = null;

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

/*
        int rowShift = 7;
        CellStyle templStyle1 = sheet.getRow(rowShift).getCell(1).getCellStyle();
        CellStyle templStyle2 = sheet.getRow(rowShift).getCell(2).getCellStyle();
        CellStyle templStyle3 = sheet.getRow(rowShift).getCell(3).getCellStyle();
        CellStyle templStyle4 = sheet.getRow(rowShift).getCell(4).getCellStyle();
        CellStyle templStyle5 = sheet.getRow(rowShift).getCell(5).getCellStyle();
        CellStyle templStyle6 = sheet.getRow(rowShift).getCell(6).getCellStyle();
*/

        int i = 0;
/*        for (Query3 item : data) {

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
        }*/
    }

    private void fillTableRaw(Sheet sheet, int indexRow,
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