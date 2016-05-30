package application;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BottomPane extends GridPane {

	public void setupBottomPane(int numOfBalls) {
		addDemoBallsToGrid(numOfBalls);// 
	}
	
	private void addDemoBallsToGrid(int numOfBalls) {
		// TODO padding etc
		this.getChildren().clear();
		for (int i = 0; i < numOfBalls; i++) {
			add(DemoBall.createDemoBallOnPane(), i, 0);
		}
	}
	
}
