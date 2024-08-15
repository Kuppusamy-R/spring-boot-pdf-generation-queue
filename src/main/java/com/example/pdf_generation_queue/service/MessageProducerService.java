package com.example.pdf_generation_queue.service;

import com.example.pdf_generation_queue.model.PDFConversionRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class MessageProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    PdfConversionService pdfConversionService;

    @Autowired
    CommonService commonService;

    public void sendMessage(PDFConversionRequest pdfConversionRequest) throws IOException {

        String serializableString = commonService.objectToString(pdfConversionRequest);
        rabbitTemplate.convertAndSend("pdfConversionQueue", serializableString);
    }

}