package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

public class PDFResponse {
    private PDF pdf;
    private int result;

    public PDFResponse(){
        result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public PDF getPdf() {
        return pdf;
    }

    public void setPdf(PDF pdf) {
        this.pdf = pdf;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
