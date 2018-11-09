package com.phoenixx.server.managers.user;

import com.phoenixx.server.utils.TaskHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class UserDataManager extends TaskHandler {

    /** A Mapping of all UserData loaded. UUID, UserData */
    public TreeMap<String, UserData> userDataMapping = new TreeMap<String, UserData>(String.CASE_INSENSITIVE_ORDER);

    /** 30 seconds: A delay for each client data to be updated */
    private int delaySecond = 0;
    private int delaySecondMax = (20 * 60 * 5); //5 mins

    /** List of data to save */
    public Queue<UserData> dataToSave = new LinkedList<UserData>();

    public UserDataManager() {
    }

    public void onUpdate() {

        if (this.delaySecond++ >= this.delaySecondMax) {
            this.dataToSave.clear();
            ArrayList<String> connectedUsers = getAllUsersConnected();
            ArrayList<UserData> dataList = new ArrayList<UserData>(userDataMapping.values());

            if (dataList.size() > 0) {
                for (UserData data : dataList) {
                    data.setOnlineStatus(connectedUsers.contains(data.getUsername()));

                    if (data.isOnline()) {
                        //data.timePlayed += delaySecondMax / 20;
                    }

                    this.dataToSave.add(data);
                }
            }

            this.delaySecond = 0;
        }

        if(this.dataToSave.size() > 0) {
            //this.saveUserData(this.dataToSave.remove());
        }

        this.runTasks();
    }
/*
    *//** Get a player's data from there UUID, will create data if none exists *//*
    public UserData getPlayerDataFromUUID(String uuid, boolean forced) {
        if (uuid != null && uuid.length() > 0) {
            if (this.userDataMapping.containsKey(uuid) == false) {
                if(forced) {
                    this.userDataMapping.put(uuid, loadPotentialPlayerDataForced(uuid));
                } else {
                    this.loadPotentialPlayerData(uuid);
                    return null;
                }
            }

            return this.userDataMapping.get(uuid);
        }

        return null;
    }*/

/*    *//** Will return null if the User's UUID is not loaded yet *//*
    public UserData getUserData(String par1) {
        return this.getPlayerDataFromUUID(UUIDManager.getUsernameUUID(par1), false);
    }*/

/*    *//** Saves and unloads all the player data in the cache *//*
    public void unloadAllUserData() {
        this.saveAllUserData();
        this.removeAllUserData();
    }

    *//** Saves all the player data in the cache *//*
    public void saveAllUserData() {
        ArrayList<UserData> list = new ArrayList<UserData>(this.userDataMapping.values());

        for (UserData data : list) {
            this.saveUserData(data);
        }
    }

    *//** Unloads all the player data in the cache (NO SAVING) *//*
    public void removeAllUserData() {
        ArrayList<UserData> list = new ArrayList<UserData>(this.userDataMapping.values());

        for (UserData data : list) {
            this.removeLocalUserData(data.getUsername());
        }
    }*/

/*    *//** Saves the given username's player data to the folder *//*
    public void saveUserData(UserData par1) {
        if (par1 != null && ThreadBackupData.isBackingUp == false) {
            FDSTagCompound tag = new FDSTagCompound(par1.getUUID());
            par1.writeToFDS(tag);
            FDSHelper.saveFDSTagCompound(this.playerDataFolder + "" + par1.getUUID(), tag);
        }
    }

    *//** Removes the player's data from the local mapping cache *//*
    public void removeLocalUserData(String username) {
        String UUID = UUIDManager.getUsernameUUID(username);

        if (UUID != null) {
            if (this.userDataMapping.containsKey(UUID)) {
                this.userDataMapping.remove(UUID);
            }
        }
    }

    public static GroupManager getGroupManager() {
        return ServerNetworkMain.getUserDataManager().groupManager;
    }*/
/*
    *//** Get data from Username, not forced, can return null *//*
    public static UserData getData(String par1) {
        return ServerNetworkMain.getPlayerDataHandler().getPlayerData(par1);
    }

    *//** Get data from UUID, not forced, can return null *//*
    public static UserData getDataUUID(String par1) {
        return ServerNetworkMain().getPlayerDataHandler().getPlayerDataFromUUID(par1, false);
    }*/

    public static ArrayList<String> getAllUsersConnected() {
        ArrayList<String> names = new ArrayList<String>();

       /* for (String name : list) {
            names.add(con.getConnectionUsername());
        }*/

        return names;
    }
}
