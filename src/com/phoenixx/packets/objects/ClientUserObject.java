package com.phoenixx.packets.objects;

import java.util.ArrayList;

public class ClientUserObject
{
    private String uuid;
    private String username;
    private String fullName;
    private String studentNumber;

    private String profilePicUrl;
    private String dateJoined;
    private String mood = "This is the default status text. You can have up to 300 characters here :D";

    private boolean isOnline = false;

    /** User rank */
    //public UserRank currentRank = UserRank.STUDENT;

    /** List of friends this player has */
    public ArrayList<String> friendsList = new ArrayList<String>();

    /** List of pending friend requests */
    public ArrayList<String> pendingFriendsList = new ArrayList<String>();

    public ClientUserObject()
    {

    }

    public ClientUserObject(String uuid, String studentNumber, String username, String fullName, String dateJoined, String profilePicUrl, String mood)
    {
        this.uuid = uuid;
        this.username = username;
        this.fullName = fullName;
        this.studentNumber = studentNumber;
        this.dateJoined = dateJoined;
        this.profilePicUrl = profilePicUrl;
        this.mood = mood;
    }

    public void setUuid(String uuid) {
        uuid = uuid;
    }

    public void setUsername(String username) {
        username = username;
    }

    public void setFullName(String fullName) {
        fullName = fullName;
    }

    public void setStudentNumber(String studentNumber) {
        studentNumber = studentNumber;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getMood() {
        return mood;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public void setOnlineStatus(boolean par1) {
        this.isOnline = par1;
    }

    /*public UserRank getCurrentRank() {
        return currentRank;
    }*/

    public void notifyFriendsOnline() {
        for(int i = 0; i < this.friendsList.size(); i++) {
            /*UserData data = UserDataManager.getData(this.friendsList.get(i));

            if(data != null && data.isOnline) {
                Notification.sendNotification(data.getUsername(), this.getUsername() + " is online!");
            }*/
        }
    }

}
