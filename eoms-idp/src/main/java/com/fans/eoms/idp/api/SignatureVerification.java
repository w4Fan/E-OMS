package com.fans.eoms.idp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignatureVerification {
    private int code;
    private String result;

    public SignatureVerification(int code, String result) {
        this.code = code;
        this.result = result;
    }

    @JsonProperty
    public int getCode() {
        return code;
    }

    @JsonProperty
    public void setCode(int code) {
        this.code = code;
    }

    @JsonProperty
    public String getResult() {
        return result;
    }

    @JsonProperty
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SignatureVerification{" +
                "code=" + code +
                ", result='" + result + '\'' +
                '}';
    }
}
