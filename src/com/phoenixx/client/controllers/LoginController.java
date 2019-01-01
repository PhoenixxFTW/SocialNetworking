package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phoenixx.client.application.ClientMain;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import com.phoenixx.packets.requests.SignInRequest;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton signInButton;

    @FXML
	private JFXButton signup;

	@FXML
	public JFXTextField username;

	@FXML
	private JFXCheckBox remember;

    @FXML
    private Label noUserFound;

    @FXML
	private JFXButton forgotpassword;

	@FXML
	private JFXPasswordField password;

    @FXML
    private Rectangle notification;

    @FXML
    private Label notificationTitle;

    @FXML
    private Label notificationText;

    @FXML
    private ImageView notificationSymbol;

    private boolean canLogin = false;

	private static LoginController instance;

	public static String usernameGiven1;

	public LoginController()
	{
		instance = this;
	}

	public static LoginController getInstance()
	{
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");
	}

	@FXML
	public void signInAction(ActionEvent e)
    {
        if(ClientMain.getNetworkManager().client.isConnected())
        {
            String usernameGiven = username.getText();
            String passwordGiven = password.getText();

            usernameGiven1=usernameGiven;

            if(!usernameGiven.isEmpty() && !passwordGiven.isEmpty())
            {
                SignInRequest request = new SignInRequest();
                request.setUsername(usernameGiven);
                request.setPassword(passwordGiven);

                ClientMain.getNetworkManager().sendMessageToServer(request);
            } else {
                noUserFound.setVisible(true);
                noUserFound.setText("Missing Entry!");
            }

        } else {
            showNotification();
        }
	}
	
	@FXML
	public void signUpAction(ActionEvent e1)
	{
        if(!ClientMain.getNetworkManager().client.isConnected())
        {
            showNotification();
        } else {
            loadUI("/fxml/SignupScreen.fxml");
        }
	}

	@FXML
	public void forgotPassAction(ActionEvent e1) throws IOException
	{

    }

    public String getUsername()
    {
        return username.getText();
    }

    private void loadUI(String ui)
    {
        Parent root = null;

        try{
            root = FXMLLoader.load(getClass().getResource(ui));
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            anchorPane.getChildren().setAll(root);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public void setCanLogin(boolean canLogin1, ClientUserObject clientUserObject, ArrayList<PostDataObject> postDataObjects) {
	    if(canLogin1)
	    {
            Platform.runLater(()->{
                this.canLogin = canLogin1;

                Parent root = null;
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeScreen.fxml"));
                    root = loader.load();

                    HomeScreenController homeScreenController = loader.getController();
                    homeScreenController.setClientUser(clientUserObject);
                    homeScreenController.setupPosts(postDataObjects);

                } catch (IOException e){
                    e.printStackTrace();
                }
                try{
                    anchorPane.getChildren().setAll(root);
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }

            });
        } else {
            noUserFound.setVisible(true);
        }
    }

    public void showNotification(/*String title, String message*/)
    {
        notification.setVisible(true);
        notificationTitle.setVisible(true);
        notificationText.setVisible(true);
        notificationSymbol.setVisible(true);

        TranslateTransition notificationBox=new TranslateTransition(new Duration(350), notification);
        notificationBox.setToY(-(notification.getHeight()));
        TranslateTransition closeNav=new TranslateTransition(new Duration(350), notification);

        if(notification.getTranslateY()!=0){
            notificationBox.play();
        }else{
            closeNav.setToY(0);
            closeNav.play();
        }

        TranslateTransition notificationTitleAnimation=new TranslateTransition(new Duration(350), notificationTitle);
        notificationTitleAnimation.setToY(-(notificationTitle.getHeight()));
        TranslateTransition closeNotificationTitle=new TranslateTransition(new Duration(350), notificationTitle);

        if(notificationTitle.getTranslateY()!=0){
            notificationTitleAnimation.play();
        }else{
            closeNotificationTitle.setToY(0);
            closeNotificationTitle.play();
        }

        TranslateTransition notificationTextAnimation=new TranslateTransition(new Duration(350), notificationText);
        notificationTextAnimation.setToY(-(notification.getHeight()));
        TranslateTransition closeNotificationText=new TranslateTransition(new Duration(350), notificationText);

        if(notificationText.getTranslateY()!=0){
            notificationTextAnimation.play();
        }else{
            closeNotificationText.setToY(0);
            closeNotificationText.play();
        }

        TranslateTransition notificationSymbolAnimation=new TranslateTransition(new Duration(350), notificationSymbol);
        notificationTextAnimation.setToY(-(notification.getHeight()));
        TranslateTransition closeNotificationSymbol=new TranslateTransition(new Duration(350), notificationSymbol);

        if(notificationSymbol.getTranslateY()!=0){
            notificationSymbolAnimation.play();
        }else{
            closeNotificationSymbol.setToY(0);
            closeNotificationSymbol.play();
        }
    }
}
