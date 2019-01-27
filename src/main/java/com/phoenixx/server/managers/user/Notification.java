package com.phoenixx.server.managers.user;

import com.phoenixx.server.ServerNetworkMain;

public class Notification {

    //TODO send notification packet

    public String sender = "Phoenix Studios";
    public String message;

    public Notification(String par1) {
        this.message = par1;
    }

    public Notification setSender(String par1) {
        this.sender = par1;
        return this;
    }

    public static void sendNotificationAll(String message) {
        ServerNetworkMain.server.sendToAllTCP(message);
    }

    public static void sendNotification(String user, String message) {
        sendNotification(user, message, "Management Server");
    }

    public static void sendNotification(String user, String message, String sender) {
        Notification notification = new Notification(message).setSender(sender);

        synchronized (ServerNetworkMain.server.getKryo()) {
            //ServerNetworkMain.server.sendToTCP();
        }
    }
}
