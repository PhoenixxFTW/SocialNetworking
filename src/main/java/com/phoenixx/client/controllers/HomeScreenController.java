package com.phoenixx.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.phoenixx.client.handlers.ClientDataHandler;
import com.phoenixx.packets.objects.ClientUserObject;
import com.phoenixx.packets.objects.PostDataObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable
{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label fullName;

    @FXML
    private Button profileButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button friendsButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button signOutButton;

    @FXML
    private ImageView profilePic;

    @FXML
    private ScrollPane postsPane;

    @FXML
    private VBox postListPane = null;

    @FXML
    private JFXButton createPostButton;

    private ClientUserObject clientUserObject;

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

    }

    public void setClientUser(ClientUserObject clientUser)
    {
        this.clientUserObject = clientUser;

        this.fullName.setText(clientUser.getFullName());

        if(clientUser.getProfilePicUrl() != null && !clientUser.getProfilePicUrl().isEmpty())
        {
            setProfilePic(clientUser.getProfilePicUrl());
        }

        //setProfilePic("https://i.imgur.com/rLTnjje.png");
    }

    public void handleMenuButtonClick(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == profileButton)
        {
            Parent root = null;
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileScreen.fxml"));
                root = loader.load();

                ProfileScreenController profileScreenController = loader.getController();
                profileScreenController.setupUser(clientUserObject, clientUserObject);

            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                anchorPane.getChildren().setAll(root);
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        if (actionEvent.getSource() == homeButton) {

            System.out.println("Home button pressed!!!");
        }
        if (actionEvent.getSource() == friendsButton) {
            System.out.println("Friends button pressed!!!");
        }
        if (actionEvent.getSource() == settingsButton) {
            System.out.println("Settings button pressed!!!");
        }

        //FIXME Either remove this, OR be sure you clear ALL client data related to the last account logged in
        if(actionEvent.getSource()== signOutButton)
        {
            ClientDataHandler.loadedPosts.clear();
            signOutButton.getScene().getWindow().hide();

            Stage login = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/LoginScreen.fxml"));

                Scene scene = new Scene(root);
                login.setScene(scene);
                login.show();
                login.setResizable(false);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void setupPosts(ArrayList<PostDataObject> postDataObjects)
    {
        try {
            postsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            Node[] nodes = new Node[postDataObjects.size()];

            for (int i = 0; i < postDataObjects.size(); i++) {
                try {

                    final int j = i;

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Post.fxml"));

                    nodes[i] = loader.load();

                    PostDataObject postDataObject = postDataObjects.get(i);

                    PostController postController = (PostController) loader.getController();

                    if(postController == null)
                    {
                        System.out.println("CONTROLLER IS NULL");
                    }

                    postController.setData(postDataObject);

                    nodes[i].setOnMouseEntered(event -> {
                        nodes[j].setStyle("-fx-background-color : #0A0E3F");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[j].setStyle("-fx-background-color : #02030A");
                    });
                    postListPane.getChildren().add(nodes[i]);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public void setProfilePic(String url)
    {
        this.profilePic.setImage(new Image(url));

        Rectangle clip = new Rectangle(
                profilePic.getFitWidth(), profilePic.getFitHeight()
        );

        clip.setArcWidth(60);
        clip.setArcHeight(60);
        clip.setStroke(Color.SEAGREEN);
        clip.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        profilePic.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profilePic.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        profilePic.setClip(null);

        // apply a shadow effect.
        profilePic.setEffect(new DropShadow(20, Color.GREEN));

        // store the rounded image in the imageView.
        profilePic.setImage(image);
    }

    public void loadPost(PostDataObject postDataObject, ClientUserObject postOwnerObject) {
        Platform.runLater(()->{

            Parent root = null;
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReadPostScreen.fxml"));
                root = loader.load();

                ReadPostScreen readPostScreen = loader.getController();
                if(postOwnerObject != null)
                {
                    readPostScreen.setPostOwner(postOwnerObject);
                    readPostScreen.setClientUser(clientUserObject);
                } else {
                    System.out.println("POST OWNER WAS NULL");
                }

                readPostScreen.setPostData(postDataObject);
                readPostScreen.setHomeScreenAnchorPane(anchorPane);

            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                anchorPane.getChildren().setAll(root);
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        });
    }

    public void handleCreatePostButton(ActionEvent actionEvent)
    {
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreatePostScreen.fxml"));
            root = loader.load();

            CreatePostController createPostController = loader.getController();
            createPostController.setClientUser(clientUserObject);

        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            anchorPane.getChildren().setAll(root);
        }catch (NullPointerException ex){
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

        try{
            anchorPane.getChildren().setAll(root);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
