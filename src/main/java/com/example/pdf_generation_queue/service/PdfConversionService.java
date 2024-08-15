package com.example.pdf_generation_queue.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

@Service
public class PdfConversionService {
    public byte[] convertHtmlToPdf(String htmlContent) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();

        // Use XMLWorkerHelper to parse and render HTML content
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlContent.getBytes()));

        document.close();
        writer.close();

        return outputStream.toByteArray();
    }

//    public byte[] convertHtmlToPdf(String htmlContent) throws IOException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter writer = new PdfWriter(outputStream);
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        // Convert HTML to PDF using HtmlConverter
//        HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes()), pdfDocument);
//
//        document.close();
//        return outputStream.toByteArray();
//    }

    public void storeFile(byte[] pdfContent) throws Exception {
        File pdfFile = new File(new Date().getTime()+"-my-pdf.pdf");
        try (FileOutputStream fileOutputStream = new FileOutputStream(pdfFile)) {
            fileOutputStream.write(pdfContent);
        }
    }
}
