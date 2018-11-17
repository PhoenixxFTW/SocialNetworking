package com.phoenixx.client.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.phoenixx.client.utils.ClientNetworkListener;
import com.phoenixx.packets.PacketRegistry;

import java.io.IOException;

public class ClientNetworkMain
{
    public static Client client = new Client(327684, 819234);
    private static Kryo kryo;

    public static boolean shouldConnect = true;
    public static boolean hasConnectedBefore = false;
    public static boolean hasShownDisconnectNotification = false;
    public static boolean hasShowConnectNotification = false;

    public static final String NETWORK_IP = "127.0.0.1";
    public static final int NETWORK_TCP_PORT = 2273;
    public static final int NETWORK_UDP_PORT = 4625;

    private ClientNetworkMain()
    {
        if(shouldConnect)
        {
            try {
                initializeClientConnection(NETWORK_IP, NETWORK_TCP_PORT, NETWORK_UDP_PORT);
            } catch (IOException e) {
                e.printStackTrace();
                attemptReconnect();
            }
        }
    }

    public static ClientNetworkMain connect()
    {
        return new ClientNetworkMain();
    }

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
        if(!hasShowConnectNotification)
        {
            //LoginController.getInstance().showNotification();
        }

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

    public void sendMessageToServer(String messageGiven){
        client.sendTCP(messageGiven);
    }

    public void sendMessageToServer(Object objectGiven){
        client.sendTCP(objectGiven);
    }

    public void shutdown()
    {
        shouldConnect = false;
        client.close();
        client.stop();
    }

    /*public static void main(String args[])
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
    }*/
}
