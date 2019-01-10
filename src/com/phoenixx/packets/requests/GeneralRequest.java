package com.phoenixx.packets.requests;

public class GeneralRequest
{
    private int requestID;
    private String data;

    public GeneralRequest()
    {

    }

    public int getRequestID() {
        return requestID;
    }

    public String getData() {
        return data;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setData(String data) {
        this.data = data;
    }
}
