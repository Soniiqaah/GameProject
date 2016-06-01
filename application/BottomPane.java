package application;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * 
 * @author Jannike
 *
 *         A class where the bottom pane is managed
 */
public class BottomPane extends GridPane {

	/**
	 * setupBottomPane(int numOfBalls)
	 * 
	 * Adds a number of balls to the bottom pane to tell the player how many
	 * tryouts are left
	 * 
	 * @param numOfBall
	 *            - an integer, the number of balls to be visable on the bottom
	 *            pane
	 */
	public void setupBottomPane(int numOfBalls) {
		addDemoBallsToGrid(numOfBalls);
	}

	/**
	 * addDemoBallsToGrid(int numOfBalls)
	 * 
	 * Resets the pane and add new demoballs in a loop
	 * 
	 * @param numOfBalls
	 *            - an integer, the number of times the loop will loop
	 */
	private void addDemoBallsToGrid(int numOfBalls) {
		// TODO padding etc
		this.getChildren().clear();
		for (int i = 0; i < numOfBalls; i++) {
			add(DemoBall.createDemoBallOnPane(), i, 0);
		}
	}

}
