package application;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;

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
		zoneList.add(new Circle(0, 0, 50));
		zoneList.add(new Circle(playingfieldWidth, 0, 50));
		zoneList.add(new Circle(playingfieldWidth / 2, playingfieldHeight / 4, 15));
		zoneList.add(new Circle(playingfieldWidth / 3, playingfieldHeight * 5 / 8, 30));
		zoneList.add(new Circle(playingfieldWidth * 2 / 3, playingfieldHeight * 5 / 8, 30));
		zoneList.add(new Circle(0, playingfieldHeight, 50));
		zoneList.add(new Circle(playingfieldWidth, playingfieldHeight, 50));
		for (int i = 0; i < zoneList.size(); i++) {
			zoneList.get(i).setFill(Color.AQUAMARINE);
			this.getChildren().add(zoneList.get(i));
		}
	}

}
