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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

import connectivity.GetNSetDb;
import entity.UserAccount;
/**
 * 
 * @author Soniiqaah
 *
 */
public class InlogView extends Application {

	private String username;
	private String password;
	private String confirmpass;
	private String alertmsg = "";
	private List<UserAccount> allUsers;
	private UserAccount user;
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text wrongLogin = new Text("Wrong username/password");
		wrongLogin.setVisible(false);
		
		BorderPane layout = new BorderPane();

		AnchorPane center = new AnchorPane();

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("Edit");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem loadItem = new MenuItem("Open");
		MenuItem exitItem = new MenuItem("Close Program");

		fileMenu.getItems().addAll(saveItem, loadItem, exitItem);

		Menu fileHelp = new Menu("Help");
		MenuItem aboutItem = new MenuItem("About");

		Menu fileStatistics = new Menu("Result");
		MenuItem resultItem = new MenuItem("View Highscore");

		fileHelp.getItems().addAll(aboutItem);

		fileStatistics.getItems().addAll(resultItem);

		menuBar.getMenus().addAll(fileMenu, fileHelp, fileStatistics);
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

		TextField txtUser = new TextField("");
		center.setTopAnchor(txtUser, 30.0);
		center.setLeftAnchor(txtUser, 10.0);

		TextField txtPw = new TextField("");
		center.setTopAnchor(txtPw, 90.0);
		center.setLeftAnchor(txtPw, 10.0);

		Button okbutton = new Button("Log in");
		center.setTopAnchor(okbutton, 350.0);
		center.setLeftAnchor(okbutton, 330.0);
		
		okbutton.setOnAction(event -> {
		user = loginUser(txtUser.getText(), txtPw.getText(), allUsers);
		if(user != null){
			okbutton.setText("Log out");
			wrongLogin.setVisible(false);
		}else{
			wrongLogin.setVisible(true);
		}
		
		}
		);

		layout.setCenter(center);
		center.getChildren().addAll(label, label1, okbutton, txtUser, account, txtPw, wrongLogin);

		Scene scene = new Scene(layout, 500, 500);

		primaryStage.setScene(scene);
		primaryStage.show();

		// Platform.exit() kallas för att stänga programmet.
		exitItem.setOnAction(e -> Platform.exit());

		aboutItem.setOnAction(e -> {

			Alert aboutMessage = new Alert(AlertType.INFORMATION, "Create an account");
			aboutMessage.showAndWait();

		});
	}

	public UserAccount loginUser(String username, String password, List<UserAccount> allUsers) {
		GetNSetDb gsdb = new GetNSetDb();
		allUsers = gsdb.getAccounts();
		for (UserAccount u : allUsers) {
			if (u.getUser().equals(username) && u.getPassword().equals(password)) {
				this.username = u.getUser();
				return u;
			}
		}
		return null;
		
	}

	private List<UserAccount> AccountList;

	public static void main(String[] args) {
		Application.launch(args);
	}

}
