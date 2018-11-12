package com.phoenixx.packets.responses;

/**
 * @author Junaid Talpur
 * - phoenix
 * - 2018-11-08
 * - 1:15 PM
 */
public class SignInResponse
{
    private boolean canLogin;
    private String message;
    private String uuid;

    public SignInResponse()
    {

    }

    public void setCanLogin(boolean login) {
        this.canLogin = login;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean canLogin() {
        return canLogin;
    }

    public String getMessage() {
        return message;
    }

    public String getUuid() {
        return uuid;
    }
}
