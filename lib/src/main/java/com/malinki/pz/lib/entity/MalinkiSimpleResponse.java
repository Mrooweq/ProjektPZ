package com.malinki.pz.lib.entity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class MalinkiSimpleResponse {
    private List<String> responseList;
    private int result;

    public MalinkiSimpleResponse() {
        this.result = HttpServletResponse.SC_BAD_REQUEST;
    }

    public List<String> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<String> responseList) {
        this.responseList = responseList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
