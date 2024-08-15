package com.example.pdf_generation_queue.service;

import com.example.pdf_generation_queue.model.PDFConversionRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    @Autowired
    CommonService commonService;

    @Autowired
    PdfConversionService pdfConversionService;

    @RabbitListener(queues = "pdfConversionQueue")
    public void receiveMessage(String deSerizableString) throws Exception, ClassNotFoundException {

        System.out.println("Process Started!");

        PDFConversionRequest pdfConversionRequest = ( PDFConversionRequest ) commonService.stringToObject( deSerizableString );

        byte[] pdfContent = pdfConversionService.convertHtmlToPdf(pdfConversionRequest.getHtmlContent());

        //PDf write is used in this example
        pdfConversionService.storeFile(pdfContent);

        //callback service is not implemented in this example
        //callCallbackUrl(pdfConversionRequest.getCallbackUrl(), pdfContent);

        System.out.println("Process Completed!");
    }

    private void callCallbackUrl(String callbackUrl, byte[] pdfContent) {
        // RestTemplate or WebClient can be implemented
    }
}

