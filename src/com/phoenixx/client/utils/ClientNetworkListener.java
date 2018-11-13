package com.phoenixx.client.utils;

import com.phoenixx.client.application.ClientMain;
import com.phoenixx.client.controllers.HomeScreenController;
import com.phoenixx.client.controllers.LoginController;
import com.phoenixx.client.controllers.SignupController;
import com.phoenixx.client.network.ClientNetworkMain;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.phoenixx.packets.responses.SignInResponse;
import com.phoenixx.packets.responses.SignUpResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ClientNetworkListener extends Listener {

    @Override
    public void received(Connection connection, Object object)
    {
        if(object instanceof SignUpResponse)
        {
            SignUpResponse response = (SignUpResponse)object;
            System.out.println(response.getMessage());

            System.out.println("SIGN UP RESPONSE RECEIVED isRegistered = " + response.isRegisterSuccessFull());

            SignupController.getInstance().setRegistered(response.isRegisterSuccessFull());
        }

        if(object instanceof SignInResponse)
        {
            SignInResponse response = (SignInResponse)object;

            System.out.println("message = " + response.getMessage());
            System.out.println("userObject full name = " + response.getClientUserObject().getFullName());

            //TODO Either here, or after the user loads his data using UUID send the users entire object to client
            LoginController.getInstance().setCanLogin(response.canLogin(), response.getClientUserObject());
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
