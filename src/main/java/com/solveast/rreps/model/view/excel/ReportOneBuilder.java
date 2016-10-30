package com.solveast.rreps.model.view.excel;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Андрей on 29.10.2016.
 */
public class ReportOneBuilder extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Sheet sheet = workbook.createSheet("New fucking sheet");

        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Book Title");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Author");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("ISBN");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Published Date");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Price");
        header.getCell(4).setCellStyle(style);
    }
}
