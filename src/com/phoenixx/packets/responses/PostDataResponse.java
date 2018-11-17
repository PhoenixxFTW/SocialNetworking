package com.phoenixx.packets.responses;

import com.phoenixx.packets.objects.PostDataObject;

import java.util.ArrayList;

public class PostDataResponse {
    private ArrayList<PostDataObject> postDataObjects = new ArrayList<>();

    public PostDataResponse() {

    }

    public void setPostDataObjects(ArrayList<PostDataObject> postDataObjects) {
        this.postDataObjects = postDataObjects;
    }

    public ArrayList<PostDataObject> getPostDataObjects() {
        return postDataObjects;
    }
}
