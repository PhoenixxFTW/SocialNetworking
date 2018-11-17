package com.phoenixx.packets.requests;

public class PostDataRequest
{
    private int postID;
    private String userUUID;

    public PostDataRequest()
    {

    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public int getPostID() {
        return postID;
    }

    public String getUserUUID() {
        return userUUID;
    }
}
