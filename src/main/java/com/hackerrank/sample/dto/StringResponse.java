package com.hackerrank.sample.dto;

public class StringResponse {
    private String echo;

    public StringResponse(final String s) {
        this.echo = s;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(final String echo) {
        this.echo = echo;
    }
}
