package com.aot.standard.common.file.pdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotFileUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotNullUtils;
import com.aot.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This view class generates a PDF document 'on the fly' based on the data contained in the model.
 *
 * @author www.codejava.net
 */
public class PdfService extends AbstractPdfboxView {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String FONT_PATH = "/font/MALGUNBD.TTF";
    public static final String VIEW_NAME = "pdfView";
    private static final Double PAGE_MARGIN = 20.0;

    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final PDDocument document, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // get data model which is passed by the Spring container
        @SuppressWarnings("unchecked") final List<PdfVO> pdfVOs = (List<PdfVO>) model.get(AbstractPdfboxView.PDF_VOS);
        final JsonArray listData = AotMapperUtils.writeObjectAsJsonArray(model.get(AbstractPdfboxView.LIST_DATA));
        final String fileName = AotFileUtils.getEncodedFileName(request, (String) model.get(AbstractPdfboxView.FILE_NAME));

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "_" + AotDateUtils.getStringNow(AotDateUtils.YYYYMMDDHHMMSS) + AbstractPdfboxView.EXTENSION + ";");

        final float[] widths = new float[pdfVOs.size()];
        for (int i = 0; i < pdfVOs.size(); i++) {
            widths[i] = pdfVOs.get(i).getWidth();
        }

        final PDPage page = new PDPage();
        page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        document.addPage(page);

        super.font = PDType0Font.load(document, new ClassPathResource(FONT_PATH).getFile());

        final Double tableWidth = page.getMediaBox().getWidth() - 2 * PAGE_MARGIN;
        final Double yStartNewPage = page.getMediaBox().getHeight() - 2 * PAGE_MARGIN;
        final Double yStart = yStartNewPage;
        final Double bottomMargin = 0.0;

        final BaseTable table = new BaseTable(yStart.floatValue(), yStartNewPage.floatValue(), bottomMargin.floatValue(), tableWidth.floatValue(), PAGE_MARGIN.floatValue(), document, page, true,
                true);
        this.createColumnLabel(document, table, pdfVOs);
        this.addRowData(document, table, pdfVOs, listData);

    }

    private void createColumnLabel(final PDDocument document, final BaseTable table, final List<PdfVO> pdfVOs) throws IOException {
        // Create Header row
        final Row<PDPage> headerRow = table.createRow(15F);
        for (int j = 0; j < pdfVOs.size(); j++) {
            final Cell<PDPage> cell = headerRow.createCell(pdfVOs.get(j).getWidth(), pdfVOs.get(j).getTitle());
            cell.setFont(super.font);
            cell.setFillColor(Color.LIGHT_GRAY);
        }
        table.addHeaderRow(headerRow);
    }

    private void addRowData(final PDDocument document, final BaseTable table, final List<PdfVO> pdfVOs, final JsonArray listData) throws IOException {
        for (int i = 0; i < listData.size(); i++) {
            if (i != 0 && i % 200 == 0) {
                this.logger.debug("[PDF] writed {} rows", i + 1);
            }

            final Row<PDPage> row = table.createRow(10F);
            final JsonObject rowData = listData.get(i).getAsJsonObject();
            for (int j = 0; j < pdfVOs.size(); j++) {
                final JsonElement jsonElement = rowData.get(pdfVOs.get(j).getDbColName());
                String strData = "";
                if (AotNullUtils.isNotEmpty(jsonElement)) {
                    strData = jsonElement.getAsString();
                }
                this.writeColumnData(pdfVOs, row, j, strData);
            }
        }
        table.draw();
    }

}