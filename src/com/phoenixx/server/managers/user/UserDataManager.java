package com.phoenixx.server.managers.user;

import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.server.ServerNetworkMain;
import com.phoenixx.server.utils.TaskHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class UserDataManager extends TaskHandler {

    /** A Mapping of all UserData loaded. UUID, UserData */
    public TreeMap<String, ClientUserObject> userDataMapping = new TreeMap<String, ClientUserObject>(String.CASE_INSENSITIVE_ORDER);

    /** 30 seconds: A delay for each client data to be updated */
    private int delaySecond = 0;
    private int delaySecondMax = (20 * 60 * 5); //5 mins

    /** List of data to save */
    public Queue<ClientUserObject> dataToSave = new LinkedList<ClientUserObject>();

    public UserDataManager() {
    }

    public void onUpdate() {

        if (this.delaySecond++ >= this.delaySecondMax) {
            this.dataToSave.clear();
            ArrayList<String> connectedUsers = getAllUsersConnected();
            ArrayList<ClientUserObject> dataList = new ArrayList<ClientUserObject>(userDataMapping.values());

            if (dataList.size() > 0) {
                for (ClientUserObject data : dataList) {
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

    /** Get a player's data from there UUID, will create data if none exists */
    public ClientUserObject getPlayerData(String username, String password, boolean forced) {
        if (username != null && username.length() > 0) {
            if (!this.userDataMapping.containsKey(username)) {
                if(forced) {
                    ClientUserObject clientUserObject = ServerNetworkMain.getDatabaseManager().getUserData(username, password);
                    this.userDataMapping.put(username, clientUserObject);
                    return clientUserObject;
                } else {
                    return this.getPlayerDataLocal(username);
                }
            }

            return this.userDataMapping.get(username);
        }

        return null;
    }

    /** Loads potential player data from the cloud's folder */
    public ClientUserObject getPlayerDataLocal(String username) {
        if(username != null) {
            if(this.userDataMapping.containsKey(username)) {
                ClientUserObject clientUserObject = this.userDataMapping.get(username);
                return clientUserObject;
            }
        }
        return null;
    }

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
