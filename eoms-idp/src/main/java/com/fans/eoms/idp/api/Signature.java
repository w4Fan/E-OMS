package com.fans.eoms.idp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Signature {
    private int code;
    private String token;

    public Signature(int code, String token) {
        this.code = code;
        this.token = token;
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
    public String getToken() {
        return token;
    }

    @JsonProperty
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "code=" + code +
                ", token='" + token + '\'' +
                '}';
    }
}
