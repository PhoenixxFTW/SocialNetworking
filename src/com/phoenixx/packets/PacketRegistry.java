package com.phoenixx.packets;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Listener;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import com.phoenixx.packets.objects.SignUpObject;
import com.phoenixx.packets.requests.PostDataRequest;
import com.phoenixx.packets.requests.SignInRequest;
import com.phoenixx.packets.requests.SignUpRequest;
import com.phoenixx.packets.responses.PostDataResponse;
import com.phoenixx.packets.responses.SignInResponse;
import com.phoenixx.packets.responses.SignUpResponse;

import java.util.*;

public class PacketRegistry extends Listener
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
        kryo.register(SignInRequest.class);
        kryo.register(SignUpRequest.class);
        kryo.register(PostDataRequest.class);

        /** Server packets */
        kryo.register(SignUpResponse.class);
        kryo.register(SignInResponse.class);
        kryo.register(PostDataResponse.class);

        /** Objects */
        kryo.register(SignUpObject.class);
        kryo.register(ClientUserObject.class);
        kryo.register(PostDataObject.class);
    }
}
