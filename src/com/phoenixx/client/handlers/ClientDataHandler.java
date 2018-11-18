package com.phoenixx.client.handlers;

import com.phoenixx.packets.objects.PostDataObject;

import java.util.ArrayList;

public class ClientDataHandler
{
    public static ArrayList<PostDataObject> loadedPosts = new ArrayList<>();

    public static void setLoadedPosts(ArrayList<PostDataObject> loadedPosts1) {
        loadedPosts.addAll(loadedPosts1);
    }

    public ArrayList<PostDataObject> getLoadedPosts() {
        return loadedPosts;
    }
}
