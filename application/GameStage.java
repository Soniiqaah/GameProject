package application;

import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * TODO
 * 
 * @author Jannike
 *
 */
public class GameStage extends Application {
	public static final int FRAME_RATE = 100; // Används för att räkna ut
												// bollens hastighet och i Ball
												// för att animera.

	private Ball ball;
	private double clickedXCoord; // globala för att kommas åt i eventhandlern
									// TODO
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
	
	/**
	 * TODO
	 */
	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 850, 650);
		Label topLabel = new Label("Plats för menyer");
		infoPane = new InfoPane(this);
		pane.setTop(topLabel);
		pane.setRight(infoPane);
		pane.setBottom(bottomPane);
		pane.setLeft(playingField);
		playingField.createPlayingField(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: darkgrey");
		startXCoord = playingFieldWidth / 2; // startcoordinates of the ball
		startYCoord = playingFieldHeight - ballRadius;

		/**
		 * MouseEvent.MOUSE_CLICKED
		 * 
		 * The coordinates where the mouse is clicked are registered and the
		 * distance to the starting point is calculated. The coordinates sets
		 * the direction of the ball and the distance gives the basis for the
		 * calculation of the ball's speed.
		 * 
		 * A ball is created, added to the playingField and is set in motion.
		 */

		playingField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			// vid klick: bollen rullar i given riktning med en fart som
			// bestämts av avståndet mellan startpunkten och klickpunkten
			System.out.println("ballsLeftToPlay: " + ballsLeftToPlay);
			if (ballsLeftToPlay == 0) {
				
				return;
			}
			ballsLeftToPlay--;
			bottomPane.setupBottomPane(ballsLeftToPlay);
			// TODO: Uppdatera bottomPane
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;
			ball = new Ball(startXCoord, startYCoord, ballRadius, Color.CHARTREUSE, playingField,
					this, distanceX / FRAME_RATE * 1.5, distanceY / FRAME_RATE * 1.5); // gångerfaktor
			// för
			// att
			// höja
			// hastighet
			// TODO
			// öka farten för varje försök?????????TODO
			playingField.getChildren().add(ball);
			ball.animateBallMovement();

		});

		primaryStage.setTitle("Skeeball");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public int getBallsLeftToPlay() {
		return ballsLeftToPlay;
	}

	/**
	 * TODO
	 * @param numOfBallsToBePlayed
	 */
	public void setBallsLeftToPlay(int numOfBallsToBePlayed) {
		ballsLeftToPlay = numOfBallsToBePlayed;
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
	 * TODO
	 * @param ballPoints
	 */
	public void setBallPoints(int ballPoints) {
		sumOfBalls += ballPoints;
		infoPane.setBallPoints(ballPoints);
		System.out.println("bollpoäng: " + ballPoints + " summa: " + sumOfBalls);
		// TODO Efter sista bollens poäng sumerats -> spara db
	}
	
	/**
	 * TODO 
	 * SKA BARA VAR PÅ ETT STÄLLE!!!!!!!!!!!!!!!!!!!! InlogView?
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
