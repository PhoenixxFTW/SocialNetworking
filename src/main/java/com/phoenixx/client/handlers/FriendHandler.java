package com.phoenixx.client.handlers;

import com.phoenixx.packets.objects.ClientUserObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Junaid Talpur
 * - SocialNetworkingPlatform
 * - 2019-01-17
 * - 2:21 PM
 **/
public class FriendHandler
{
    public final FriendHandler INSTANCE;
    private HashMap<String, ClientUserObject> friendsList = new HashMap<>();
    private HashMap<String, ClientUserObject> friendRequestsIN = new HashMap<>(); // Friend requests sent to you
    private HashMap<String, ClientUserObject> friendRequestsOUT = new HashMap<>(); // Friend requests sent to people

    public FriendHandler()
    {
        this.INSTANCE = this;
    }

    public void loadFriends(ArrayList<ClientUserObject> givenFriendsList) {

        for(ClientUserObject clientUserObject: givenFriendsList){
            if(!friendsList.containsKey(clientUserObject.getUuid())){
                friendsList.put(clientUserObject.getUuid(), clientUserObject);
            }
        }
    }

    public void loadFriendRequestsIN(ArrayList<ClientUserObject> givenRequestsList) {

        for(ClientUserObject clientUserObject: givenRequestsList){
            if(!friendRequestsIN.containsKey(clientUserObject.getUuid())){
                friendRequestsIN.put(clientUserObject.getUuid(), clientUserObject);
            }
        }
    }

    public void loadFriendsFriendRequestsOUT(ArrayList<ClientUserObject> givenRequestsList) {

        for(ClientUserObject clientUserObject: givenRequestsList){
            if(!friendRequestsOUT.containsKey(clientUserObject.getUuid())){
                friendRequestsOUT.put(clientUserObject.getUuid(), clientUserObject);
            }
        }
    }

    public void addFriend(String userUUID)
    {
        if(!this.friendsList.containsKey(userUUID)){
            //TODO Get ClientUserObject from packet or pass it through
        }
    }




}




