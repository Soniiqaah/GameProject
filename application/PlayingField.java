package application;

import java.util.ArrayList;
import java.util.List;

//import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Jannike
 *
 *         A class that represents the playingfield of the game and includes its
 *         functions
 */
public class PlayingField extends Pane {

	int playingfieldWidth; // ska man ha int eller double? TODO
	int playingfieldHeight;
	List<Circle> zoneList; // kan man lösa det på annat sätt än med globala
							// variabler? TODO
	List<Integer> pointsList;
	List<Integer> resultList;
	public int zoneID;

	/**
	 * TODO ordval, grammatik? protected?
	 * 
	 * setSize(int xSize, int ySize)
	 * 
	 * Sets the size of the playingfield and crops anything on the outside of
	 * the field
	 * 
	 * @param xSize
	 *            - int that specifies the width of the field
	 * @param ySize
	 *            - int that specifies the height of the field
	 */
	protected void setSize(int xSize, int ySize) {
		this.setMaxSize(xSize, ySize);
		this.setMinSize(xSize, ySize);
		this.playingfieldWidth = xSize;
		this.playingfieldHeight = ySize;
		Rectangle crop = new Rectangle(playingfieldWidth, playingfieldHeight);
		setClip(crop);
	}

	/**
	 * placingZones()
	 * 
	 * The zones that gives points are declared, defined and added to the
	 * playingfield in this method
	 * 
	 * TODO Map för att para ihop poäng med zon?
	 */
	protected void placingZones() {
		zoneList = new ArrayList<Circle>();
		pointsList = new ArrayList<Integer>();
		zoneList.add(new Circle(0, 0, 60));
		zoneList.add(new Circle(playingfieldWidth, 0, 50));
		zoneList.add(new Circle(playingfieldWidth / 2, playingfieldHeight / 4, 5));
		zoneList.add(new Circle(playingfieldWidth / 4, playingfieldHeight * 5 / 8, 10));
		zoneList.add(new Circle(playingfieldWidth / 2, playingfieldHeight * 5 / 8, 20));
		zoneList.add(new Circle(playingfieldWidth * 3 / 4, playingfieldHeight * 5 / 8, 15));
		zoneList.add(new Circle(0, playingfieldHeight, 40));
		zoneList.add(new Circle(playingfieldWidth, playingfieldHeight, 30));
		int i = 0;
		for (Circle zone : zoneList) {
			pointsList.add(setPoints(zone.getRadius()));
			zone.setFill(setColor(pointsList.get(i)));// TODO kan det göras
														// snyggare?
			zone.setStroke(Color.DARKGREY);
			i++;
			this.getChildren().add(zone);
		}
		// for (int j : pointsList) {
		// System.out.println("j: " + j);
		// }
	}

	/**
	 * setPoints(Double radius)
	 * 
	 * This method sets the value of points depending of the value of radius
	 * 
	 * @param radius
	 *            - A double value that represents the radius
	 * @return - A int value that is the points you are awarded
	 */
	private int setPoints(Double radius) {
		int points = 0;
		if (radius == 5) {
			points = 50;
		} else if (radius == 10) {
			points = 40;
		} else if (radius == 15) {
			points = 30;
		} else if (radius == 20) {
			points = 20;
		} else if (radius == 30) {
			points = 10;
		} else if (radius == 40) {
			points = 5;
		} else if (radius == 50) {
			points = 3;
		} else if (radius == 60) {
			points = 1;
		}
		return points;
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
	private Color setColor(int points) {
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

	public void checkInZone(Ball ball) {
		zoneID = -1;
		// for (Circle zone : zoneList)
		for (int k = 0; k < zoneList.size(); k++) {
			ball.inAZone(zoneList.get(k));
			if (ball.isInAZone) {
				zoneID = k;
			}
		}
	}

	// för varje kast anropas checkinZone, zonID registreras, mha zonID:t letar
	// man upp poängen i pointList, summerar efter varje kast och skriver ut på
	// skärmen. När alla bollar är kastade ska resultatet skrivas ut på skärmen
	
	// skapa ny klass för användare?
//	private List<Integer> collectResult()resultList
	
}