package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	double xcoor;
	double ycoor;
	Ball ball;
	double clickedXCoord; // globala f�r att kommas �t i eventhandlern TODO
	double clickedYCoord;
	double startXCoord;
	double startYCoord;
	double distanceX;
	double distanceY;
	private static final int playingFieldWidth = 450;
	private static final int playingFieldHeight = 500;
	private static final int ballRadius = 20;

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		PlayingField playingField = new PlayingField();

		Scene scene = new Scene(pane, 600, 680);
		// dummy-kod f�r att f� fason p� scenen s� l�nge
		Button topButton = new Button("Toppknapp");
		Button rightButton = new Button("H�gerknapp");
		Button bottomButton = new Button("Bottenknapp");
		pane.setTop(topButton);
		pane.setRight(rightButton);
		pane.setBottom(bottomButton);
		// slut dummy-kod
		pane.setLeft(playingField);
		playingField.setSize(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: darkgrey");
		startXCoord = playingFieldWidth / 2; // startcoordinates of the ball
		startYCoord = playingFieldHeight - ballRadius;

		playingField.getChildren().add(playingField.createStartPoint(startXCoord, startYCoord));
		playingField.placingZones();

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
			// best�mts av avst�ndet mellan startpunkten och klickpunkten
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;
			ball = new Ball(startXCoord, startYCoord, ballRadius, Color.CHARTREUSE, playingField,
					distanceX / 100 * 1.5, distanceY / 100 * 1.5); // g�ngerfaktor
																	// f�r
																	// att
																	// h�ja
																	// hastighet
																	// TODO
			// �ka farten f�r varje f�rs�k?????????TODO
			playingField.getChildren().add(ball);
			ball.animateBallMovement();
		});

		primaryStage.setTitle(" ");
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}

}
