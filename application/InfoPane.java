package application;

import java.awt.TextField;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InfoPane extends GridPane {

	// rita ut vilka po�ng de olika f�rgerna p� zoner betyder, L�GST PRIORITET
	// lablar, textrutor etc f�r info och dialog
	// dialog f�r val av antal bollar
	
	public InfoPane() {
		ComboBox<String> optionsCBox = new ComboBox<String>();;
		Text title = new Text("Skeeball");
		Text introQuestion = new Text("Hur m�nga bollar vill du spela?");
		Button numOfBallsDecided = new Button("OK");
		GridPane pointsGrid = new GridPane();
		Label pointsSoFar = new Label("Po�ng i p�g�ende spel: ");
		Label outputPointsSoFar = new Label();
		Label ballsLeftToPlay = new Label("Antal bollar kvar att spela: ");
		Label outputBallsLeftToPlay = new Label();
		int totalSum = 0;
		Label resultOutPut = new Label("Spelet �r slut. Du fick " + totalSum + " po�ng.");
		int numOfBallsPlayed = 0;
		TextArea highscoreHeadline = new TextArea("Highscore f�r " + numOfBallsPlayed + " spelade bollar:"); 
		TextArea highscoreResults = new TextArea();
		setHgap(10);
		setVgap(10);
		// s�tta fonter, centrering etc TODO, nu �r det bara exempel
		title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		add(title, 0, 0, 9, 1);
		setHalignment(title, HPos.CENTER);
		introQuestion.setFont(Font.font("Arial", 18));
		add(introQuestion, 0, 2, 5, 1);
		optionsCBox.getItems().addAll("3", "5", "7");
		add(optionsCBox, 6, 2, 2, 1);
		add(numOfBallsDecided, 8, 2, 1, 1);
		
	}
	
	public static int getNumOfBallsToBePlayed() {
		return 6;
		
	}
	
}
