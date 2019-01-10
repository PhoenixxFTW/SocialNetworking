package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.phoenixx.client.application.ClientMain;
import com.phoenixx.client.handlers.AlertHandler;
import com.phoenixx.client.handlers.ClientDataHandler;
import com.phoenixx.client.network.ClientNetworkMain;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import com.phoenixx.packets.requests.GeneralRequest;
import com.phoenixx.packets.requests.PostDataRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadPostScreen implements Initializable
{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane homeScreenAnchorPane;

    @FXML
    private ScrollPane postScroll_pane;

    @FXML
    private Label postTitle_text;

    @FXML
    private Text post_text;

    @FXML
    private JFXButton authorName_button;

    @FXML
    private JFXButton authorUsername_button;

    @FXML
    private JFXButton authorUuid_button;

    @FXML
    private JFXButton deletePostButton;

    @FXML
    private Label postID_text;

    @FXML
    private Label dateCreated_text;

    @FXML
    private Label tags_text;

    private ClientUserObject postOwnerObject;
    private ClientUserObject clientUserObject;
    private PostDataObject postDataObject;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setHomeScreenAnchorPane(AnchorPane homeScreenAnchorPane) {
        this.homeScreenAnchorPane = homeScreenAnchorPane;
    }

    public void setPostOwner(ClientUserObject postOwner)
    {
        this.postOwnerObject = postOwner;

        this.authorName_button.setText(postOwner.getFullName());
        this.authorUsername_button.setText(postOwner.getUsername());
        this.authorUuid_button.setText(postOwner.getUuid());

    }

    public void setClientUser(ClientUserObject clientUser)
    {
        this.clientUserObject = clientUser;

        if(clientUser.getUuid().equals(postOwnerObject.getUuid()))
        {
            deletePostButton.setVisible(true);
            deletePostButton.setDisable(false);
        }
    }

    public void setPostData(PostDataObject postData)
    {
        postScroll_pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.postDataObject = postData;

        this.postTitle_text.setText(postData.getPostTile());
        this.postID_text.setText(String.valueOf(postData.getPostID()));
        this.dateCreated_text.setText(postData.getDateCreated());
        this.tags_text.setText(postData.getTags());
        this.post_text.setText(postData.getPostText());
    }

    public void handleAuthorClicked(MouseEvent mouseEvent)
    {
        System.out.println("Author buttons clicked!");

        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileScreen.fxml"));
            root = loader.load();

            ProfileScreenController profileScreenController = loader.getController();
            profileScreenController.setupUser(clientUserObject, postOwnerObject);

        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            anchorPane.getChildren().setAll(root);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public void handleBackButtonClick(MouseEvent mouseEvent)
    {
        PostDataRequest request = new PostDataRequest();
        request.setRequestID(2);

        ClientMain.getNetworkManager().sendMessageToServer(request);

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

    public void onDeleteClicked(ActionEvent actionEvent)
    {
        if(clientUserObject.getUuid().equalsIgnoreCase(postOwnerObject.getUuid()))
        {
            System.out.println("Delete post button was clicked!");
            GeneralRequest generalRequest = new GeneralRequest();
            generalRequest.setRequestID(1);
            generalRequest.setData(String.valueOf(postDataObject.getPostID()));

            ClientMain.getNetworkManager().sendMessageToServer(generalRequest);
        }
    }
}
