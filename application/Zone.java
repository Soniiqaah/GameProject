package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Zone extends Circle {

	private int points;

	public Zone(double x, double y, double radius, int points) {
		super(x, y, radius);
		this.points = points;
		setFill(getColor(this.points));
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * setColor(int points)
	 * 
	 * The input is a int value that is matched with a color
	 * 
	 * @param points
	 *            - A int value representing number of points
	 * @return - Returns the name of a color
	 */
	private Color getColor(int points) {
		Color colorName;
		switch (points) {
		case 1:
			colorName = Color.web("Aquamarine");
			break;
		case 3:
			colorName = Color.web("DarkKhaki"); // #BDB76B
			break;
		case 5:
			colorName = Color.web("DarkSalmon"); // #E9967A
			break;
		case 10:
			colorName = Color.web("DarkSeaGreen"); // #8FBC8F
			break;
		case 20:
			colorName = Color.web("GoldenRod"); // #DAA520
			break;
		case 30:
			colorName = Color.web("LightGreen"); // #90EE90
			break;
		case 40:
			colorName = Color.web("NavajoWhite"); // #FFDEAD
			break;
		case 50:
			colorName = Color.web("Orchid"); // #DA70D6;
			break; // break saknades i tidigare version av koden så radie = 5
					// gav svart zon TODO
		default:
			colorName = Color.web("Black");
		}
		return colorName;
	}

	
}
