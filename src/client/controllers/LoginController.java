package client.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import client.dBConnection.DBHandler;

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
        connection = handler.getConnection();
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
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username and Password Is Not Correct");
                alert.show();*/
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
        }
	}
	
	@FXML
	public void signUpAction(ActionEvent e1)
	{
        //signInButton.getScene().getWindow().hide();

        loadUI("/client/fxml/SignupScreen.fxml");

        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/client/fxml/SignupScreen.fxml"));
            Scene newScene = new Scene(root);

            Stage mainStage;
            mainStage = ClientMain.parentWindow;
            mainStage.setScene(newScene);
        } catch (Exception e){
            e.printStackTrace();
        }*/

		
		/*Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/client/fxml/SignupScreen.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);*/
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
}
