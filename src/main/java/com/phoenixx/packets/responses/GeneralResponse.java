package com.phoenixx.packets.responses;

public class GeneralResponse
{
    private int responseID;
    private String responseData;

    public GeneralResponse()
    {

    }

    public int getResponseID() {
        return responseID;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
