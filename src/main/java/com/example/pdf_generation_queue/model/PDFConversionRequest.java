package com.example.pdf_generation_queue.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PDFConversionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4314757012958912389L;

    private String htmlContent;
    private String callbackUrl;

    public String getHtmlContent(){
        return htmlContent;
    }

    public String getCallbackUrl(){
        return  callbackUrl;
    }

}

