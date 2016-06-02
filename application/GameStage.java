package application;

import java.util.Iterator;

import connectivity.GetNSetDb;
import entity.GameLevel;
import entity.UserAccount;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Jannike
 * 
 *         The class that manages the main part of the game.
 * 
 *         To the left is the playingField, to the right a pane with
 *         communication and information to the player and below is a pane with
 *         visual feedback of how many balls taht are left to play
 *
 */
public class GameStage {
	public static final int FRAME_RATE = 100; // Used to calculate the speed of
												// the ball and in Ball to
												// animate
	private Ball ball;
	private double clickedXCoord;
	private double clickedYCoord;
	private static double startXCoord;
	private static double startYCoord;
	private double distanceX;
	private double distanceY;
	private static final int playingFieldWidth = 450;
	private static final int playingFieldHeight = 500;
	private static final int ballRadius = 20;
	private int ballsLeftToPlay = 0;
	private BottomPane bottomPane = new BottomPane();
	private PlayingField playingField = new PlayingField();
	private int sumOfBalls = 0;
	private InfoPane infoPane;
	private int resultsExpected = 0;
	private int ballsToBePlayed = 0;

	/**
	 * The different parts of the game are put together
	 */
	public GameStage(Stage primaryStage) {
		Scene loginScene = primaryStage.getScene();
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 840, 650);
		infoPane = new InfoPane(this);
		pane.setRight(infoPane);
		pane.setBottom(bottomPane);
		pane.setLeft(playingField);
		playingField.createPlayingField(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: darkgrey");
		startXCoord = playingFieldWidth / 2; // startcoordinates of the ball
		startYCoord = playingFieldHeight - ballRadius;

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Log out");
		MenuItem exitItem = new MenuItem("Log out");
		fileMenu.getItems().add(exitItem);
		Menu fileHelp = new Menu("How to play");
		MenuItem rulesItem = new MenuItem("How to play");
		fileHelp.getItems().addAll(rulesItem);
		menuBar.getMenus().addAll(fileMenu, fileHelp);
		pane.setTop(menuBar);

		/**
		 * Display a how to play guide
		 */
		rulesItem.setOnAction(event -> {
			infoPane.displayHowToPlay();
		});

		/**
		 * When logging out the program redirects to the login page
		 * 
		 * On the login page the user's username and password are visible, that
		 * could be fixed in a future version of the program
		 */
		exitItem.setOnAction(event -> {
			primaryStage.setScene(loginScene);
		});

		/**
		 * MouseEvent.MOUSE_CLICKED
		 * 
		 * Used to play a ball in the game
		 * 
		 * The coordinates where the mouse is clicked are registered and the
		 * distance to the starting point is calculated. The coordinates sets
		 * the direction of the ball and the distance is the basis for the
		 * calculation of the ball's speed.
		 * 
		 * A ball is created, added to the playingField and is set in motion.
		 */
		playingField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (ballsLeftToPlay == 0) {
				return; // The game is over, no more balls to play
			}
			resultsExpected++;
			ballsLeftToPlay--;
			bottomPane.setupBottomPane(ballsLeftToPlay);
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;
			ball = new Ball(startXCoord, startYCoord, ballRadius, Color.CHARTREUSE, playingField,
					this, distanceX / FRAME_RATE * 1.5, distanceY / FRAME_RATE * 1.5);
			playingField.getChildren().add(ball);
			ball.animateBallMovement();
		});

		primaryStage.setTitle("Skee Ball");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * getBallsLeftToPlay()
	 * 
	 * @return an int, the number of balls left to play
	 */
	public int getBallsLeftToPlay() {
		return ballsLeftToPlay;
	}

	/**
	 * setBallsLeftToPlay(int numOfBallsToBePlayed)
	 * 
	 * Called at the start of a game
	 * 
	 * Sets variables ballsLeftToPlay and ballsToBePlayed to the number of balls
	 * that vill be played.
	 * 
	 * ballsLeftToPlay is later used to calculate how many balls are left to
	 * play, ballsToBePlayed is used to set the level of the game.
	 * 
	 * The bottom pane is set up and the balls from last game are removed from
	 * the playingfield and the counter of points is reseted
	 * 
	 * @param numOfBallsToBePlayed
	 *            - an int, the number of balls that will be played in the game
	 */
	public void setBallsLeftToPlay(int numOfBallsToBePlayed) {
		ballsLeftToPlay = numOfBallsToBePlayed;
		ballsToBePlayed = numOfBallsToBePlayed;
		bottomPane.setupBottomPane(ballsLeftToPlay);
		// Removes the balls from last game
		Iterator<Node> iterator = playingField.getChildren().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof Ball) {
				iterator.remove();
			}
		}
		sumOfBalls = 0;
	}

	/**
	 * setBallPoints(int ballPoints)
	 * 
	 * Decrements the number of results left to expect, when 0 no ball is in
	 * play
	 * 
	 * Accumulates the number of poits collected by the player
	 * 
	 * Sends the latest number of points to infopane
	 * 
	 * Check if all balls are played and their point accounted for, if so the
	 * variable used to represent the gamelevel (the number of balls to be used
	 * in the game is the level) is set and infoPane gets called to write the
	 * result. The result is also saved to the database.
	 * 
	 * @param ballPoints
	 *            - an int, the number of points for one ball
	 */
	public void setBallPoints(int ballPoints) {
		resultsExpected--;
		sumOfBalls += ballPoints;
		infoPane.setBallPoints(ballPoints);
		if (resultsExpected == 0 && ballsLeftToPlay == 0) {
			int level = ballsToBePlayed;
			saveResultToDB(sumOfBalls, level);
			infoPane.WriteResults();
		}
	}

	/**
	 * saveResultToDB(int sumPoints, int level)
	 * 
	 * Get the identity of the current player and sets the result and gamelevel
	 * for the game in the database
	 * 
	 * @param sumPoints
	 *            - an int, the sum of points in a game
	 * @param level
	 *            - an int, the number of balls to be used in a game
	 */
	public void saveResultToDB(int sumPoints, int level) {
		UserAccount user = InlogView.getCurrentUser();
		GetNSetDb gsdb = new GetNSetDb();
		gsdb.setResult(user, sumPoints, level);
	}

}
