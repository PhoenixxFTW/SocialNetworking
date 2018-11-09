package com.phoenixx.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HomePageController implements Initializable {

	@FXML 
	public AnchorPane holderPane;
	
	@FXML
    private Text welcome;
	
    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXToolbar toolBar;

    @FXML
    private HBox toolBarRight;

    @FXML
    private Label lblMenu;

    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnExit;
   
	
	AnchorPane home;
	
	private static HomePageController instance;
	
	public HomePageController()
	{
		instance = this;
	}
	
	public static HomePageController getInstance()
	{
		return instance;
	}	
  
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	   JFXRippler rippler = new JFXRippler(lblMenu);
	   rippler.setMaskType(JFXRippler.RipplerMask.RECT);
	   toolBarRight.getChildren().add(rippler);
	  
	   openMenus();
       createPage(home, "/com/phoenixx/client/fxml/HOME.fxml");
      
       setUsername(LoginController.getInstance().getUsername());
      
	}

	
	public void setUsername(String user)
	{
		this.welcome.setText("Welcome, "+user);
	}
	
	

	private void openMenus() {
	   
		JFXPopup pop = new JFXPopup();
		//pop.setContent(overflowContainer);
		//pop.setPopupContainer(anchor);
		//pop.setSource(lblMenu);
		
		lblMenu.setOnMouseClicked(e ->{
			
			//pop.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -1, 42);
			
		});
	}


	public void setNode(Node node)
	{
		holderPane.getChildren().clear();
		holderPane.getChildren().add((Node) node);
		
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
		
		
	}

	public void createPage(AnchorPane homeN,String loc) {
		
		try {
			homeN = FXMLLoader.load(getClass().getResource(loc));
			setNode(homeN);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@FXML
	public void homeBtn(ActionEvent he)
	{
		 setNode(home);
		
	}
	
	 @FXML
	public void exit(ActionEvent event) {

		 Platform.exit();
		 
	    }

    @FXML
	public void logOut(ActionEvent event) {
    
    	btnLogOut.getScene().getWindow().hide();
    	
   	    Stage login = new Stage();
   	    Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/com/phoenixx/client/fxml/LoginMain.fxml"));
		
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
	  }
	
	
}
