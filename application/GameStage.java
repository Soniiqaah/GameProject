package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Jannike
 *
 */
public class GameStage extends Application {

	private Ball ball;
	private double clickedXCoord; // globala för att kommas åt i eventhandlern
									// TODO
	private double clickedYCoord;
	private double startXCoord;
	private double startYCoord;
	private double distanceX;
	private double distanceY;
	private static final int playingFieldWidth = 450;
	private static final int playingFieldHeight = 500;
	private static final int ballRadius = 20;
	private int ballsLeftToPlay = 0;

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		PlayingField playingField = new PlayingField();

		Scene scene = new Scene(pane, 850, 650);
		Label topLabel = new Label("Plats för menyer");
		InfoPane infoPane = new InfoPane();
		BottomPane bottomPane = new BottomPane();
		pane.setTop(topLabel);
		pane.setRight(infoPane);
		pane.setBottom(bottomPane.setupBottomPane()); 
		pane.setLeft(playingField);
		playingField.setSize(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: darkgrey");
		startXCoord = playingFieldWidth / 2; // startcoordinates of the ball
		startYCoord = playingFieldHeight - ballRadius;

		playingField.getChildren().add(playingField.createStartPoint(startXCoord, startYCoord));
		playingField.placingZones();
		ballsLeftToPlay = InfoPane.getNumOfBallsToBePlayed();
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
			if (ballsLeftToPlay == 0) {
				return;
			}
			ballsLeftToPlay--;
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;
			ball = new Ball(startXCoord, startYCoord, ballRadius, Color.CHARTREUSE, playingField,
					distanceX / 100 * 1.5, distanceY / 100 * 1.5); // gångerfaktor
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

	public int getBallsLeftToPlay() {
		return ballsLeftToPlay;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
