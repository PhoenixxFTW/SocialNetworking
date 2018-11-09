package com.phoenixx.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private JFXButton maruti;

    @FXML
    private JFXButton hyundai;

    @FXML
    private JFXButton chevrolet;

    @FXML
    private JFXButton tataMotors;

    private AnchorPane CarsAnchor;
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
 
		
	}
	
	@FXML
	public void marutiAction(ActionEvent e)
	{
	  
		HomePageController.getInstance().createPage(CarsAnchor, "/com/phoenixx/client/fxml/Maruti.fxml");
		
	  
	}
	
	 @FXML
	 public void chevroletAction(ActionEvent event) {

		 HomePageController.getInstance().createPage(CarsAnchor, "/com/phoenixx/client/fxml/Chevrolet.fxml");
		
		 
	  }

	 @FXML
	 public void hyundaiAction(ActionEvent event) {

		
		 HomePageController.getInstance().createPage(CarsAnchor, "/com/phoenixx/client/fxml/Hyundai.fxml");
		 	  
	 }


	  @FXML
	  public void tataMotorsAction(ActionEvent event) {

		  HomePageController.getInstance().createPage(CarsAnchor, "/com/phoenixx/client/fxml/TataMotors.fxml");
		   
	   }

}
