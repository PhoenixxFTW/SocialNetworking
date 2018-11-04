package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import packets.PacketRegistry;
import server.managers.database.Database;
import server.managers.database.DatabaseManager;
import server.managers.database.DatabaseThread;
import server.utils.ServerNetworkListener;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Date;

public class ServerNetworkMain
{
    public static Server server = new Server(32768, 8192);
    private static Kryo kryo;

    private static final int NETWORK_TCP_PORT = 2273;
    private static final int NETWORK_UDP_PORT = 4625;

    private static final String DB_HOST = "den1.mysql4.gear.host";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "testing17";
    private static final String DB_PASS = "Ix7odo!4B_Nb";
    private static final String DB_NAME = "testing17";

    public static Date startDate;
    private static ServerNetworkMain instance;

    private static Database mySqlDatabase;
    private static DatabaseThread databaseQueue;

    public static void main(String args[])
    {
        try {
            initializeServer(NETWORK_TCP_PORT,NETWORK_UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mySqlDatabase = new Database(DB_HOST, Integer.parseInt(DB_PORT), DB_USER, DB_NAME, DB_PASS);
        mySqlDatabase.createDefaultTables();

		databaseQueue = new DatabaseThread();
		databaseQueue.start();
    }

    private static void initializeServer(int tcpPort, int udpPort) throws IOException
    {
        System.out.println("Starting Management Server...");
        server.start();
        server.bind(tcpPort, udpPort);
        kryo = server.getKryo();

        System.out.println("Started new Management Server Instance on ports TCP/UDP: " + tcpPort + "/" + udpPort);
        System.out.println("Running Java Version: " + System.getProperty("java.version"));

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
}
