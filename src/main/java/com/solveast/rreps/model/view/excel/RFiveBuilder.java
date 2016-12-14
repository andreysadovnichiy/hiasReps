package com.solveast.rreps.model.view.excel;

import com.solveast.rreps.model.queries.five.Query5;
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
    private final ForReport report = ForReport.FIVE;
    private final WorkbookFactory wbFactory;

    public RFiveBuilder() {
        wbFactory = new WorkbookFactory(report);
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return wbFactory.getWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("model");
        List<Query5> rawData = (List<Query5>) data.get("rawData");

        Sheet sheet = wbFactory.getSheet(report);

    }
}