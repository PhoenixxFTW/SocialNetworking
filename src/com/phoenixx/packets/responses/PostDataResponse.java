package com.phoenixx.packets.responses;

import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;

import java.util.ArrayList;

public class PostDataResponse {

    private int responseID;

    private PostDataObject postDataObject;
    private ClientUserObject postOwnerObject;

    private ArrayList<PostDataObject> postDataObjects = new ArrayList<>();

    public PostDataResponse() {

    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public void setPostDataObject(PostDataObject postDataObject) {
        this.postDataObject = postDataObject;
    }

    public void setPostOwnerObject(ClientUserObject postOwnerObject) {
        this.postOwnerObject = postOwnerObject;
    }

    public void setPostDataObjects(ArrayList<PostDataObject> postDataObjects) {
        this.postDataObjects = postDataObjects;
    }

    public int getResponseID() {
        return responseID;
    }

    public PostDataObject getPostDataObject() {
        return postDataObject;
    }

    public ClientUserObject getPostOwnerObject() {
        return postOwnerObject;
    }

    public ArrayList<PostDataObject> getPostDataObjects() {
        return postDataObjects;
    }
}
