package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Jannike
 *
 *A class that represents a zone, and its functions
 */
public class Zone extends Circle {

	private int points;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param points
	 */
	public Zone(double x, double y, double radius, int points) {
		super(x, y, radius);
		this.points = points;
		setFill(getColor(this.points));
	}

	/**
	 * getPoints()
	 * 
	 * @return an int
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * setPoints(int points)
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * getColor(int points)
	 * 
	 * The input is an int value that is matched with a color
	 * 
	 * @param points
	 *            - A int value representing number of points
	 * @return - Returns the name of a color
	 */
	private Color getColor(int points) {
		Color colorName;
		switch (points) {
		case 1:
			colorName = Color.web("Aquamarine");  //#7FFFD4
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
			break; 
		default:
			colorName = Color.web("Black");
		}
		return colorName;
	}

}
