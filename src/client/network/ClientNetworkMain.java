package client.network;

import packets.PacketRegistry;
import client.utils.ClientNetworkListener;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientNetworkMain
{
    public static Client client = new Client(32768, 8192);
    private static Kryo kryo;

    public static boolean shouldConnect = true;
    public static boolean hasConnectedBefore = false;

    public static final String NETWORK_IP = "127.0.0.1";
    public static final int NETWORK_TCP_PORT = 2273;
    public static final int NETWORK_UDP_PORT = 4625;


    public static void initializeClientConnection(String host, int tcpPort, int udpPort) throws IOException
    {
        System.out.println("[Client] Connecting to " + host + " on ports TCP: " + tcpPort + " & UDP:" + udpPort);

        client.stop();
        client.close();

        client.start();
        client.connect(5000, host, tcpPort, udpPort);
        kryo = client.getKryo();

        PacketRegistry.registerPackets(kryo);

        client.addListener(new PacketRegistry());
        client.addListener(new ClientNetworkListener());

        /*while(true)
        {
            Scanner reader = new Scanner(System.in);
            System.out.println("[Client] Enter a command >: ");
            String command = reader.nextLine();

            if(command.equalsIgnoreCase("test"))
            {
                System.out.println("test initiated");
            }
        }*/
    }

    public static void attemptReconnect()
    {
        if(shouldConnect && hasConnectedBefore)
        {
            new Thread(() -> {
                System.out.println("[Client] Attempting to reconnect to management server...");
                try
                {
                    client.stop();
                    client.start();
                    client.reconnect();
                }
                catch (IOException ex)
                {
                    System.out.println("[Client] Failed to re-connect to management server!");
                    try {
                        Thread.sleep(5000L);
                        attemptReconnect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ex.printStackTrace();
                }
            }).start();
        } else if(shouldConnect && !hasConnectedBefore) {
            new Thread(() -> {
                System.out.println("[Client] Attempting to reconnect to management server...");
                try
                {
                    initializeClientConnection(NETWORK_IP, NETWORK_TCP_PORT, NETWORK_UDP_PORT);
                }
                catch (IOException ex)
                {
                    System.out.println("[Client] Failed to re-connect to management server!");
                    try {
                        Thread.sleep(5000L);
                        attemptReconnect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ex.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String args[])
    {
        if(shouldConnect)
        {
            try {
                initializeClientConnection(NETWORK_IP, NETWORK_TCP_PORT,NETWORK_UDP_PORT);

            } catch (IOException e) {
                e.printStackTrace();
                attemptReconnect();
            }
        }
    }
}
