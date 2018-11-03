package server.utils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.requests.LoginRequest;

public class ServerNetworkListener extends Listener
{
    public void received(Connection connection, Object object)
    {
        int connectionID = connection.getID();

        if(object instanceof LoginRequest)
        {
            LoginRequest request = (LoginRequest)object;

            System.out.println("Login request received from: " + request.getUsername());
            System.out.println("Password is :" + request.getPassword());
        }
    }

    @Override
    public void connected(Connection connection)
    {
        System.out.println("New connection made with ID: " + connection.getID());
    }

    @Override
    public void disconnected(Connection connection)
    {
        System.out.println("Connection with ID " + connection.getID() + " disconnected!");
    }

}