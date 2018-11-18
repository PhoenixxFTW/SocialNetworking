package com.phoenixx.client.controllers;

import com.phoenixx.client.application.ClientMain;
import com.phoenixx.client.handlers.ClientDataHandler;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import com.phoenixx.packets.requests.CreatePostRequest;
import com.phoenixx.packets.requests.PostDataRequest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatePostController  implements Initializable
{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField titleText;

    @FXML
    private TextField tagsText;

    @FXML
    private TextArea postText;

    @FXML
    private Label missingEntryText;

    private ClientUserObject clientUserObject;

    private static CreatePostController instance;

    public static CreatePostController getInstance() {
        return instance;
    }

    //TODO Setup parsing system as well as formatting (User clicks bold for example, which add's '<b> </b>' and all text between that will be bold or something)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public void setClientUser(ClientUserObject clientUser)
    {
        this.clientUserObject = clientUser;
    }

    public void setPostCreated(boolean success)
    {
        if(success)
        {
            Platform.runLater(()->{
                missingEntryText.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                missingEntryText.setText("Successfully posted!");
                missingEntryText.setVisible(true);
            });
        } else {
            Platform.runLater(()->{
                missingEntryText.setTextFill(Paint.valueOf(Color.RED.toString()));
                missingEntryText.setText("Failed to create post");
                missingEntryText.setVisible(true);
            });
        }

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

    public void handleSubmitButtonClick(ActionEvent actionEvent)
    {
        String postTitle = this.titleText.getText();
        String postTags = this.tagsText.getText();
        String postText = this.postText.getText();

        if((postTitle != null && !postTitle.isEmpty()) && (postTags != null && !postTags.isEmpty()) && (postText != null && !postText.isEmpty()))
        {
            PostDataObject postDataObject = new PostDataObject();
            CreatePostRequest createPostRequest = new CreatePostRequest();

            String ownerUUID = this.clientUserObject.getUuid();
            String ownerName = this.clientUserObject.getFullName();

            postDataObject.setOwnerUUID(ownerUUID);
            postDataObject.setOwnerName(ownerName);
            postDataObject.setPostTile(this.titleText.getText());
            postDataObject.setTags(this.tagsText.getText());
            postDataObject.setPostText(this.postText.getText());

            createPostRequest.setPostDataObject(postDataObject);
            createPostRequest.setOwnerUUID(ownerUUID);
            createPostRequest.setOwnerName(ownerName);

            ClientMain.getNetworkManager().sendMessageToServer(createPostRequest);

            PostDataRequest request = new PostDataRequest();
            request.setRequestID(2);

            ClientMain.getNetworkManager().sendMessageToServer(request);
        } else {
            missingEntryText.setVisible(true);
        }
    }
}
