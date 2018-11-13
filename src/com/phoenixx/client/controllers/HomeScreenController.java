package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.phoenixx.client.application.ClientMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable
{
    @FXML
    private AnchorPane anchorPane;

    // Buttons
    @FXML
    private JFXButton nameButton;

    private String uuid;

    private static HomeScreenController instance;

    public HomeScreenController()
    {
        instance = this;
    }

    public static HomeScreenController getInstance()
    {
        return instance;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nameButton.setStyle("-fx-text-inner-color: #a0a2ab;");
        nameButton.setText(LoginController.getInstance().username.getText());
    }

    public void nameButtonClicked(MouseEvent mouseEvent)
    {
        //TODO Setup a settings menu
    }

    public void setUUID(String givenUuid)
    {
        uuid = givenUuid;
        try{
            nameButton.setText(givenUuid);
        } catch(Exception ex) {
            System.out.println("There was an error while setting the name text! setUUID method in HomeScreenController");
            ex.printStackTrace();
        }

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
