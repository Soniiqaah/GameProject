package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Ball extends Circle {
	private double dx = 1, dy = 1;
	private double paneWidth, paneHeight;
	public boolean ballStopped = false;
	private Timeline animation;

	Ball(double x, double y, double radius, Color color, double paneWidth,
			double paneHeight, double dx, double dy) {
		super(x, y, radius);
		this.paneWidth = paneWidth;
		this.paneHeight = paneHeight;
		this.dx = dx;
		this.dy = dy;
		setFill(color); // Set ball color
	}

	protected void moveBall() {
		// Check boundaries
		// this inte nödvändigt men förtydligande i sammanhanget TODO
		if ((this.getCenterX() < this.getRadius()) && (this.dx < 0)) {
			this.dx *= -1; // change direction when hitting the left wall
		} else if ((this.getCenterX() > this.paneWidth - this.getRadius())
				&& (this.dx > 0)) {
			this.dx *= -1; // change direction when hitting the right wall
		}
		if ((this.getCenterY() < this.getRadius()) && (this.dy < 0)) {
			this.dy *= -1; // change direction when hitting the top wall
		} else if ((this.getCenterY() > this.paneHeight - this.getRadius())
				&& (this.dy > 0)) {
			this.dy *= -1; // change direction when hitting the bottom wall
		}

		// Ball movement
		this.setCenterX(this.dx + this.getCenterX());
		this.setCenterY(this.dy + this.getCenterY());
		// Decreasing the speed
		this.dx *= 0.99;
		this.dy *= 0.99;
		// Make the ball stop a bit quicker
		if ((Math.abs(this.dx) < 0.005) && (Math.abs(this.dy) < 0.005)) {
			this.dx = 0;
			this.dy = 0;
		}
		// The ball has stopped
		if ((this.dx == 0) && (this.dy == 0)) {
			ballStopped = true;
			animation.stop(); // Stop animation
		}
	}

	// The animation of the balls movement
	public void animateBallMovement() {
		animation = new Timeline(
				new KeyFrame(Duration.millis(50), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

	}

}
