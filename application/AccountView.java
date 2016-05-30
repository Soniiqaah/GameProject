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

public class AccountView extends Application {
	private String username;
	private String password;
	private String confirmpass;
	private String alertmsg = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Text notValidUname = new Text("The username is already in use, please choose another one");
		notValidUname.setVisible(false);

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

		fileHelp.getItems().addAll(aboutItem);

		menuBar.getMenus().addAll(fileMenu, fileHelp);
		layout.setTop(menuBar);

		Label account = new Label("Create an account");
		center.setRightAnchor(account, 60.0);
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

		Button okbutton = new Button("OK");
		center.setTopAnchor(okbutton, 350.0);
		center.setLeftAnchor(okbutton, 330.0);
		
		okbutton.setOnAction(event -> {
			boolean accountIsCreated = addUserAccount(txtUser.getText(), txtPw.getText());
		
			if(accountIsCreated == false){
				notValidUname.setVisible(true);
			}else{
				notValidUname.setVisible(false);
			}
			
			}
			);

		layout.setCenter(center);
		center.getChildren().addAll(label, label1, okbutton, txtUser, account, txtPw, notValidUname);

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

	public boolean addUserAccount(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("skeeballdb");
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
	public boolean checkDouble(String username, EntityManager em){
		Query query = em.createNamedQuery("CheckingIfUsernameIsAvailable");
		query.setParameter("uname", username);
		List<UserAccount> AccountList = query.getResultList();
		for(UserAccount useraccount : AccountList){
			if(username.equals(useraccount.getUser()))
				return true;
		}
		return false;
	} 

	public void checkUser(String user, String password, String confirmpass) {
		if (username.length() < 5) {
			alertmsg = ("The username is not long enough, please try again!");
		} else if (password.length() < 5) {
			alertmsg = ("You have to atleast type in six characters for the password!");
		} else if (password != confirmpass) {
			alertmsg = ("The both password must match!");

		} else {
			alertmsg = ("You are logged in!");

		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
