package com.phoenixx.packets.objects;

public class PostDataObject
{
    private int postID;
    private String ownerUUID;
    private String dateCreated;
    private String tags;
    private String postText;
    private String postTile;

    public PostDataObject()
    {

    }

    public PostDataObject(int postID, String ownerUUID, String dateCreated, String tags, String postTile, String postText)
    {
        this.postID = postID;
        this.ownerUUID = ownerUUID;
        this.dateCreated = dateCreated;
        this.tags = tags;
        this.postTile = postTile;
        this.postText = postText;
    }

    public int getPostID() {
        return postID;
    }

    public String getOwnerUUID() {
        return ownerUUID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getTags() {
        return tags;
    }

    public String getPostTile() {
        return postTile;
    }

    public String getPostText() {
        return postText;
    }
}
