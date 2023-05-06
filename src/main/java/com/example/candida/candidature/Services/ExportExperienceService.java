package com.example.candida.candidature.Services;

import com.example.candida.candidature.Entity.Candidature;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.apache.poi.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;



@Service
public class ExportExperienceService {
    public ByteArrayInputStream experiencesPDFRepot(List<Candidature> candidatures) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("Liste des candidatures");
            com.itextpdf.text.Font font = com.itextpdf.text.FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Liste des candidatures", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(7);

            Stream.of("idCondidate", "enumEtat", "enumPost", "firstName", "lastname", "email", "mobile")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });
            for (Candidature candidature : candidatures) {
                PdfPCell idCondidate = new PdfPCell(new Phrase(candidature.getIdCondidate() + ""));
                idCondidate.setPaddingLeft(1);
                idCondidate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCondidate.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(idCondidate);

                PdfPCell enumEtat = new PdfPCell(new Phrase(candidature.getEnumEtat() + ""));
                enumEtat.setPaddingLeft(1);
                enumEtat.setVerticalAlignment(Element.ALIGN_MIDDLE);
                enumEtat.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(enumEtat);

                PdfPCell enumPost = new PdfPCell(new Phrase(candidature.getEnumPost() + ""));
                enumPost.setPaddingLeft(1);
                enumPost.setVerticalAlignment(Element.ALIGN_MIDDLE);
                enumPost.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(enumPost);

                PdfPCell firstName = new PdfPCell(new Phrase(candidature.getFirstName() + ""));
                firstName.setPaddingLeft(1);
                firstName.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstName.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstName);

                PdfPCell lastname = new PdfPCell(new Phrase(candidature.getLastname()));
                lastname.setPaddingLeft(1);
                lastname.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastname.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(lastname);

                PdfPCell email = new PdfPCell(new Phrase(candidature.getEmail()));
                email.setPaddingLeft(1);
                email.setVerticalAlignment(Element.ALIGN_MIDDLE);
                email.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(email);

                PdfPCell mobile = new PdfPCell(new Phrase(candidature.getMobile()));
                mobile.setPaddingLeft(1);
                mobile.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mobile.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(mobile);


            }
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }




    public static ByteArrayInputStream candidatureExcelReport(List<Candidature> candidatures) throws IOException {
        String[] columns = {"idCondidate", "enumEtat", "enumPost", "firstName", "lastname", "email", "mobile"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("candidatures");
            sheet.autoSizeColumn(columns.length);

            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();

            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            for(int col = 0; col < columns.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(cellStyle);
            }
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setDataFormat(creationHelper.createDataFormat().getFormat("#"));
            int rowIndex = 1;
            for (Candidature candidature: candidatures){
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(candidature.getIdCondidate());
                row.createCell(1).setCellValue(candidature.getEnumEtat().ordinal());
                row.createCell(2).setCellValue(candidature.getEnumPost().ordinal());
                row.createCell(3).setCellValue(candidature.getFirstName());
                row.createCell(4).setCellValue(candidature.getLastname());
                row.createCell(5).setCellValue(candidature.getEmail());
                row.createCell(6).setCellValue(candidature.getMobile());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
