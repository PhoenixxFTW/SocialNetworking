package com.phoenixx.client.controllers;

import com.phoenixx.client.handlers.ClientDataHandler;
import com.phoenixx.packets.objects.ClientUserObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileScreenController implements Initializable
{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label fullNameText;

    @FXML
    private Label usernameText;

    @FXML
    private Label studentNumberText;

    @FXML
    private Label dateJoinedText;

    @FXML
    private Label moodText;

    /** This is the user that made the request to view someones profile*/
    private ClientUserObject clientUserObject;

    /** This is the user that we're viewing*/
    private ClientUserObject userObject;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setupUser(ClientUserObject clientUserObject, ClientUserObject userToView)
    {
        this.clientUserObject = clientUserObject;
        this.userObject = userToView;

        if(userToView.getProfilePicUrl() != null){
            this.profilePic.setImage(new Image(userToView.getProfilePicUrl()));
        }

        this.fullNameText.setText(userToView.getFullName());
        this.usernameText.setText(userToView.getUsername());
        this.studentNumberText.setText(userToView.getStudentNumber());
        this.dateJoinedText.setText(userToView.getDateJoined());
        this.moodText.setText(userToView.getMood());
    }

    public void handleAddEditAction(ActionEvent actionEvent)
    {

    }

    public void handleBackButtonClick(MouseEvent mouseEvent)
    {
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeScreen.fxml"));
            root = loader.load();

            HomeScreenController homeScreenController = loader.getController();
            homeScreenController.setClientUser(clientUserObject);
            homeScreenController.setupPosts(ClientDataHandler.loadedPosts);

        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            anchorPane.getChildren().setAll(root);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
