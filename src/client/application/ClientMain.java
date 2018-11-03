package client.application;

import client.network.ClientNetworkMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class ClientMain extends Application
{

	public static Stage parentWindow;

    @Override
	public void start(Stage primaryStage) {
		try {
			parentWindow = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("/client/fxml/LoginScreen.fxml"));

			Scene scene = new Scene(root,1187,664);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Phoenix Studios");
			primaryStage.setResizable(false);


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
        /*if(ClientNetworkMain.shouldConnect)
        {
            try {
                ClientNetworkMain.initializeClientConnection(ClientNetworkMain.NETWORK_IP, ClientNetworkMain.NETWORK_TCP_PORT, ClientNetworkMain.NETWORK_UDP_PORT);

            } catch (IOException e) {
                e.printStackTrace();
                ClientNetworkMain.attemptReconnect();
            }
        }*/

        launch(args);
	}
}
