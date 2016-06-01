package application;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import connectivity.GetNSetDb;
import entity.GameLevel;
import entity.Result;
import entity.UserAccount;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InfoPane extends GridPane {

	// rita ut vilka po�ng de olika f�rgerna p� zoner betyder, L�GST PRIORITET
	// lablar, textrutor etc f�r info och dialog
	// dialog f�r val av antal bollar

	private ComboBox<String> optionsCBox;
	private int numOfBallsToBePlayed;
	private GameStage gameStage;
	private int latestPoints;
	private int sumOfBallPoints = 0;
	private Label resultOutPut;

	public InfoPane(GameStage gameStage) {
		this.gameStage = gameStage;
		optionsCBox = new ComboBox<String>();
		Text title = new Text("Skeeball");
		Text introQuestion = new Text("Hur många bollar vill du spela?");
		Button numOfBallsDecided = new Button("OK");
		GridPane pointsColorGrid = new GridPane();
		int totalSum = 0;
		// Label pointsSoFar = new Label("Poäng i pågående spel: "); TODO
		// Label outputPointsSoFar = new Label("" + totalSum);
		// Label ballsLeftToPlayHeadline = new Label("Antal bollar kvar att
		// spela: ");
		// Label outputBallsLeftToPlay = new Label();
		int numOfBallsLeftToPlay = 0;
		Label resultOutPut = new Label();
		// int numOfBallsPlayed = 0; //TODO preperation for future enhancment
		// TextArea highscoreHeadline = new TextArea(
		// "Highscore för " + numOfBallsPlayed + " spelade bollar:");
		// TextArea highscoreResults = new TextArea();
		setHgap(10);
		setVgap(10);
		// TODO fonts, padding etc
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		add(title, 0, 0, 9, 1);
		setHalignment(title, HPos.CENTER);
		introQuestion.setFont(Font.font("Arial", 18));
		add(introQuestion, 0, 2, 5, 1);
		optionsCBox.getItems().addAll("3", "5", "7"); // TODO available levels
														// to be collected from
														// the database
		add(optionsCBox, 6, 2, 2, 1);
		add(numOfBallsDecided, 8, 2, 1, 1);
		add(pointsColorGrid, 0, 4, 9, 2);
		setPointsColorGrid(pointsColorGrid);
		// add(pointsSoFar, 0, 7, 3, 1); //TODO preperation for future enhancment
		// add(outputPointsSoFar, 5, 7, 2, 1);
		// fler komponenter ska adderas

		numOfBallsDecided.setOnAction(event -> {
			numOfBallsToBePlayed = getNumOfBallsToBePlayed();
			gameStage.setBallsLeftToPlay(numOfBallsToBePlayed);
			sumOfBallPoints = 0;
		});
	}

	public int getNumOfBallsToBePlayed() {
		return Integer.parseInt(optionsCBox.getValue());
	}

	public void setBallPoints(int ballPoints) {
		latestPoints = ballPoints;
		sumOfBallPoints += ballPoints;
	}

	private void setPointsColorGrid(GridPane gPane) {
		// TODO loopa kod istället, paddin, margin, border,...
		double paneWidth = 25.0;
		double paneHeight = 30.0;
		Label labelColor = new Label("Color: ");
		labelColor.setMinSize(45, 30);
		Label labelPoints = new Label("Points: ");
		labelPoints.setMinSize(45, 30);

		// array färger
		// array poängtalen
		Pane pane1 = new Pane();
		pane1.setStyle("-fx-background-color: Aquamarine;");
		pane1.setMinSize(paneWidth, paneHeight);
		Label label1 = new Label("1");
		Pane pane3 = new Pane();
		pane3.setMinSize(paneWidth, paneHeight);
		pane3.setStyle("-fx-background-color: DarkKhaki;");
		Label label3 = new Label("3");
		Pane pane5 = new Pane();
		pane5.setStyle("-fx-background-color: DarkSalmon;");
		pane5.setMinSize(paneWidth, paneHeight);
		Label label5 = new Label("5");
		Pane pane10 = new Pane();
		pane10.setStyle("-fx-background-color: DarkSeaGreen;");
		pane10.setMinSize(paneWidth, paneHeight);
		Label label10 = new Label("10");
		Pane pane20 = new Pane();
		pane20.setStyle("-fx-background-color: GoldenRod;");
		pane20.setMinSize(paneWidth, paneHeight);
		Label label20 = new Label("20");
		Pane pane30 = new Pane();
		pane30.setStyle("-fx-background-color: LightGreen;");
		pane30.setMinSize(paneWidth, paneHeight);
		Label label30 = new Label("30");
		Pane pane40 = new Pane();
		pane40.setStyle("-fx-background-color: NavajoWhite;");
		pane40.setMinSize(paneWidth, paneHeight);
		Label label40 = new Label("40");
		Pane pane50 = new Pane();
		pane50.setStyle("-fx-background-color: Orchid;");
		pane50.setMinSize(paneWidth, paneHeight);
		Label label50 = new Label("50");
		gPane.add(labelColor, 0, 0);
		gPane.add(labelPoints, 0, 1);
		gPane.add(pane1, 1, 0);
		gPane.add(label1, 1, 1);
		gPane.add(pane3, 2, 0);
		gPane.add(label3, 2, 1);
		gPane.add(pane5, 3, 0);
		gPane.add(label5, 3, 1);
		gPane.add(pane10, 4, 0);
		gPane.add(label10, 4, 1);
		gPane.add(pane20, 5, 0);
		gPane.add(label20, 5, 1);
		gPane.add(pane30, 6, 0);
		gPane.add(label30, 6, 1);
		gPane.add(pane40, 7, 0);
		gPane.add(label40, 7, 1);
		gPane.add(pane50, 8, 0);
		gPane.add(label50, 8, 1);

	}

	public void WriteResults() {
		resultOutPut.setText("Spelet är slut. Du fick " + sumOfBallPoints + " poäng.");
		add(resultOutPut, 0, 9, 6, 1);

	}

	private GridPane getHighscoreList(int level) {
		GetNSetDb gsdb = new GetNSetDb();
		GridPane highScorePane = new GridPane();
		List<Result> levelResults = gsdb.getSortedResults(level);
		int i = 0;
		for (Result r : levelResults) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText(i + ".");
			usernameLabel.setText(r.getAccount().getUser());
			pointsLabel.setText(String.valueOf(r.getPoints()));
			highScorePane.add(placeLabel, 0, i);
			highScorePane.add(usernameLabel, 1, i);
			highScorePane.add(pointsLabel, 2, i);
			i++;
		}
		return highScorePane;
	}

}
