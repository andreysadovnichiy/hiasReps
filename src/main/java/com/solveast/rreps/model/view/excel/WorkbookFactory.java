package com.solveast.rreps.model.view.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Андрей on 28.11.2016.
 */
public class WorkbookFactory {
    private Workbook workbook = null;
    private ForReport report;

    public WorkbookFactory(ForReport report) {
        this.report = report;
    }

    public Workbook getWorkbook() {

        FileInputStream inputStream = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("patterns.xlsx").getFile());

            inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> sheetNames = new ArrayList<String>(Arrays.asList("Запрос 1", "Запрос 2", "Запрос 3", "Запрос 4", "Запрос 5", "Запрос 6", "Запрос 7"));
        switch (report) {
            case ONE:
                sheetNames.remove("Запрос 1");
                break;
            case TWO:
                sheetNames.remove("Запрос 2");
                break;
            case THREE:
                sheetNames.remove("Запрос 3");
                break;
            case FOUR:
                sheetNames.remove("Запрос 4");
                break;
            case FIVE:
                sheetNames.remove("Запрос 5");
                break;
            case SIX:
                sheetNames.remove("Запрос 6");
                break;
            case SEVEN:
                sheetNames.remove("Запрос 7");
                break;
        }

        for (String sheetName : sheetNames) {
            Sheet sheet = workbook.getSheet(sheetName);
            if(sheet != null)   {
                int index = workbook.getSheetIndex(sheet);
                workbook.removeSheetAt(index);
            }
        }

        return workbook;
    }

    public Sheet getSheet(ForReport report) {

        switch (report) {
            case ONE:
                return workbook.getSheet("Запрос 1");
            case TWO:
                return workbook.getSheet("Запрос 2");
            case THREE:
                return workbook.getSheet("Запрос 3");
            case FOUR:
                return workbook.getSheet("Запрос 4");
            case FIVE:
                return workbook.getSheet("Запрос 5");
            case SIX:
                return workbook.getSheet("Запрос 6");
            case SEVEN:
                return workbook.getSheet("Запрос 7");
        }
        return null;
    }
}
