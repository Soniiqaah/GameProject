package application;

import java.util.ArrayList;
import java.util.List;

//import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Jannike
 *
 *         A class that represents the playingfield of the game and includes its
 *         functions
 */
public class PlayingField extends Pane {

	int playingFieldWidth; // ska man ha int eller double? TODO
	int playingFieldHeight;

	List<Zone> zoneList; // kan man l�sa det p� annat s�tt �n med globala
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
		this.playingFieldWidth = xSize;
		this.playingFieldHeight = ySize;
		Rectangle crop = new Rectangle(playingFieldWidth, playingFieldHeight);
		setClip(crop);
	}

	
	// markerar startpunkten p� spelplanen
	public Path createStartPoint(double startXCoord, double startYCoord) {
		int startMarkX = playingFieldWidth / 2;
		int startMarkY = playingFieldHeight;
		Path path = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(startMarkX);
		moveTo.setY(startMarkY);
		LineTo line1 = new LineTo(startMarkX + 5, startMarkY);
		LineTo line2 = new LineTo(startMarkX, startMarkY - 8);
		LineTo line3 = new LineTo(startMarkX - 5, startMarkY);
		LineTo line4 = new LineTo(startMarkX, startMarkY);
		path.getElements().addAll(moveTo, line1, line2, line3, line4);
		path.setFill(Color.BLACK);
		return path;
	}

	/**
	 * placingZones()
	 * 
	 * The zones that gives points are declared, defined and added to the
	 * playingfield in this method
	 * 
	 * TODO Map f�r att para ihop po�ng med zon?
	 */
	protected void placingZones() {
		zoneList = new ArrayList<Zone>();
		zoneList.add(new Zone(0, 0, 60, 20));
		zoneList.add(new Zone(playingFieldWidth, 0, 50, 30));
		zoneList.add(new Zone(playingFieldWidth / 2, playingFieldHeight / 4, 5, 40));
		zoneList.add(new Zone(playingFieldWidth / 4, playingFieldHeight * 5 / 8, 10, 20));
		zoneList.add(new Zone(playingFieldWidth / 2, playingFieldHeight * 5 / 8, 20, 10));
		zoneList.add(new Zone(playingFieldWidth * 3 / 4, playingFieldHeight * 5 / 8, 15, 10));
		zoneList.add(new Zone(0, playingFieldHeight, 40, 40));
		zoneList.add(new Zone(playingFieldWidth, playingFieldHeight, 30, 50));
		for (Zone zone : zoneList) {
			zone.setStroke(Color.DARKGREY);
			this.getChildren().add(zone);
		}
		// for (int j : pointsList) {
		// System.out.println("j: " + j);
		// }
	}



	public void checkInZone(Ball ball) {
		zoneID = -1;
		// for (Circle zone : zoneList)
		for (int k = 0; k < zoneList.size(); k++) {
			ball.inAZone(zoneList.get(k));
			if (ball.isInAZone) {
				zoneID = k;
				break;
			}
		}
	}

	// f�r varje kast anropas checkinZone, zonID registreras, mha zonID:t letar
	// man upp po�ngen i pointList, summerar efter varje kast och skriver ut p�
	// sk�rmen. N�r alla bollar �r kastade ska resultatet skrivas ut p� sk�rmen
	
	// skapa ny klass f�r anv�ndare?
//	private List<Integer> collectResult()resultList
	
}