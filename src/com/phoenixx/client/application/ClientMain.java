package com.phoenixx.client.application;

import com.phoenixx.client.network.ClientNetworkMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ClientMain extends Application
{
	public static Stage parentWindow;

	private static ClientNetworkMain networkManager;

    @Override
	public void start(Stage primaryStage) {
		try {
			parentWindow = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("/com/phoenixx/client/fxml/LoginScreen.fxml"));

			Scene scene = new Scene(root,1187,664);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Phoenix Studios");
			primaryStage.setResizable(false);

			primaryStage.setOnCloseRequest(event -> {
                getNetworkManager().shutdown();
            });


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

        /*if(ClientNetworkMain.shouldConnect)
        {
            try {
                ClientNetworkMain.initializeClientConnection(ClientNetworkMain.NETWORK_IP, ClientNetworkMain.NETWORK_TCP_PORT, ClientNetworkMain.NETWORK_UDP_PORT);

            } catch (IOException e) {
                e.printStackTrace();
                ClientNetworkMain.attemptReconnect();
            }
        }*/

        newNetworkManager();
        launch(args);
    }

	public static void newNetworkManager()
	{
		networkManager = ClientNetworkMain.connect();
	}

	public static ClientNetworkMain getNetworkManager()
	{
		return networkManager;
	}
}
