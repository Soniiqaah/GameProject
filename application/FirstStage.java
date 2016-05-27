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
import javafx.util.Duration;

public class FirstStage extends Application {

	double xcoor;
	double ycoor;
	Ball ball;
	double clickedXCoord; // globa f�r att kommas �t i eventhandlern TODO
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
		// int playingfieldWidth; // ska man ha int eller double? TODO
		// int playingfieldHeight;
		PlayingField playingField = new PlayingField();
		double distance;

		// private void getClickedCoords(Event event) {
		// }
		// TODO funktion f�r detta

		Scene scene = new Scene(pane, 600, 680);
		Button topButton = new Button("Toppknapp");
		Button leftButton = new Button("V�nsterknapp");
		Button rightButton = new Button("H�gerknapp");
		Button bottomButton = new Button("Bottenknapp");
		pane.setTop(topButton);
		pane.setRight(rightButton);
		pane.setBottom(bottomButton);
		pane.setLeft(playingField);
		playingField.setSize(playingFieldWidth, playingFieldHeight);
		playingField.setStyle("-fx-border-color: yellow");
//		playingField.setStyle("-fx-overflow: hidden");
		startXCoord = playingFieldWidth / 2;
		startYCoord = playingFieldHeight - ballRadius; // TODO kunna ange
														// storlek p�
														// boll/g�ra om till
														// en konstant
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
		// createStartPoint(startXCoord, startYCoord);

		playingField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			// vid klick: bollen rullar i given riktning med en fart som
			// best�mts av avst�ndet mellan startpunkten och klickpunkten
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			// startXCoord = playingField.getWidth() / 2;
			// startYCoord = playingField.getHeight() - 25; // TODO kunna ange
			// // storlek p�
			// // boll/g�ra om till
			// // en konstant

			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;

			ball = new Ball(startXCoord, startYCoord, 20, Color.CHARTREUSE,
					playingField, distanceX / 100 * 1.5, distanceY / 100 * 1.5); // g�ngerfaktor
																				// f�r
																				// att
																				// h�ja
																				// hastighet
																				// TODO
			playingField.getChildren().add(ball);
			ball.animateBallMovement();
		});

		// f�rberedelse f�r att kunna ha olika storlekar p� spelplanen

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
		//
		distance = Math.sqrt(Math.pow((clickedXCoord - startXCoord), 2)
				+ (Math.pow((clickedYCoord - startYCoord), 2)));
		primaryStage.setTitle("stage 13 working");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
