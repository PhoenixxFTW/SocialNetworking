package client.controllers;


import client.dBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class SignupController implements Initializable
{

    @FXML
    private AnchorPane parentPane;

    @FXML
    private JFXTextField full_name;

    @FXML
    private JFXTextField student_number;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField verified_password;

    @FXML
    private Label passwordsDoNotMatch;

    @FXML
    private Rectangle passwordDetection;

    @FXML
    private JFXButton registerButton;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
    {
        full_name.setStyle("-fx-text-inner-color: #a0a2ab;");
        student_number.setStyle("-fx-text-inner-color: #a0a2ab;");
        email.setStyle("-fx-text-inner-color: #a0a2ab;");
        username.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color: #a0a2ab;");
        verified_password.setStyle("-fx-text-inner-color: #a0a2ab;");

	    handler = new DBHandler(); 
	}

	@FXML
    public void verifyPassTypedAction(ActionEvent e)
    {
        /*if(!password.getText().equals(verified_password.getText()))
        {
            passwordsDoNotMatch.setVisible(true);
            passwordDetection.setVisible(true);
        }*/
    }
		
	@FXML
	public void registerAction(ActionEvent e)
	{
        if(password.getText().equals(verified_password.getText()))
        {
            passwordsDoNotMatch.setVisible(false);
            passwordDetection.setVisible(false);
        } else {
            System.out.println("passwords do not match! Main pass: " + password.getText() + " other pass: " + verified_password.getText());
            passwordsDoNotMatch.setVisible(true);
            passwordDetection.setVisible(true);
        }

	    if(!full_name.getText().isEmpty() && !student_number.getText().isEmpty() && !email.getText().isEmpty()/* && !password.getText().isEmpty() && !verified_password.getText().isEmpty()*/)
	    {
            String insert = "INSERT INTO users(uuid,student_number,username,full_name,email,password) VALUES (?,?,?,?,?,?)";

            connection = handler.getConnection();
            try {
                pst = connection.prepareStatement(insert);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            try {
                String uuid = generateUUID().toString();
                String studentNumber = student_number.getText();
                String usernameGiven = username.getText();
                String fullName = full_name.getText();
                String emailGiven = email.getText();
                String passwordGiven = password.getText();

                pst.setString(1, uuid);
                pst.setString(2, studentNumber);
                pst.setString(3, usernameGiven);
                pst.setString(4, fullName);
                pst.setString(5, emailGiven);
                pst.setString(6, passwordGiven);

                System.out.println("UUID: " + uuid);
                System.out.println("student_number: " + studentNumber);
                System.out.println("username: " + usernameGiven);
                System.out.println("full_name: " + fullName);
                System.out.println("email: " + emailGiven);
                System.out.println("password: " + passwordGiven);

                pst.executeUpdate();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            Stage home = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/client/fxml/HOME.fxml"));

                Scene scene = new Scene(root);
                home.setScene(scene);
                home.show();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("DATA IS EMPTY");
        }

	}

    @FXML
    public void passwordKeyTypedAction(KeyEvent keyEvent)
    {
        if(keyEvent.getCode().toString().equals(password.getText()))
        {
            passwordDetection.setVisible(false);
            passwordsDoNotMatch.setVisible(false);
        }
    }

    public static UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}

	

