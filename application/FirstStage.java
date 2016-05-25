package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class FirstStage extends Application {

	double xcoor;
	double ycoor;

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		Label resultLabel = new Label();
		Path path = new Path();
		int startXCoord;
		int startYCoord;
		int playingfieldWidth; // ska man ha int eller double? TODO
		int playingfieldHeight;
		double clickedXCoord;
		double clickedYCoord;
		double distance;
		double distanceX;
		double distanceY;

		// private void getClickedCoords(Event event) {
		// } TODO ska man ha funktion för detta eller göra det i
		// eventhandlern????

		playingfieldWidth = (int) pane.getWidth();
		playingfieldHeight = (int) pane.getHeight();

		pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			// vid klick: bollen rullar i given riktning med en fart som
			// bestämts av avståndet mellan startpunkten och klickpunkten
			// vid klick: boll uppstår på startpunkten och rör sig mot klicket
			xcoor = event.getX();
			int xcoorInt = (int) event.getX();
			ycoor = event.getY();
			int ycoorInt = (int) event.getY();
			String utskrift = ("x-koor: " + xcoor + " y-koor: " + ycoor + "\n"
					+ "x-koorInt: " + xcoorInt + " y-koorInt: " + ycoorInt);
			resultLabel.setText(utskrift);
			pane.getChildren().add(new Ball(30, 30, 20, Color.CHARTREUSE,
					playingfieldWidth, playingfieldHeight));

			// getClickedCoords(event);
		});

		resultLabel.setStyle("-fx-border-color: yellow");
		pane.setTop(resultLabel);
		Scene scene = new Scene(pane, 250, 150);
		// förberedelse för att kunna ha olika storlekar på spelplanen
		startXCoord = playingfieldWidth / 2;
		startYCoord = playingfieldHeight;
		clickedXCoord = xcoor;
		clickedYCoord = ycoor;

		MoveTo moveTo = new MoveTo();
		moveTo.setX(startXCoord);
		moveTo.setY(startYCoord);
		LineTo line1 = new LineTo(startXCoord + 2, startYCoord);
		LineTo line2 = new LineTo(startXCoord, startYCoord - 4);
		LineTo line3 = new LineTo(startXCoord - 2, startYCoord);
		path.getElements().addAll(moveTo, line1, line2, line3);
		pane.getChildren().add(path);
		distance = Math.sqrt(Math.pow((clickedXCoord - startXCoord), 2)
				+ (Math.pow((clickedYCoord - startYCoord), 2)));
		distanceX = clickedXCoord - startXCoord;
		distanceY = clickedYCoord - startYCoord;

		primaryStage.setTitle("Working on fourth stage");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
