package application;

import java.awt.Color;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author Jannike
 *
 *         Class to communicate with the user, provide information about the
 *         game and displaying the result
 * 
 *         Displays the mapping between colour of a zone and the points the
 *         player gets when a ball stops in it
 */
public class InfoPane extends GridPane {

	private ComboBox<String> optionsCBox;
	private int numOfBallsToBePlayed;
	private GameStage gameStage;
	private int latestPoints;
	private int sumOfBallPoints = 0;
	private Label resultOutPut = new Label();
	private GridPane highScorePane;

	/**
	 * Constructor
	 * 
	 * @param gameStage
	 */
	public InfoPane(GameStage gameStage) {
		this.gameStage = gameStage;

		GetNSetDb gsdb = new GetNSetDb();
		List<GameLevel> gameLevels = gsdb.getGameLevels();
		// The gamelevels are set if the database is empty
		if (gameLevels.isEmpty()) { 
			gsdb.setGameLevels(new int[] { 3, 5, 7 });
			gameLevels = gsdb.getGameLevels();
		}

		optionsCBox = new ComboBox<String>();
		Text title = new Text("Skeeball");
		Text introQuestion = new Text("How many balls do you want to play?");
		Button numOfBallsDecided = new Button("OK");
		GridPane pointsColorGrid = new GridPane();
		int totalSum = 0;
		int numOfBallsLeftToPlay = 0;
		setHgap(10);
		setVgap(10);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		add(title, 0, 0, 9, 1);
		setHalignment(title, HPos.CENTER);
		introQuestion.setFont(Font.font("Arial", 14));
		add(introQuestion, 0, 2, 5, 1);
		for (GameLevel gameLevel : gameLevels) { 
			optionsCBox.getItems().add("" + gameLevel.getNumOfTryOuts());
		}
		add(optionsCBox, 6, 2, 2, 1);
		add(numOfBallsDecided, 8, 2, 1, 1);
		add(pointsColorGrid, 0, 4, 9, 2);
		setPointsColorGrid(pointsColorGrid);

		add(resultOutPut, 0, 9, 6, 1);

		numOfBallsDecided.setOnAction(event -> {
			numOfBallsToBePlayed = getNumOfBallsToBePlayed();
			gameStage.setBallsLeftToPlay(numOfBallsToBePlayed);
			sumOfBallPoints = 0;
			resultOutPut.setText("");
			updateHighScoreList();
		});
	}
/**
 * TODO
 */
	private void updateHighScoreList() {
		getChildren().remove(highScorePane);
		highScorePane = getHighscoreLists(numOfBallsToBePlayed);
		add(highScorePane, 0, 10, 8, 1);
	}

	/**
	 * getNumOfBallsToBePlayed()
	 * 
	 * Gets the value chosen in the combobox
	 * 
	 * @return - an int, the number of ball that will be used in the game, the
	 *         same as level
	 */
	public int getNumOfBallsToBePlayed() {
		return Integer.parseInt(optionsCBox.getValue());
	}

	/**
	 * setBallPoints(int ballPoints)
	 * 
	 * Gets the points from the latest ball played and accumulates the sum
	 * 
	 * @param ballPoints
	 *            - an int, the points from the latest ball played
	 */
	public void setBallPoints(int ballPoints) {
		latestPoints = ballPoints;
		sumOfBallPoints += ballPoints;
	}

	/**
	 * setPointsColorGrid(GridPane gPane)
	 * 
	 * Creates the grid presenting the mapping between the colours of a zone and
	 * the point value of the zone
	 * 
	 * @param gPane
	 *            - a GridPane
	 */
	private void setPointsColorGrid(GridPane gPane) {
		double paneWidth = 25.0;
		double paneHeight = 30.0;
		Label labelColor = new Label("Color: ");
		labelColor.setMinSize(45, 30);
		Label labelPoints = new Label("Points: ");
		labelPoints.setMinSize(45, 30);
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

	/**
	 * WriteResults()
	 * 
	 * When the game is finished the result is displayed and the highscore list is updated
	 * 
	 */
	public void WriteResults() {
		resultOutPut.setText("The game is over. You got " + sumOfBallPoints + " points.");
		resultOutPut.setFont((Font.font("Arial", 14)));
		updateHighScoreList();
	}

	/**
	 * getHighscoreList(int level)
	 * 
	 * Creates a highscore list for the gamelevel currently choosed
	 * 
	 * @param level
	 *            - an int, represents the gamelevel, is the number of balls to
	 *            be played at this time
	 * @return a GridPane - the highscore list in a gridpane format
	 */
	private GridPane getHighscoreLists(int level) {
		String uname = InlogView.getCurrentUser().getUser();
		System.out.println("\n\n\n\n uname: "+ uname);
		GetNSetDb gsdb = new GetNSetDb();
		GridPane highScorePane = new GridPane();
		List<Result> levelResults = gsdb.getSortedResults(level);
		List<Result> oneUserLevelResults = gsdb.getSortedResultsOneUser(uname, level);
		Label highscoreHeadline = new Label("Top 10, " + level + " balls");
		Label highscoreHeadlineOneUser = new Label("Your top 10 results, " + level + " balls");
		highscoreHeadline.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		highscoreHeadlineOneUser.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		int i = 0;
		highScorePane.add(highscoreHeadline, 0, 0, 3, 1);
		for (Result r : levelResults) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText((i + 1) + ".");
			usernameLabel.setText(r.getAccount().getUser());
			pointsLabel.setText(String.valueOf(r.getPoints()));
			highScorePane.getColumnConstraints().add(new ColumnConstraints(20));
			highScorePane.add(placeLabel, 0, i + 1);
			highScorePane.getColumnConstraints().add(new ColumnConstraints(150));
			highScorePane.add(usernameLabel, 1, i + 1);
			highScorePane.getColumnConstraints().add(new ColumnConstraints(50));
			highScorePane.add(pointsLabel, 2, i + 1);
			i++;
		}
		int j = 0;
		highScorePane.add(highscoreHeadlineOneUser, 0, 12, 3, 1);
		for (Result r : oneUserLevelResults) {
			Label placeLabel = new Label();
			Label usernameLabel = new Label();
			Label pointsLabel = new Label();
			placeLabel.setText((j + 1) + ".");
			usernameLabel.setText(uname);
			pointsLabel.setText(String.valueOf(r.getPoints()));
			highScorePane.getColumnConstraints().add(new ColumnConstraints(15));
			highScorePane.add(placeLabel, 0, j + 13);
			highScorePane.getColumnConstraints().add(new ColumnConstraints(150));
			highScorePane.add(usernameLabel, 1, j + 13);
			highScorePane.getColumnConstraints().add(new ColumnConstraints(50));
			highScorePane.add(pointsLabel, 2, j + 13);
			j++;
		}
		
		
		return highScorePane;
	}

}
 