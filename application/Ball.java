package application;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Ball extends Circle {
	private double dx = 1, dy = 1;
	private double paneWidth, paneHeight;

	Ball(double x, double y, double radius, Color color, double paneWidth, double paneHeight) {
		super(x, y, radius);
		this.paneWidth = paneWidth;
		this.paneHeight = paneHeight;
		setFill(color); // Set ball color
	}

	protected void moveBall() {
		// Check boundaries
		// this inte nödvändigt men förtydligande i sammanhanget TODO
		if (this.getCenterX() < this.getRadius() || this.getCenterX() > this.paneWidth - this.getRadius()) {
			this.dx *= -1; // Change ball move direction
		}
		if (this.getCenterY() < this.getRadius() || this.getCenterY() > this.paneHeight - this.getRadius()) {
			this.dy *= -1; // Change ball move direction
		}

		// Adjust ball position
		this.setCenterX(this.dx + this.getCenterX());
		this.setCenterY(this.dy + this.getCenterY());
		this.dx *= 0.999; // inte original
		this.dy *= 0.999; // inte original
	}
}
