package com.phoenixx.server.managers.user;

import java.util.ArrayList;

public class UserData {

    private int id = -1;

    private String username = "";
    private String UUID = "";
    private boolean isOnline = false;

    /** User rank */
    public UserRank currentRank = UserRank.STUDENT;

    /** The users status set up in there profile */
    public String mood = "I am a newb, tehe :D";

    /** List of friends this player has */
    public ArrayList<String> friendsList = new ArrayList<String>();

    /** List of pending friend requests */
    public ArrayList<String> pendingFriendsList = new ArrayList<String>();

    public UserData(String par1) {
        this.UUID = par1;
    }

    public void setUsername(String par1) {
        this.username = par1;
    }

    public void notifyFriendsOnline() {
        for(int i = 0; i < this.friendsList.size(); i++) {
            /*UserData data = UserDataManager.getData(this.friendsList.get(i));

            if(data != null && data.isOnline) {
                Notification.sendNotification(data.getUsername(), this.getUsername() + " is online!");
            }*/
        }
    }

    public String getUUID() {
        return this.UUID;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public void setOnlineStatus(boolean par1) {
        this.isOnline = par1;
    }

    public UserRank getCurrentRank() {
        return currentRank;
    }

    public String getSender() {
        return this.username;
    }

    public void sendChat(String par1) {
        //PacketHandler.sendPacketToPlayer(this.username, new PacketConsoleMessage(par1));
    }
}
