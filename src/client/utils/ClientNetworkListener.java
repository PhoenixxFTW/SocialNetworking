package client.utils;

import client.controllers.SignupController;
import client.network.ClientNetworkMain;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.responses.SignUpResponse;

public class ClientNetworkListener extends Listener {

    @Override
    public void received(Connection connection, Object object)
    {
        if(object instanceof SignUpResponse)
        {
            SignUpResponse response = (SignUpResponse)object;
            System.out.println(response.getMessage());

            SignupController.getInstance().setRegistered(response.isRegisterSuccessFull());
        }
    }

    @Override
    public void connected(Connection arg) {
        // This is for when the Cardinal comes back online and connects to the client
        System.out.println("[Client] Connected to Management Server! [Cardinal_MessageListenerClient.connected]");

        /*if(CardinalClient.client.isConnected() && !CardinalClientVariables.connectedToCardinal)
        {
            CardinalClient.sendLoginInfo();
        }*/
    }

    @Override
    public void disconnected(Connection arg) {
        System.out.println("[Client] Disconnected from Management server! [ClientNetworkListener.disconnected]");

        if (ClientNetworkMain.shouldConnect) {
            ClientNetworkMain.attemptReconnect();
        }
    }


}
