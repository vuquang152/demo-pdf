package com.example.demo;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class LeaveApplicationPDFController {

    @GetMapping("/createLeaveApplicationPDF")
    public String createLeaveApplicationPDF() {
        LeaveApplicationPDF pdfCreator = new LeaveApplicationPDF();
        pdfCreator.createPDF("don_xin_nghi_phep-create.pdf");
        return "File PDF đã được tạo thành công!";
    }

    @GetMapping("/downloadLeaveApplicationPDF")
    public ResponseEntity<InputStreamResource> downloadPDF() throws FileNotFoundException {
        LeaveApplicationPDF pdfCreator = new LeaveApplicationPDF();
        String filePath = "don_xin_nghi_phep_1.pdf";
        pdfCreator.createPDF(filePath);

        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length())
                .body(resource);
    }
}