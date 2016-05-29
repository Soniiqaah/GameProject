package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DemoBall extends Circle{

	public DemoBall() {
		
	};
	
	public DemoBall(double x, double y, double radius, Color color) {
		super(x, y, radius);
		setFill(color);
		setStroke(Color.DARKGREY);
	}
	
	public static Pane createDemoBallOnPane() {
		DemoBall demoBall = new DemoBall(20, 20, 20, Color.CHARTREUSE);
		Pane demoBallOnPane = new Pane(demoBall);
		return demoBallOnPane;
	}

}
