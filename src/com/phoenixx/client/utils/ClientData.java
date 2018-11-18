package com.phoenixx.client.utils;

import com.phoenixx.packets.objects.PostDataObject;

import java.util.ArrayList;

public class ClientData
{
    public static ArrayList<PostDataObject> loadedPosts = new ArrayList<>();

    public static void setLoadedPosts(ArrayList<PostDataObject> loadedPosts1) {
        loadedPosts.addAll(loadedPosts1);
    }

    public ArrayList<PostDataObject> getLoadedPosts() {
        return loadedPosts;
    }
}
