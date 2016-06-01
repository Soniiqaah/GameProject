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
 * @author Soniiqaah Creating a class InlogView that extends from Application.
 */
public class InlogView extends Application {
	/**
	 * Declare instance variables
	 */
	private String username;
	private String password;
	private String alertmsg = "";
	private List<UserAccount> allUsers;
	private static UserAccount user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/**
		 * Creating a fault printout text and setting it to visible false
		 */
		Text wrongLogin = new Text("Wrong username/password");
		wrongLogin.setVisible(false);
		
		/**
		 * Declaring a Borderpane layout and inside a Anchorpane
		 */
		BorderPane layout = new BorderPane();

		AnchorPane center = new AnchorPane();

		/**
		 * Creating a Menubar and menuitems
		 */
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("Close program");
		MenuItem exitItem = new MenuItem("Close");
		/**
		 * Getting the item exit and creating menu tabs for help, about, result
		 * and highscore.
		 */
		fileMenu.getItems().add(exitItem);

		Menu fileHelp = new Menu("Help");
		MenuItem rulesItem = new MenuItem("Rules of the Game");
		
		Menu fileStatistics = new Menu("Result");
		MenuItem resultItem = new MenuItem("View Highscore");
		

		fileHelp.getItems().addAll(rulesItem);

		fileStatistics.getItems().addAll(resultItem);

		menuBar.getMenus().addAll(fileMenu, fileHelp, fileStatistics);
		layout.setTop(menuBar);
		/**
		 * Creating labels and textfields for header, username, password.
		 */
		Label header = new Label("Skee-ball");
		center.setRightAnchor(header, 110.0);
		center.setLeftAnchor(header, 180.0);

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
		
		TextField txtrules = new TextField("");
		center.setTopAnchor(txtrules, 120.0);
		center.setLeftAnchor(txtrules, 10.0);
		
		/**
		 * Creating buttons for ok and account
		 */
		Button okbutton = new Button("Log in");
		center.setTopAnchor(okbutton, 350.0);
		center.setLeftAnchor(okbutton, 330.0);

		Button accbutton = new Button("Create an account");
		center.setTopAnchor(accbutton, 400.0);
		center.setLeftAnchor(accbutton, 330.0);
		/**
		 * Setting the fault printout text under password label
		 */
		center.setTopAnchor(wrongLogin, 130.0);
		center.setLeftAnchor(wrongLogin, 10.0);
		
		/**
		 * Creating an event that is checking if loginuser is available and
		 * changing the ok button after the user has logged in into "logout".
		 * When the user wants to logout the page is closing. And if the inlog
		 * is failed the fault print out text is coming up
		 * "Wrong username/password".
		 */
		okbutton.setOnAction(event -> {
			user = loginUser(txtUser.getText(), txtPw.getText());
			if (user != null) {
				GameStage game = new GameStage(primaryStage);
			
			} else {
				wrongLogin.setVisible(true);
			}

		});
		
		accbutton.setOnAction(event -> {
			AccountView createAccount = new AccountView(primaryStage);
			
		});

		/**
		 * Setting Borderpane into center and all the variable names in the
		 * layout.
		 */

		layout.setCenter(center);
		center.getChildren().addAll(label, label1, okbutton, accbutton, txtUser, header, txtPw, wrongLogin);
		/**
		 * Sets the scenes size
		 */
		Scene scene = new Scene(layout, 500, 500);
		/**
		 * Setting the scene and show
		 */
		primaryStage.setScene(scene);
		primaryStage.show();

		/**
		 * Closing the program
		 */
		exitItem.setOnAction(e -> Platform.exit());

		rulesItem.setOnAction(e -> {

		});
		
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */

	public UserAccount loginUser(String username, String password) {
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

	public static UserAccount getCurrentUser() {
		return user;
	}

	/**
	 * 
	 * @param args
	 *            Main method
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
