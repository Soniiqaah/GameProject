package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Jannike
 *
 *         A class that represents a demo ball, and its functions
 * 
 *         The demo balls has several similarites with balls, but are only used
 *         to visualize the number of ball that are left to play
 */
public class DemoBall extends Circle {

	public DemoBall() {
	};

	/**
	 * Constructor of the class DemoBall
	 * 
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param color
	 */
	private DemoBall(double x, double y, double radius, Color color) {
		super(x, y, radius);
		setFill(color);
		setStroke(Color.DARKGREY);
	}

	/**
	 * createDemoBallOnPane()
	 * 
	 * Creates a pane with a demoball added to it
	 * 
	 * @return a Pane 
	 */
	public static Pane createDemoBallOnPane() {
		DemoBall demoBall = new DemoBall(20, 20, 20, Color.CHARTREUSE);
		Pane demoBallOnPane = new Pane(demoBall);
		return demoBallOnPane;
	}

}
