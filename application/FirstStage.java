package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
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
	double clickedXCoord; //måste kommas åt i eventhandlern
	double clickedYCoord;
	int startXCoord;
	int startYCoord;
	double distanceX;
	double distanceY;

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		Path path = new Path();
		int playingfieldWidth; // ska man ha int eller double? TODO
		int playingfieldHeight;
		double distance;

		// private void getClickedCoords(Event event) {
		// } 
		// TODO funktion för detta 

		Scene scene = new Scene(pane, 450, 550);

		playingfieldWidth = (int) pane.getWidth();
		playingfieldHeight = (int) pane.getHeight();
//		createStartPoint();

		pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			// vid klick: bollen rullar i given riktning med en fart som
			// bestämts av avståndet mellan startpunkten och klickpunkten
			// vid klick: boll uppstår på startpunkten och rör sig mot klicket
			clickedXCoord = event.getX();
			clickedYCoord = event.getY();
			startXCoord = playingfieldWidth / 2;
			startYCoord = playingfieldHeight - 25; // TODO kunna ange storlek på boll

			distanceX = clickedXCoord - startXCoord;
			distanceY = clickedYCoord - startYCoord;

			ball = new Ball(startXCoord, startYCoord, 20, Color.CHARTREUSE,
					playingfieldWidth, playingfieldHeight, distanceX/20, distanceY/20);
			pane.getChildren().add(ball);
			ball.animateBallMovement();

			// getClickedCoords(event);
		});

		// förberedelse för att kunna ha olika storlekar på spelplanen

//		private void createStartPoint(int startXCoord, int startYCoord) {
//			MoveTo moveTo = new MoveTo();
//			moveTo.setX(startXCoord);
//			moveTo.setY(startYCoord);
//			LineTo line1 = new LineTo(startXCoord + 5, startYCoord);
//			LineTo line2 = new LineTo(startXCoord, startYCoord - 8);
//			LineTo line3 = new LineTo(startXCoord - 5, startYCoord);
//			LineTo line4 = new LineTo(startXCoord, startYCoord);
//			path.getElements().addAll(moveTo, line1, line2, line3, line4);
//			pane.getChildren().add(path);
//		}
		
		distance = Math.sqrt(Math.pow((clickedXCoord - startXCoord), 2)
				+ (Math.pow((clickedYCoord - startYCoord), 2)));
		primaryStage.setTitle("stage 11 working");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
