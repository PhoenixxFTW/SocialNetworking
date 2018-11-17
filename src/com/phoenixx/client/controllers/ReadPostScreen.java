package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadPostScreen implements Initializable
{
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
    private Label postID_text;

    @FXML
    private Label dateCreated_text;

    @FXML
    private Label tags_text;

    private ClientUserObject clientUserObject;
    private PostDataObject postDataObject;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setClientUser(ClientUserObject clientUser)
    {
        this.clientUserObject = clientUser;

        this.authorName_button.setText(clientUser.getFullName());
        this.authorUsername_button.setText(clientUser.getUsername());
        this.authorUuid_button.setText(clientUser.getUuid());
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
        //TODO Get author data and then load a profile screen
        System.out.println("Author buttons clicked!");

    }
}
