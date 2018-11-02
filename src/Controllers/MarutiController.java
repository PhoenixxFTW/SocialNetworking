package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MarutiController implements Initializable {

	 @FXML
	private JFXButton back;

     @FXML
    private AnchorPane MarutiAnchor;
	 
     AnchorPane CarInfo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	@FXML 
	public void backAction(ActionEvent e)
	{
		
		HomePageController.getInstance().createPage(CarInfo, "/FXML/HOME.fxml");
		
	}

	
	
   
      
	
}
