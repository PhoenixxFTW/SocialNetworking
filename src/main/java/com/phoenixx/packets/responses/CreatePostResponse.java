package com.phoenixx.packets.responses;

public class CreatePostResponse
{
    private boolean postCreationSuccessFull;
    private String message;

    public CreatePostResponse()
    {

    }

    public void setPostCreationSuccessFull(boolean postCreationSuccessFull) {
        this.postCreationSuccessFull = postCreationSuccessFull;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPostCreationSuccessFull() {
        return postCreationSuccessFull;
    }

    public String getMessage() {
        return message;
    }
}
