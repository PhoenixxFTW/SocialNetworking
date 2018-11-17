package com.phoenixx.packets.responses;

import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;

public class PostDataResponse {

    private PostDataObject postDataObject;
    private ClientUserObject postOwnerObject;

    public PostDataResponse() {

    }

    public void setPostDataObject(PostDataObject postDataObject) {
        this.postDataObject = postDataObject;
    }

    public void setPostOwnerObject(ClientUserObject postOwnerObject) {
        this.postOwnerObject = postOwnerObject;
    }

    public PostDataObject getPostDataObject() {
        return postDataObject;
    }

    public ClientUserObject getPostOwnerObject() {
        return postOwnerObject;
    }
}
