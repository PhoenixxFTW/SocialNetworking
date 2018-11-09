package com.phoenixx.packets.responses;

public class SignUpResponse
{
    private boolean registerSuccessFull;
    private String message;

    public SignUpResponse()
    {

    }

    public void setRegisterSuccessFull(boolean registerSuccessFull) {
        this.registerSuccessFull = registerSuccessFull;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRegisterSuccessFull() {
        return registerSuccessFull;
    }

    public String getMessage() {
        return message;
    }
}
