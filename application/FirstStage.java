package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/**
 * 
 * @author Jannike
 *
 */
public class FirstStage extends Application {

	double xcoor;
	double ycoor;
	Ball ball;
	double clickedXCoord; // globala för att kommas åt i eventhandlern TODO
	double clickedYCoord;
	double startXCoord;
	double startYCoord;
	double distanceX;
	double distanceY;
	private static final int playingFieldWidth = 450;
	private static final int playingFieldHeight = 500;
	private static final int ballRadius = 20;
	private static final int startMarkX = playingFieldWidth / 2;
	private static final int startMarkY = playingFieldHeight;

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		Path path = new Path();
		PlayingField playingField = new PlayingField();

		Scene scene = new Scene(pane, 600, 680);
		// dummy-kod för att få fason på scenen så länge
		Button topButton = new Button("Toppknapp");
		Button rightButton = new Button("Högerknapp");
		Button bottomButton = new Button("Bottenknapp");
		pane.setTop(topButton);
		pane.setRight(rightButton);
		pane.setBottom(bottomButton);
		// slut dummy-kod
		pane.setLeft(playingField);
		playingField.setSize(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: darkgrey");
		startXCoord = playingFieldWidth / 2;
		startYCoord = playingFieldHeight - ballRadius;

		// läggas i playingfield? TODO
		// createStartPoint(startXCoord, startYCoord); // metod i stället TODO
		// markerar startpunkten på spelplanen
		MoveTo moveTo = new MoveTo();
		moveTo.setX(startMarkX);
		moveTo.setY(startMarkY);
		LineTo line1 = new LineTo(startMarkX + 5, startMarkY);
		LineTo line2 = new LineTo(startMarkX, startMarkY - 8);
		LineTo line3 = new LineTo(startMarkX - 5, startMarkY);
		LineTo line4 = new LineTo(startMarkX, startMarkY);
		path.getElements().addAll(moveTo, line1, line2, line3, line4);
		playingField.getChildren().add(path);
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
			// bestämts av avståndet mellan startpunkten och klickpunkten
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();

			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;

			ball = new Ball(startXCoord, startYCoord, 20, Color.CHARTREUSE, playingField,
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

		// private void createStartPoint(int startXCoord, int startYCoord) {
		// MoveTo moveTo = new MoveTo();
		// moveTo.setX(startXCoord);
		// moveTo.setY(startYCoord);
		// LineTo line1 = new LineTo(startXCoord + 5, startYCoord);
		// LineTo line2 = new LineTo(startXCoord, startYCoord - 8);
		// LineTo line3 = new LineTo(startXCoord - 5, startYCoord);
		// LineTo line4 = new LineTo(startXCoord, startYCoord);
		// path.getElements().addAll(moveTo, line1, line2, line3, line4);
		// pane.getChildren().add(path);
		// }

		primaryStage.setTitle(" ");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
