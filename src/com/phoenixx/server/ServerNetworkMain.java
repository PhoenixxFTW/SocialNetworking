package com.phoenixx.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.phoenixx.packets.PacketRegistry;
import com.phoenixx.server.managers.database.Database;
import com.phoenixx.server.managers.database.DatabaseManager;
import com.phoenixx.server.managers.database.DatabaseThread;
import com.phoenixx.server.managers.user.UserDataManager;
import com.phoenixx.server.utils.ServerNetworkListener;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Date;


public class ServerNetworkMain
{
    public static Server server = new Server(327684, 819234);
    private static Kryo kryo;

    private static final int NETWORK_TCP_PORT = 2273;
    private static final int NETWORK_UDP_PORT = 4625;

    private static final String DB_HOST = "den1.mysql4.gear.host";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "testing17";
    private static final String DB_PASS = "Ix7odo!4B_Nb";
    private static final String DB_NAME = "testing17";

    public static Date startDate;

    private static boolean isRunning = false;

    public static ServerNetworkMain instance;

    private static Database mySqlDatabase;
    private static DatabaseManager databaseManager;
    private static DatabaseThread databaseQueue;
    private static UserDataManager userDataManager;

    public static void main(String args[])
    {
        try {
            initializeServer(NETWORK_TCP_PORT,NETWORK_UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mySqlDatabase = new Database(DB_HOST, Integer.parseInt(DB_PORT), DB_USER, DB_NAME, DB_PASS);

        databaseManager = new DatabaseManager(mySqlDatabase);
        databaseManager.createDefaultTables();

        /*databaseManager.createNewPost("testUUID1", "Bryn", "Math, cooking, other shit", "What up hoes", "fake text");
        databaseManager.createNewPost("testUUID2", "Jon", "English", "How do I do this?", "fake text");
        databaseManager.createNewPost("testUUID3", "Jake", "Tech design", "Where can I get this", "fake text");
        databaseManager.createNewPost("testUUID4", "Alex", "Robotics", "Who wrote this", "fake text");
        databaseManager.createNewPost("testUUID5", "Brad", "Law, foo", "pls help me", "fake text");
        databaseManager.createNewPost("testUUID6", "Jay", "Civics", "This wont work", "fake text");
        databaseManager.createNewPost("testUUID7", "Maalik", "Geography", "Where Im I", "fake text");
        databaseManager.createNewPost("testUUID8", "Reasad", "Computers", "What is this", "fake text");*/

		databaseQueue = new DatabaseThread();
		databaseQueue.start();

        userDataManager = new UserDataManager();

        // Update shit here
        while(isRunning) {
            userDataManager.onUpdate();

            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void initializeServer(int tcpPort, int udpPort) throws IOException
    {
        System.out.println("Starting Management Server...");
        server.start();
        server.bind(tcpPort, udpPort);
        kryo = server.getKryo();

        System.out.println("Started new Management Server Instance on ports TCP/UDP: " + tcpPort + "/" + udpPort);
        System.out.println("Running Java Version: " + System.getProperty("java.version"));
        isRunning = true;

        PacketRegistry.registerPackets(kryo);

        server.addListener(new PacketRegistry());
        server.addListener(new ServerNetworkListener());

        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long startTime = runtimeBean.getStartTime();
        startDate = new Date(startTime);

        //TODO Create a whole new JavaFX UI for this with console system and everything

        //new Thread(new ConsoleHandler()).start();
    }

    public static Database getDatabase() {
        return mySqlDatabase;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static UserDataManager getUserDataManager() {
        return userDataManager;
    }
}
