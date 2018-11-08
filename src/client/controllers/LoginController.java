package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import client.application.ClientMain;
import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import client.dBConnection.DBHandler;
import javafx.util.Duration;
import packets.requests.SignInRequest;

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

	private DBHandler handler;
	private Connection connection;
	private java.sql.PreparedStatement pst;
	private static LoginController instance;

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
		
		handler = new DBHandler();
	}

	@FXML
	public void signInAction(ActionEvent e)
    {
        if(ClientMain.getNetworkManager().client.isConnected())
        {

            String usernameGiven = username.getText();
            String passwordGiven = password.getText();

            if(!usernameGiven.isEmpty() && !passwordGiven.isEmpty())
            {
                SignInRequest request = new SignInRequest();
                request.setUsername(usernameGiven);
                request.setPassword(passwordGiven);

                ClientMain.getNetworkManager().sendMessageToServer(request);
            } else {
                //TODO Display missing entry text
            }

        } else {
            showNotification();
        }
        /*connection = handler.getConnection();
        String q1 = "SELECT * from users where username=? and password=?";

        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            ResultSet rs = pst.executeQuery();

            int count=0;

            while(rs.next())
            {
                count=count+1;
            }

            if(count==1)
            {
                signInButton.getScene().getWindow().hide();
                noUserFound.setVisible(false);

                Stage home = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/client/fxml/HOME.fxml"));

                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else
            {
                noUserFound.setVisible(true);
                *//*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and Password Is Not Correct");
                alert.show();*//*
                //progress.setVisible(false);
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        finally
        {
            try {
                connection.close();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }*/
	}
	
	@FXML
	public void signUpAction(ActionEvent e1)
	{
        if(!ClientMain.getNetworkManager().client.isConnected())
        {
            showNotification();
        } else {
            loadUI("/client/fxml/SignupScreen.fxml");
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

    public String getPassword()
    {
        return password.getText();
    }

    private void loadUI(String ui)
    {
        Parent root = null;

        try{
            root = FXMLLoader.load(getClass().getResource(ui));
        } catch (IOException e){
            e.printStackTrace();
        }
        anchorPane.getChildren().setAll(root);
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
