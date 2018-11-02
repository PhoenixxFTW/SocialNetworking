package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ChevroletController implements Initializable {

	    @FXML
		private JFXButton back;
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
