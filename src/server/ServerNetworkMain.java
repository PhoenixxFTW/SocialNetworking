package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import packets.PacketRegistry;
import server.packets.PacketRegistryServer;
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

    public static Date startDate;

    public static void main(String args[])
    {
        try {
            initializeServer(NETWORK_TCP_PORT,NETWORK_UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
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


        PacketRegistryServer.registerPackets(kryo);

        server.addListener(new PacketRegistry());
        server.addListener(new ServerNetworkListener());

        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long startTime = runtimeBean.getStartTime();
        startDate = new Date(startTime);

        //new Thread(new ConsoleHandler()).start();
    }
}
