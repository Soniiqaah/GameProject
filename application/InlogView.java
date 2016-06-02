package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

import connectivity.GetNSetDb;
import entity.Result;
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
	private List<UserAccount> allUsers;
	private static UserAccount user;
	private String howToPlayTxt = "Start the game by choosing the number of balls \n"
			+ "to be played and press OK. The chosen amount \n"
			+ "of balls will then be showed below the \n"
			+ "playingfield as a visual feedback. \n\n"
			+ "The colours of the zones represents the number \n"
			+ "of points you get when a ball stops in it and \n"
			+ "the mapping between colour and points is showed \n"
			+ "to the right of the playingfield. When the ball \n"
			+ "stops in a zone, the colour of the ball changes \n" + "into blue. \n\n "
			+ "The start position of the ball is at the black \n"
			+ "pointer in the bottom of the playingfield. To \n"
			+ "play a ball you click in the playingfield. The \n"
			+ "direction of the ball is decided by where you \n"
			+ "click and the distance between the coordinates \n"
			+ "on which you clicked and the start pointer sets \n" + "the speed of the ball. \n\n"
			+ "When a ball is played the number of balls in the \n"
			+ "visual feedback is decremented.\n\n"
			+ "When all balls are played the result is displayed \n" + "to the right.";
	private TextArea howToPlay = new TextArea(howToPlayTxt);
	private Button hideHowToPlayButton = new Button("Close");
	private GridPane theCurrentHighscores = new GridPane();
	private Button hideHighscoresButton = new Button("Close");

	@Override
	public void start(Stage primaryStage) throws Exception {

		/**
		 * Creating a fault printout text and setting it to visible false
		 */
		Text wrongLogin = new Text("Wrong username/password");
		wrongLogin.setVisible(false);
		/**
		 * Setting the fault printout to red text
		 */
		wrongLogin.setFill(Color.RED);

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
		 * Getting the item exit and creating menu tabs for help and highscore.
		 */
		fileMenu.getItems().add(exitItem);

		Menu fileHelp = new Menu("Help");
		MenuItem rulesItem = new MenuItem("How to play");
		fileHelp.getItems().addAll(rulesItem);

		Menu fileHighscore = new Menu("Highscore");
		MenuItem highscoreItem = new MenuItem("Top 4 of all levels");
		fileHighscore.getItems().addAll(highscoreItem);

		/**
		 * Getting the menus
		 */

		menuBar.getMenus().addAll(fileMenu, fileHelp, fileHighscore);
		layout.setTop(menuBar);

		/**
		 * Creating labels and textfields for header, username, password.
		 */
		Label header = new Label("Skee Ball");
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
		wrongLogin.setStyle("-fx-color: red");

		/**
		 * The textarea with instructions on how to play the game
		 */
		center.setTopAnchor(howToPlay, 30.0);
		center.setLeftAnchor(howToPlay, 180.0);
		howToPlay.setVisible(false);
		howToPlay.setPrefSize(300, 250);

		center.setTopAnchor(hideHowToPlayButton, 290.0);
		center.setLeftAnchor(hideHowToPlayButton, 420.0);
		hideHowToPlayButton.setVisible(false);

		center.setTopAnchor(hideHighscoresButton, 290.0);
		center.setLeftAnchor(hideHighscoresButton, 420.0);
		hideHighscoresButton.setVisible(false);

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
				new GameStage(primaryStage);

			} else {
				wrongLogin.setVisible(true);
			}

		});

		accbutton.setOnAction(event -> {
			new AccountView(primaryStage);

		});

		/**
		 * Setting Borderpane into center and all the variable names in the
		 * layout.
		 */

		layout.setCenter(center);
		center.getChildren().addAll(label, label1, okbutton, accbutton, txtUser, header, txtPw,
				wrongLogin, howToPlay, hideHowToPlayButton, theCurrentHighscores,
				hideHighscoresButton);
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

		rulesItem.setOnAction(event -> {
			displayHowToPlay();
		});

		hideHowToPlayButton.setOnAction(event -> {
			howToPlay.setVisible(false);
			hideHowToPlayButton.setVisible(false);
		});

		highscoreItem.setOnAction(event -> {
			getTheTop4s();
			center.setTopAnchor(theCurrentHighscores, 30.0);
			center.setLeftAnchor(theCurrentHighscores, 180.0);
		});

		hideHighscoresButton.setOnAction(event -> {
			theCurrentHighscores.setVisible(false);
			hideHighscoresButton.setVisible(false);
		});
	}

	private void displayHowToPlay() {
		howToPlay.setVisible(true);
		hideHowToPlayButton.setVisible(true);
	}

	/**
	 * Creates the top 4 lists of the three levels - 3, 5, and 7 balls and puts
	 * them on a gridpane
	 */
	private void createTheTop4s() {
		GetNSetDb gsdb = new GetNSetDb();
		theCurrentHighscores.getChildren().clear();
		int maxNumOfResults = 4;
		List<Result> TopOfLevel3 = getTheTopOfLevel(3, maxNumOfResults, gsdb);
		List<Result> TopOfLevel5 = getTheTopOfLevel(5, maxNumOfResults, gsdb);
		List<Result> TopOfLevel7 = getTheTopOfLevel(7, maxNumOfResults, gsdb);

		Label highscoreHeadline3 = new Label("Top " + maxNumOfResults + " , 3 balls");
		Label highscoreHeadline5 = new Label("Top " + maxNumOfResults + " , 5 balls");
		Label highscoreHeadline7 = new Label("Top " + maxNumOfResults + " , 7 balls");
		highscoreHeadline3.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		highscoreHeadline5.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		highscoreHeadline7.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		// level 3 balls
		int i = 0;
		theCurrentHighscores.add(highscoreHeadline3, 0, 0, 3, 1);
		for (Result r : TopOfLevel3) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText((i + 1) + ".");
			usernameLabel.setText(r.getAccount().getUser());
			pointsLabel.setText(String.valueOf(r.getPoints()));
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(20));
			theCurrentHighscores.add(placeLabel, 0, i + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(150));
			theCurrentHighscores.add(usernameLabel, 1, i + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(50));
			theCurrentHighscores.add(pointsLabel, 2, i + 1);
			i++;
		}
		// level 5 balls
		i = 0;
		int j = maxNumOfResults + 3;
		theCurrentHighscores.add(highscoreHeadline5, 0, j, 3, 1);
		for (Result r : TopOfLevel5) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText((i + 1) + ".");
			usernameLabel.setText(r.getAccount().getUser());
			pointsLabel.setText(String.valueOf(r.getPoints()));
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(20));
			theCurrentHighscores.add(placeLabel, 0, j + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(150));
			theCurrentHighscores.add(usernameLabel, 1, j + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(50));
			theCurrentHighscores.add(pointsLabel, 2, j + 1);
			i++;
			j++;
		}
		// level 7 balls
		i = 0;
		int k = 2 * maxNumOfResults + 6;
		theCurrentHighscores.add(highscoreHeadline7, 0, k, 3, 1);
		for (Result r : TopOfLevel7) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText((i + 1) + ".");
			usernameLabel.setText(r.getAccount().getUser());
			pointsLabel.setText(String.valueOf(r.getPoints()));
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(20));
			theCurrentHighscores.add(placeLabel, 0, k + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(150));
			theCurrentHighscores.add(usernameLabel, 1, k + 1);
			theCurrentHighscores.getColumnConstraints().add(new ColumnConstraints(50));
			theCurrentHighscores.add(pointsLabel, 2, k + 1);
			i++;
			k++;
		}
	}

	private List<Result> getTheTopOfLevel(int level, int numOfTopResults, GetNSetDb gsdb) {
		List<Result> topResults = gsdb.getSortedResults(level, numOfTopResults);
		return topResults;
	}

	private void getTheTop4s() {
		createTheTop4s();
		theCurrentHighscores.setVisible(true);
		hideHighscoresButton.setVisible(true);
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
