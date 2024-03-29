package com.phoenixx.client.utils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.phoenixx.client.controllers.CreatePostController;
import com.phoenixx.client.controllers.HomeScreenController;
import com.phoenixx.client.controllers.LoginController;
import com.phoenixx.client.controllers.SignUpController;
import com.phoenixx.client.handlers.ClientDataHandler;
import com.phoenixx.client.network.ClientNetworkMain;
import com.phoenixx.packets.responses.CreatePostResponse;
import com.phoenixx.packets.responses.PostDataResponse;
import com.phoenixx.packets.responses.SignInResponse;
import com.phoenixx.packets.responses.SignUpResponse;

public class ClientNetworkListener extends Listener {

    @Override
    public void received(Connection connection, Object object)
    {
        if(object instanceof SignUpResponse)
        {
            SignUpResponse response = (SignUpResponse)object;
            System.out.println(response.getMessage());

            SignUpController.getInstance().setRegistered(response.isRegisterSuccessFull());
        }

        if(object instanceof SignInResponse)
        {
            SignInResponse response = (SignInResponse)object;
            LoginController.getInstance().setCanLogin(response.canLogin(), response.getClientUserObject(), response.getPostDataObjects());

            ClientDataHandler.loadedPosts.clear();
            ClientDataHandler.setLoadedPosts(response.getPostDataObjects());
        }

        if(object instanceof PostDataResponse)
        {
            PostDataResponse response = (PostDataResponse)object;

            switch (response.getResponseID())
            {
                case 1:
                    if(response.getPostDataObject() != null)
                    {
                        try{
                            HomeScreenController.getInstance().loadPost(response.getPostDataObject(), response.getPostOwnerObject());
                        } catch (NullPointerException ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    ClientDataHandler.loadedPosts.clear();
                    ClientDataHandler.setLoadedPosts(response.getPostDataObjects());
                    break;

            }
        }

        if(object instanceof CreatePostResponse)
        {
            CreatePostResponse response = (CreatePostResponse)object;

            CreatePostController.getInstance().setPostCreated(response.isPostCreationSuccessFull());
        }
    }

    @Override
    public void connected(Connection arg) {
        // This is for when the Cardinal comes back online and connects to the com.phoenixx.client
        System.out.println("[Client] Connected to Management Server! [Cardinal_MessageListenerClient.connected]");

        /*if(CardinalClient.com.phoenixx.client.isConnected() && !CardinalClientVariables.connectedToCardinal)
        {
            CardinalClient.sendLoginInfo();
        }*/
    }

    @Override
    public void disconnected(Connection arg) {
        //TODO Management server offline / disconnected UI (RED background with error text)
        System.out.println("[Client] Disconnected from Management com.phoenixx.server! [ClientNetworkListener.disconnected]");

        if (ClientNetworkMain.shouldConnect) {
            ClientNetworkMain.attemptReconnect();
        }
    }
}
