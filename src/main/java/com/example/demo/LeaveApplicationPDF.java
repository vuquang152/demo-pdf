package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LeaveApplicationPDF {

    public void createPDF(String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            BaseFont bf = BaseFont.createFont("D:\\Intellij projects\\demo\\src\\main\\resources\\fonts\\Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font_bold_18 = new Font(bf, 18, Font.BOLD);
            Font font_bold_16 = new Font(bf, 16, Font.BOLD);
            Font font_bold_14 = new Font(bf, 14, Font.BOLD);
            Font font_bold_12 = new Font(bf, 12, Font.BOLD);
            Font font_bold_italic_12 = new Font(bf, 12, Font.BOLDITALIC);
            Font font_italic_14 = new Font(bf, 14, Font.ITALIC);
            Font font_italic_12 = new Font(bf, 12, Font.ITALIC);
            Font font_12 = new Font(bf, 12, Font.NORMAL);
            Font font_14 = new Font(bf, 14, Font.NORMAL);

            // Tiêu đề đơn
            Paragraph p1 = new Paragraph("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM",font_bold_16);
            p1.setAlignment(Element.ALIGN_CENTER);
            Paragraph p2 = new Paragraph("Độc lập – Tự do – Hạnh phúc",font_bold_14);
            p2.setAlignment(Element.ALIGN_CENTER);
            Paragraph p3 = new Paragraph("\nĐƠN XIN NGHỈ PHÉP",font_bold_18);
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            document.add(p2);
            document.add(p3);

            // Thêm nội dung chính của đơn
            document.add(new Paragraph("\nKính gửi: - Chủ tịch HĐQT\n" +
                    "                 - Ban Giám Đốc Công Ty\n" +
                    "                 - Trưởng phòng Hành chính – Nhân sự\n",font_bold_14));

            document.add(new Paragraph("\n1. Người làm đơn: ...",font_bold_14));
            String deptName = "Phong Hanh Chinh - nhan su";
            document.add(new Phrase("Bộ phận/Phòng Ban: " + deptName,font_14));
            String role = "Nhan vien";
            document.add(new Phrase("Chức vụ: " + role,font_14));

            // Bảng thông tin thời gian nghỉ
            float[] columnWidths = {100F, 100F, 75F, 75F, 50F, 250F};
            PdfPTable table = new PdfPTable(columnWidths);

            // Tiêu đề cột
            table.addCell(createCell("Thời gian nghỉ", font_bold_italic_12, 2, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Số ngày nghỉ", font_bold_italic_12, 4, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Từ\n" + "(ngày)", font_bold_italic_12, 1, 2, Element.ALIGN_CENTER));
            table.addCell(createCell("Đến\n" + "(ngày)", font_bold_italic_12, 1, 2, Element.ALIGN_CENTER));
            table.addCell(createCell("Phép năm\n" + "(không áp dụng trong thời gian thử việc)", font_bold_italic_12, 2, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Những ngày nghỉ khác(*)", font_bold_italic_12, 2, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Số ngày nghỉ", font_12, 1, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Ngày còn lại", font_12, 1, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Số ngày", font_italic_12, 1, 1, Element.ALIGN_CENTER));
            table.addCell(createCell("Lý do", font_italic_12, 1, 1, Element.ALIGN_CENTER));

            // Dữ liệu cho các hàng
            String[][] leaveData = {
                    {"01/09/2024", "05/09/2024", "2", "1", "2", "Lý do cá nhân"},
                    {"10/09/2024", "15/09/2024", "3", "0", "3", "Đi du lịch"}
                    // Thêm dữ liệu khác nếu cần
            };

            // Thêm các hàng dựa trên dữ liệu
            addRowsToTable(table, leaveData, font_12);

            document.add(table);

            // Thêm các phần khác của đơn xin nghỉ phép
            document.add(new Paragraph("2. Thời gian làm đơn: ...",font_bold_14));
            document.add(new Paragraph("3. Ký duyệt: Duyệt / Không duyệt\n\n",font_bold_14));

            // Đóng tài liệu
            document.close();
            System.out.println("File PDF đã được tạo thành công tại: " + filePath);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Hàm tiện ích tạo ô trong bảng
    private PdfPCell createCell(String content, Font font, int colspan, int rowspan, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        if (colspan > 1) cell.setColspan(colspan);
        if (rowspan > 1) cell.setRowspan(rowspan);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    private void addRowsToTable(PdfPTable table, String[][] data, Font font) {
        for (String[] row : data) {
            for (String cellData : row) {
                PdfPCell cell = new PdfPCell(new Paragraph(cellData, font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }
    }
}
