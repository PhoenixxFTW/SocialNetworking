package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	  
		HomePageController.getInstance().createPage(CarsAnchor, "/FXML/Maruti.fxml");
		
	  
	}
	
	 @FXML
	 public void chevroletAction(ActionEvent event) {

		 HomePageController.getInstance().createPage(CarsAnchor, "/FXML/Chevrolet.fxml");
		
		 
	  }

	 @FXML
	 public void hyundaiAction(ActionEvent event) {

		
		 HomePageController.getInstance().createPage(CarsAnchor, "/FXML/Hyundai.fxml");
		 	  
	 }


	  @FXML
	  public void tataMotorsAction(ActionEvent event) {

		  HomePageController.getInstance().createPage(CarsAnchor, "/FXML/TataMotors.fxml");
		   
	   }
	

	
	
}
