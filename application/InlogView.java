package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

import entity.Account;

public class InlogView extends Application {

	private String username;
	private String password;
	private String confirmpass;
    private String alertmsg="";

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        BorderPane layout = new BorderPane();
       
        AnchorPane center = new AnchorPane();
        
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("Edit");	
		MenuItem saveItem = new MenuItem("Save");
		MenuItem loadItem = new MenuItem("Open");
		MenuItem exitItem = new MenuItem("Close Program");
							
		fileMenu.getItems().addAll(saveItem,loadItem,exitItem);
		
		Menu fileHelp = new Menu("Help");		
		MenuItem aboutItem = new MenuItem("About");
		
		Menu fileStatistics = new Menu("Statistics");
		MenuItem statisticsItem = new MenuItem("View Highscore");
		
		fileHelp.getItems().addAll(aboutItem);
		
		fileStatistics.getItems().addAll(statisticsItem);
		
		menuBar.getMenus().addAll(fileMenu,fileHelp,fileStatistics);
		layout.setTop(menuBar);
		
		Label account = new Label("Skee-ball");
		center.setRightAnchor(account, 110.0);
		center.setLeftAnchor(account, 180.0);
		
		Label label1 = new Label("Username");
		center.setTopAnchor(label1, 10.0);
		center.setLeftAnchor(label1, 10.0);
		
		Label label = new Label("Password");
		center.setTopAnchor(label, 70.0);
		center.setLeftAnchor(label, 10.0);
		
		TextField txtName = new TextField("");
		center.setTopAnchor(txtName, 30.0);
		center.setLeftAnchor(txtName, 10.0);
		
		TextField txtName3=new TextField("");
		center.setTopAnchor(txtName3, 90.0);
		center.setLeftAnchor(txtName3, 10.0);
		
		Button okbutton = new Button("Log in");
		center.setTopAnchor(okbutton, 350.0);
		center.setLeftAnchor(okbutton, 330.0);
			
		layout.setCenter(center);
		center.getChildren().addAll(label,label1,okbutton,txtName, account,txtName3);
		
		Scene scene = new Scene(layout,500,500);
        
        primaryStage.setScene(scene);
        primaryStage.show();
       
        //Platform.exit() kallas för att stänga programmet. 
		exitItem.setOnAction(e -> Platform.exit());

		aboutItem.setOnAction(e -> {
			
			Alert aboutMessage = new Alert(AlertType.INFORMATION, "Create an account"); 
			aboutMessage.showAndWait();
			
		});	
	
		/*okbutton.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Now you have an account!");
			alert.setContentText("Welcome!");

			alert.showAndWait();
		}
		
		);
		*/
		
    }
    public void checkUser(String user, String password, String confirmpass){
    	if(username.length() < 5 ){
    		alertmsg=("The username is not long enough, please try again!");
    	}
    	else if(password.length() < 5){
    		alertmsg=("You have to atleast type in six characters for the password!");
    }else{
    		alertmsg=("You are logged in!");
    		
    	}
}
   public Account loginUser(String username, String password){
	return null;

    }
	
	private List <Account> AccountList;
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
