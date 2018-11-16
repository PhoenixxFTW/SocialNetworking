package com.phoenixx.client.application;

import com.phoenixx.client.network.ClientNetworkMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientMain extends Application
{
	public static Stage parentWindow;

    public static final String ICON_IMAGE_LOC = "icon.png";

	private static ClientNetworkMain networkManager;

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

    @Override
	public void start(Stage primaryStage) {
		try {
			parentWindow = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));

			Scene scene = new Scene(root,1187,664);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Phoenix Studios");
			primaryStage.setResizable(false);

            setStageIcon(primaryStage);

			primaryStage.setOnCloseRequest(event -> {
                getNetworkManager().shutdown();
            });

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
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
