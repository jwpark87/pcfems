package com.aot.standard.common.file.excel;

import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotFileUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ExcelService extends AbstractExcelXView {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String VIEW_NAME = "excelView";

    @Override
    protected void buildExcelDocument(final Map<String, Object> model, final SXSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked") final List<ExcelVO> excelVOs = (List<ExcelVO>) model.get(AbstractExcelXView.EXCEL_VOS);
        final JsonArray listData = AotMapperUtils.writeObjectAsJsonArray(model.get(AbstractExcelXView.LIST_DATA));
        final String fileName = AotFileUtils.getEncodedFileName(request, (String) model.get(AbstractExcelXView.FILE_NAME));
        final String title = StringUtils.defaultString((String) model.get(AbstractExcelXView.TITLE));

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "_" + AotDateUtils.getStringNow(AotDateUtils.YYYYMMDDHHMMSS) + AbstractExcelXView.EXTENSION + ";");

        final SXSSFSheet sheet = workbook.createSheet("report");
        sheet.setRandomAccessWindowSize(100);

        this.createColumnLabel(sheet, excelVOs, title);

        this.addRowData(sheet, excelVOs, listData);
        this.autoSizeColumn(sheet, excelVOs);
    }

    private void createColumnLabel(final SXSSFSheet sheet, final List<ExcelVO> excelVOs, final String title) {
        final SXSSFRow row0 = sheet.createRow(0);
        final CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, excelVOs.size() - 1);
        sheet.addMergedRegion(mergedRegion);
        final SXSSFCell cell00 = row0.createCell(0);
        cell00.setCellValue(title);

        final SXSSFRow row1 = sheet.createRow(1);
        final CellRangeAddress mergedRegion1 = new CellRangeAddress(1, 1, 0, excelVOs.size() - 1);
        sheet.addMergedRegion(mergedRegion1);
        final SXSSFCell cell10 = row1.createCell(0);
        cell10.setCellValue("This report was generated at " + AotDateUtils.getStringNow(AotDateUtils.YYYY_MM_DD_HH_MM_SS));

        final XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        final XSSFFont font = (XSSFFont) sheet.getWorkbook().createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        final SXSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < excelVOs.size(); i++) {
            final SXSSFCell cell = row2.createCell(i);
            try {
                cell.setCellValue(excelVOs.get(i).getTitle());
                cell.setCellStyle(cellStyle);
            } catch (final Exception e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    private void addRowData(final SXSSFSheet sheet, final List<ExcelVO> excelVOs, final JsonArray listData) throws SQLException, NoSuchFieldException, SecurityException {
        for (int i = 0; i < listData.size(); i++) {
            if (i != 0 && i % 100 == 0) {
                this.logger.debug("[Excel]{} writed {} rows", sheet.getSheetName(), i + 1);
            }
            final SXSSFRow row = sheet.createRow(3 + i);
            final JsonObject jo = listData.get(i).getAsJsonObject();
            for (int j = 0; j < excelVOs.size(); j++) {
                final JsonElement jsonElement = jo.get(excelVOs.get(j).getDbColName());
                if (jsonElement != null && !jsonElement.isJsonNull() && StringUtils.isNotEmpty(jsonElement.getAsString())) {
                    this.writeColumnData(excelVOs, j, row.createCell(j), jsonElement.getAsString());
                }
            }
        }
    }
}
