package com.phoenixx.packets.responses;

import com.phoenixx.packets.objects.ClientUserObject;

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
    private ClientUserObject clientUserObject;

    public SignInResponse()
    {

    }

    public void setCanLogin(boolean login) {
        this.canLogin = login;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setClientUserObject(ClientUserObject clientUserObject) {
        this.clientUserObject = clientUserObject;
    }

    public boolean canLogin() {
        return canLogin;
    }

    public String getMessage() {
        return message;
    }

    public ClientUserObject getClientUserObject() {
        return clientUserObject;
    }
}
