package com.phoenixx.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phoenixx.client.application.ClientMain;
import com.phoenixx.client.dBConnection.DBHandler;
import com.phoenixx.packets.objects.SignUpObject;
import com.phoenixx.packets.requests.SignUpRequest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.UUID;

public class SignupController implements Initializable
{

    //TODO setup password encryption

    @FXML
    private AnchorPane anchorPane;

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
    private Label missingEntry;

    @FXML
    private Rectangle passwordDetection;

    @FXML
    private JFXButton registerButton;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    private static SignupController instance;

    public static SignupController getInstance() {
        return instance;
    }

    public void setRegistered(boolean isRegistered)
    {
        if(!isRegistered)
        {
            Platform.runLater(()->{
                missingEntry.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                missingEntry.setText("Registered Successfully!");
                missingEntry.setVisible(true);
            });
        } else {
            Platform.runLater(()->{
                missingEntry.setTextFill(Paint.valueOf(Color.RED.toString()));
                missingEntry.setText("User already exists!");
                missingEntry.setVisible(true);
            });
        }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
    {
        instance = this;
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
	    if(ClientMain.getNetworkManager().client.isConnected())
	    {
	        //TODO Add a check to see if the email is valid
            //TODO Add a check to see if password is certain characters long
            //TODO Add a check to look for any vulgar language

            String studentNumber = student_number.getText();
            String usernameGiven = username.getText();
            String fullName = full_name.getText();
            String emailGiven = email.getText();
            String passwordGiven = password.getText();

            boolean isMissingEntry = fullName.isEmpty() || studentNumber.isEmpty() || usernameGiven.isEmpty() || emailGiven.isEmpty() || passwordGiven.isEmpty() || verified_password.getText().isEmpty();

            if(passwordGiven.equals(verified_password.getText()))
            {
                passwordsDoNotMatch.setVisible(false);
                passwordDetection.setVisible(false);
            } else {
                System.out.println("passwords do not match! Main pass: " + passwordGiven + " other pass: " + verified_password.getText());
                passwordsDoNotMatch.setVisible(true);
                passwordDetection.setVisible(true);
            }

            if(isMissingEntry)
            {
                System.out.println("==================================");
                System.out.println("full_name = " + fullName + " | isEmpty: " + fullName.isEmpty());
                System.out.println("student_number = " + studentNumber + " | isEmpty: " + studentNumber.isEmpty());
                System.out.println("email = " + emailGiven + " | isEmpty: " + emailGiven.isEmpty());
                System.out.println("password = " + passwordGiven + " | isEmpty: " + passwordGiven.isEmpty());
                System.out.println("verified_password = " + verified_password.getText() + " | isEmpty: " + verified_password.getText().isEmpty());
                System.out.println("Missing an entry!");

                missingEntry.setTextFill(Paint.valueOf(new Color(1, 0.2, 0.2, 1).toString()));
                missingEntry.setText("You are missing an entry!");
                missingEntry.setVisible(true);
                return;
            } else {
                missingEntry.setVisible(false);
            }

            if(!fullName.isEmpty() && !studentNumber.isEmpty() && !usernameGiven.isEmpty() && !emailGiven.isEmpty() && !passwordGiven.isEmpty() && !verified_password.getText().isEmpty() && (passwordGiven.equals(verified_password.getText())))
            {
                String uuid = generateUUID().toString();

                SignUpRequest request = new SignUpRequest();
                SignUpObject signUpObject = new SignUpObject();

                signUpObject.setUuid(uuid);
                signUpObject.setStudentNumber(studentNumber);
                signUpObject.setFullName(fullName);
                signUpObject.setEmail(emailGiven);
                signUpObject.setUsername(usernameGiven);
                signUpObject.setPassword(passwordGiven);

                request.setSignUpObject(signUpObject);

                ClientMain.getNetworkManager().sendMessageToServer(request);

            } else {
                System.out.println("DATA IS EMPTY");

                missingEntry.setTextFill(Paint.valueOf(new Color(1, 0.2, 0.2, 1).toString()));
                missingEntry.setText("You are missing an entry!");
                missingEntry.setVisible(true);
            }
        } else {
            missingEntry.setTextFill(Paint.valueOf(new Color(1, 0.2, 0.2, 1).toString()));
            missingEntry.setText("Error processing request! Please try again later.");
            missingEntry.setTranslateX(-60);
            missingEntry.setVisible(true);
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

    public void backButtonClickAction(MouseEvent mouseEvent)
    {
        loadUI("/com/phoenixx/client/fxml/LoginScreen.fxml");
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

	

