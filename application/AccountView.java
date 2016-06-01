package application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.UserAccount;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Soniiqaah Creating a class AccountView that extends from Application.
 *
 */

public class AccountView {
	/**
	 * Declare instance variables
	 */
	private String username;
	private String password;
	private String confirmpass;
	private String alertmsg = "";

	public AccountView (Stage primaryStage) throws Exception {
		/**
		 * Creating a fault printout text and setting it to false
		 */
		Text notValidUname = new Text("The username is already in use, please choose another one");
		notValidUname.setVisible(false);
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
 * Getting the items
 */
		fileMenu.getItems().addAll(exitItem);

		Menu fileHelp = new Menu("Help");
		MenuItem rulesItem = new MenuItem("Game rules");

		fileHelp.getItems().addAll(rulesItem);
/**
 * Setting the menu at the top
 */
		menuBar.getMenus().addAll(fileMenu, fileHelp);
		layout.setTop(menuBar);
/**
 * Creating labels and setting the position
 */
		Label account = new Label("Create an account");
		center.setRightAnchor(account, 60.0);
		center.setLeftAnchor(account, 180.0);

		Label label1 = new Label("Username");
		center.setTopAnchor(label1, 10.0);
		center.setLeftAnchor(label1, 10.0);

		Label label = new Label("Password");
		center.setTopAnchor(label, 70.0);
		center.setLeftAnchor(label, 10.0);
/**
 * Creating textfields and setting the position
 */
		TextField txtUser = new TextField("");
		center.setTopAnchor(txtUser, 30.0);
		center.setLeftAnchor(txtUser, 10.0);

		TextField txtPw = new TextField("");
		center.setTopAnchor(txtPw, 90.0);
		center.setLeftAnchor(txtPw, 10.0);

		Button okbutton = new Button("OK");
		center.setTopAnchor(okbutton, 350.0);
		center.setLeftAnchor(okbutton, 330.0);

		center.setTopAnchor(notValidUname, 130.0);
		center.setLeftAnchor(notValidUname, 10.0);
/**
 * 
 */
		okbutton.setOnAction(event -> {
			boolean accountIsCreated = addUserAccount(txtUser.getText(), txtPw.getText());

			if (accountIsCreated == false) {
				notValidUname.setVisible(true);
			} else {
				notValidUname.setVisible(false);
			}

		});

		layout.setCenter(center);
		center.getChildren().addAll(label, label1, okbutton, txtUser, account, txtPw, notValidUname);

		Scene scene = new Scene(layout, 500, 500);

		primaryStage.setScene(scene);
		primaryStage.show();

		// Closing the program.
		exitItem.setOnAction(e -> Platform.exit());

		rulesItem.setOnAction(e -> {

			Alert aboutMessage = new Alert(AlertType.INFORMATION, "Create an account");
			aboutMessage.showAndWait();

		});

	}
/**
 * 
 * @param username
 * @param password
 * @return
 */
	public boolean addUserAccount(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		if (!checkDouble(username, em)) {
			UserAccount user = new UserAccount();
			user.setUser(username);
			user.setPassword(password);

			em.getTransaction().begin();
			em.persist(user);

			em.getTransaction().commit();
			em.close();
			emf.close();
			return true;
		}
		em.close();
		emf.close();
		return false;

	}
	/**
	 * 
	 * @param username
	 * @param em
	 * @return
	 */

	public boolean checkDouble(String username, EntityManager em) {
		Query query = em.createNamedQuery("CheckingIfUsernameIsAvailable");
		query.setParameter("uname", username);
		List<UserAccount> accountList = query.getResultList();
		for (UserAccount useraccount : accountList) {
			if (username.equals(useraccount.getUser()))
				return true;
		}
		return false;
	}
}
