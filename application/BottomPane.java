package application;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BottomPane extends GridPane {

	public GridPane setupBottomPane() {
		return addDemoBallsToGrid(InfoPane.getNumOfBallsToBePlayed());
	}
	
	private GridPane addDemoBallsToGrid(int numOfBalls) {
		GridPane demoBallGrid = new GridPane();
		// TODO padding etc
		for (int i = 0; i < numOfBalls; i++) {
			demoBallGrid.add(DemoBall.createDemoBallOnPane(), i, 0);
		}
		return demoBallGrid;
	}
	
}
