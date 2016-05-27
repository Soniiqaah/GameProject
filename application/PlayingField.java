package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.PointLight;

//import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PlayingField extends Pane {

	int playingfieldWidth; // ska man ha int eller double? TODO
	int playingfieldHeight;

	protected void setSize(int xSize, int ySize) {
		this.setMaxSize(xSize, ySize);
		this.setMinSize(xSize, ySize);
		this.playingfieldWidth = xSize;
		this.playingfieldHeight = ySize;
		Rectangle crop = new Rectangle(playingfieldWidth, playingfieldHeight);
		setClip(crop);
	}

	protected void placingZones() {
		List<Circle> zoneList = new ArrayList<Circle>();
		List<Integer> pointsList = new ArrayList<Integer>();
		zoneList.add(new Circle(0, 0, 50));
		zoneList.add(new Circle(playingfieldWidth, 0, 60));
		zoneList.add(new Circle(playingfieldWidth / 2, playingfieldHeight / 4, 10));
		zoneList.add(new Circle(playingfieldWidth / 3, playingfieldHeight * 5 / 8, 30));
		zoneList.add(new Circle(playingfieldWidth * 2 / 3, playingfieldHeight * 5 / 8, 30));
		zoneList.add(new Circle(0, playingfieldHeight, 50));
		zoneList.add(new Circle(playingfieldWidth, playingfieldHeight, 50));
		// for (int i = 0; i < zoneList.size(); i++) {
		int i = 0;
		for (Circle zone : zoneList) {
			pointsList.add(setPoints(zone.getRadius()));
			zone.setFill(setColor(pointsList.get(i)));// TODO
			i++;
//			zone.setFill(Color.AQUAMARINE);
			this.getChildren().add(zone);
		}
		// for (int i = 0; i < pointsList.size(); i++) {
		// System.out.println("i: " + i + " poäng: " + pointsList.get(i));
		// }
	}

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

	private Color setColor(int points) {
		Color colorName;
		switch (points) {
		case 1:
			colorName = Color.web("Aquamarine"); // decode("7FFFD4");
													// //Aquamarine;
			break;
		case 3:
			colorName = Color.web("DarkKhaki"); // #BDB76B //DarkKhaki;
			break;
		case 5:
			colorName = Color.web("DarkSalmon"); // #E9967A //DarkSalmon;
			break;
		case 10:
			colorName = Color.web("DarkSeaGreen"); // #8FBC8F //DarkSeaGreen;
			break;
		case 20:
			colorName = Color.web("GoldenRod"); // #DAA520 //GoldenRod;
			break;
		case 30:
			colorName = Color.web("LightGreen"); // #90EE90 //LightGreen;
			break;
		case 40:
			colorName = Color.web("NavajoWhite"); // #FFDEAD //NavajoWhite;
			break;
		case 50:
			colorName = Color.web("Orchid"); // #DA70D6; //orchid
		default:
			colorName = Color.web("black");
		}
		return colorName;
	}

}
