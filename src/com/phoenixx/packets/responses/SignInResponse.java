package com.phoenixx.packets.responses;

import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;

import java.util.ArrayList;

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
    private ArrayList<PostDataObject> postDataObjects = new ArrayList<>();

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

    public void setPostDataObjects(ArrayList<PostDataObject> postDataObjects) {
        this.postDataObjects = postDataObjects;
    }

    public boolean canLogin() {
        return canLogin;
    }

    public String getMessage() {
        return message;
    }

    public ClientUserObject getClientUserObject() {
        return this.clientUserObject;
    }

    public ArrayList<PostDataObject> getPostDataObjects() {
        return postDataObjects;
    }
}
