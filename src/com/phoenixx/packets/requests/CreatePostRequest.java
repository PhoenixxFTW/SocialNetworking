package com.phoenixx.packets.requests;

import com.phoenixx.packets.objects.PostDataObject;

public class CreatePostRequest
{
    private PostDataObject postDataObject;
    private String ownerUUID;
    private String ownerName;

    public CreatePostRequest()
    {

    }

    public void setPostDataObject(PostDataObject postDataObject) {
        this.postDataObject = postDataObject;
    }

    public void setOwnerUUID(String ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public PostDataObject getPostDataObject() {
        return postDataObject;
    }

    public String getOwnerUUID() {
        return ownerUUID;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
