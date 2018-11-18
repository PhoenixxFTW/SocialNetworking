package com.phoenixx.packets.requests;

public class PostDataRequest
{
    private int requestID;

    private int postID;
    private String userUUID;

    public PostDataRequest()
    {

    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getPostID() {
        return postID;
    }

    public String getUserUUID() {
        return userUUID;
    }
}
