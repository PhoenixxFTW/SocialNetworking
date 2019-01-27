package com.phoenixx.server.packets;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Listener;

import java.util.*;

public class PacketRegistryServer extends Listener
{
    public static void registerPackets(Kryo kryo)
    {
        kryo.register(String.class);
        kryo.register(Integer.TYPE);
        kryo.register(Long.TYPE);
        kryo.register(ArrayList.class);
        kryo.register(UUID.class);
        kryo.register(Iterator.class);
        kryo.register(Map.Entry.class);
        kryo.register(Map.class);
        kryo.register(Date.class);
        kryo.register(Boolean.TYPE);
        kryo.register(Boolean.class);
        kryo.register(String[].class);

        /** Client packets */
        //kryo.register(Packet_Handshake_Request.class);
        //kryo.register(Packet_Message_Request.class);

        /** Server packets */
        //kryo.register(Packet_Handshake_Response.class);
        //kryo.register(Packet_Message_Response.class);
        //kryo.register(Packet_Notification_Response.class);

        /** Objects */
        //kryo.register(PlayerJoinObject.class);
        //kryo.register(ConnectionObject.class);
        //kryo.register(NotificationObject.class);
    }
}
