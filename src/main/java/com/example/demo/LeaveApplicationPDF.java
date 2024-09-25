package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class LeaveApplicationPDF {

    public void createPDF(String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Tiêu đề đơn
            document.add(new Paragraph("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM",
                    FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD)));
            document.add(new Paragraph("Độc lập – Tự do – Hạnh phúc",
                    FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD)));
            document.add(new Paragraph("\nĐƠN XIN NGHỈ PHÉP",
                    FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD)));

            // Thêm nội dung chính của đơn
            document.add(new Paragraph("\nKính gửi: - Chủ tịch HĐQT\n" +
                    "                 - Ban Giám Đốc Công Ty\n" +
                    "                 - Trưởng phòng Hành chính – Nhân sự\n"));

            // Bảng thông tin người làm đơn và loại nghỉ phép
            float[] columnWidths = {200F, 200F};
            PdfPTable table = new PdfPTable(columnWidths);

            // Tiêu đề cột
            table.addCell((new Paragraph("Người làm đơn:")));
            table.addCell((new Paragraph("Phép năm:")));

            // Thông tin người làm đơn và phép năm
            table.addCell((new Paragraph("Nguyễn Văn A")));
            table.addCell((new Paragraph("05 ngày")));
            document.add(table);

            // Thêm các phần khác của đơn xin nghỉ phép
            document.add(new Paragraph("\n1. Người làm đơn: ..."));
            document.add(new Paragraph("2. Thời gian làm đơn: ..."));
            document.add(new Paragraph("3. Ký duyệt: Duyệt / Không duyệt\n\n"));

            // Đóng tài liệu
            document.close();
            System.out.println("File PDF đã được tạo thành công tại: " + filePath);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
