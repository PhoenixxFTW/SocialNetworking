package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import dBConnection.DBHandler;

public class LoginController implements Initializable {

	@FXML
	private JFXButton signup;

	@FXML
	public JFXTextField username;

	@FXML
	private JFXCheckBox remember;

	@FXML
	private JFXButton signInButton;

	@FXML
	private JFXButton forgotpassword;

	//@FXML
	//private ImageView progress;

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


	public String username()
	{
		return username.getText();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//progress.setVisible(false);
		username.setStyle("-fx-text-inner-color: #a0a2ab;");
		password.setStyle("-fx-text-inner-color: #a0a2ab;");
		
		handler = new DBHandler();
	}

	@FXML
	public void signInAction(ActionEvent e)
    {
		//loading bar
		//progress.setVisible(true);
		PauseTransition pt = new PauseTransition();
		pt.setDuration(Duration.seconds(3));
		pt.setOnFinished(ev -> {
		
			 //Retrive Data from Database
			
			connection = handler.getConnection();
			String q1 = "SELECT * from youtubers where names=? and password=?";
			
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
			    	
			    	Stage home = new Stage();
			    	try {
			    		
						Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
						
					    Scene scene = new Scene(root);
					    home.setScene(scene);
					    home.show();
			    	
			    	} catch (IOException e1) {
					
						e1.printStackTrace();
					}
			    	
			
			    }
			    else
			    {
			    	Alert alert = new Alert(Alert.AlertType.ERROR);
			    	alert.setHeaderText(null);
			    	alert.setContentText("Username and Password Is Not Correct");
			    	alert.show();
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
       });
		
		pt.play();


	}
	
	@FXML
	public void signUpAction(ActionEvent e1) throws IOException
	{
        signInButton.getScene().getWindow().hide();
		
		Stage signup = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUP.fxml"));
		Scene scene = new Scene(root);
		signup.setScene(scene);
		signup.show();
		signup.setResizable(false);
	}

	@FXML
	public void forgotPassAction(ActionEvent e1) throws IOException
	{

	}
}
