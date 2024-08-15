package com.example.pdf_generation_queue.controller;

import com.example.pdf_generation_queue.dto.ResponseDto;
import com.example.pdf_generation_queue.model.PDFConversionRequest;
import com.example.pdf_generation_queue.service.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfConversionController {

    @Autowired
    private MessageProducerService messageProducerService;

    @PostMapping("/convert-html-to-pdf")
    public ResponseEntity<ResponseDto> convertHtmlToPdf(@RequestBody PDFConversionRequest request) throws IOException, ClassNotFoundException {
        messageProducerService.sendMessage(request);
        ResponseDto responseDto = ResponseDto.builder().success(true).message("Add to Queue successfully!").build();
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
}
