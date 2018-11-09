package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.phoenixx.client.utils.ClientInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    private static HomeScreenController instance;

    public HomeScreenController()
    {
        instance = this;
        //nameButton.setText(ClientInfo.username);
    }

    public static HomeScreenController getInstance()
    {
        return instance;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nameButton.setStyle("-fx-text-inner-color: #a0a2ab;");
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
