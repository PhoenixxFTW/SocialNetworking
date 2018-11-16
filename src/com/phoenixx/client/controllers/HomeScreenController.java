package com.phoenixx.client.controllers;

import com.phoenixx.packets.objects.ClientUserObject;
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
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable
{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label fullName;

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

    private String uuid;

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

        //TODO Get image from ClientObject / Database
        setProfilePic("https://i.imgur.com/rLTnjje.png");

        try{
        } catch(Exception ex) {
            System.out.println("There was an error while setting the name text! setUUID method in HomeScreenController");
            ex.printStackTrace();
        }
    }

    public void handleMenuButtonClick(ActionEvent actionEvent)
    {
        //TODO Setup posts instead of "items"
        if (actionEvent.getSource() == homeButton) {
            Node[] nodes = new Node[10];
            for (int i = 0; i < nodes.length; i++) {
                try {

                    final int j = i;
                    nodes[i] = FXMLLoader.load(getClass().getResource("/com/phoenixx/client/fxml/Item.fxml"));

                    //give the items some effect

                    nodes[i].setOnMouseEntered(event -> {
                        nodes[j].setStyle("-fx-background-color : #0A0E3F");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[j].setStyle("-fx-background-color : #02030A");
                    });
                    postListPane.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            signOutButton.getScene().getWindow().hide();

            Stage login = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/phoenixx/client/fxml/LoginScreen.fxml"));

                Scene scene = new Scene(root);
                login.setScene(scene);
                login.show();
                login.setResizable(false);
            } catch (IOException e) {

                e.printStackTrace();
            }
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
