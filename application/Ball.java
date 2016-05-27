package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball extends Circle {
	private double dx, dy;
	private double pfWidth, pfHeight;
	public boolean isBallStopped = false;
	public boolean isInAZone = false;
	private Timeline animation;
	public double stopXCoord;
	public double stopYCoord;

	Ball(double x, double y, double radius, Color color, PlayingField playingField, double dx,
			double dy) {
		super(x, y, radius);
		this.pfWidth = playingField.getWidth();
		this.pfHeight = playingField.getHeight();
		this.dx = dx;
		this.dy = dy;
		setFill(color); // Set ball color
	}

	protected void moveBall() {
		// this inte nödvändigt men förtydligande i sammanhanget TODO
		if ((this.getCenterX() < this.getRadius()) && (this.dx < 0)) {
			this.dx *= -1; // change direction when hitting the left wall
		} else if ((this.getCenterX() > this.pfWidth - this.getRadius()) && (this.dx > 0)) {
			this.dx *= -1; // change direction when hitting the right wall
		}
		if ((this.getCenterY() < this.getRadius()) && (this.dy < 0)) {
			this.dy *= -1; // change direction when hitting the top wall
		} else if ((this.getCenterY() > this.pfHeight - this.getRadius()) && (this.dy > 0)) {
			this.dy *= -1; // change direction when hitting the bottom wall
		}

		// Ball movement
		this.setCenterX(this.dx + this.getCenterX());
		this.setCenterY(this.dy + this.getCenterY());
		// Decreasing the speed
		this.dx *= 0.995;
		this.dy *= 0.995;
		// Make the ball stop a bit quicker
		if ((Math.abs(this.dx) < 0.05) && (Math.abs(this.dy) < 0.05)) {
			this.dx = 0;
			this.dy = 0;
		}
		// The ball has stopped
		if ((this.dx == 0) && (this.dy == 0)) {
			isBallStopped = true;
			animation.stop(); // Stop animation
			stopXCoord = this.getCenterX();
			stopYCoord = this.getCenterY();
		}
		// When the ball has stopped moving it gets a black border
		if (isBallStopped) {
			this.setStroke(Color.BLACK);
			if (isInAZone) {
				this.setFill(Color.CORNFLOWERBLUE);
			}
		}
	}

	// The animation of the balls movement
	public void animateBallMovement() {
		animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	public boolean inAZone(Circle zone) {
		double distanceToZoneCenter;
		distanceToZoneCenter = Math.sqrt(Math.pow((this.stopXCoord - zone.getCenterX()), 2)
				+ (Math.pow((this.stopYCoord - zone.getCenterY()), 2)));
		if (distanceToZoneCenter <= zone.getRadius()) {
			isInAZone = true;
		}
		return isInAZone;
	}

}
