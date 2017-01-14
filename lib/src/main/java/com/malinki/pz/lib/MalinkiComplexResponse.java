package com.malinki.pz.lib;

import javax.servlet.http.HttpServletResponse;

public class MalinkiComplexResponse <A, B> {
    private A dtoResult;
    private B uvmResult;
    private int result;

    public MalinkiComplexResponse() {
        this.result = HttpServletResponse.SC_BAD_REQUEST;
    }

    public A getDtoResult() {
        return dtoResult;
    }

    public void setDtoResult(A dtoResult) {
        this.dtoResult = dtoResult;
    }

    public B getUvmResult() {
        return uvmResult;
    }

    public void setUvmResult(B uvmResult) {
        this.uvmResult = uvmResult;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
