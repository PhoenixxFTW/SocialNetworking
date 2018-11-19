package com.phoenixx.client.controllers;

import com.phoenixx.client.application.ClientMain;
import com.phoenixx.packets.objects.PostDataObject;
import com.phoenixx.packets.requests.PostDataRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable
{
    @FXML
    private Label title_text;

    @FXML
    private Label owner_text;

    @FXML
    private Label date_text;

    @FXML
    private Label tags_text;

    @FXML
    private Tooltip tagsToolTip;

    private PostDataObject postDataObject;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setData(String titleText, String ownerText, String dateText, String tagTest)
    {
        title_text.setText(titleText);
        owner_text.setText(ownerText);
        date_text.setText(dateText);
        tags_text.setText(tagTest);
    }

    public void setData(PostDataObject postDataObject)
    {
        this.postDataObject = postDataObject;
        title_text.setText(postDataObject.getPostTile());
        owner_text.setText("By: "+postDataObject.getOwnerName());
        date_text.setText("Created on: "+postDataObject.getDateCreated());
        tags_text.setText("Tag(s): "+postDataObject.getTags());
        tagsToolTip.setText("Tag(s): "+postDataObject.getTags());
    }

    public void onPostClicked(MouseEvent mouseEvent)
    {
        PostDataRequest request = new PostDataRequest();
        request.setRequestID(1);
        request.setPostID(this.postDataObject.getPostID());
        request.setUserUUID(this.postDataObject.getOwnerUUID());

        ClientMain.getNetworkManager().sendMessageToServer(request);
    }
}
