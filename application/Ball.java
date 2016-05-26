package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Ball extends Circle {
	private double dx = 1, dy = 1;
	private double pfWidth, pfHeight;
	public boolean ballStopped = false;
	private Timeline animation;
	private Circle stoppedBall;
	private double stopXCoord;
	private double stopYCoord;
	private double ballRadius;
	private PlayingField playingField;
	private Color color;

	Ball(double x, double y, double radius, Color color,
			PlayingField playingField, double dx, double dy) {
		super(x, y, radius);
		this.color = color;
		this.playingField = playingField;
		this.pfWidth = playingField.getWidth();
		this.pfHeight = playingField.getHeight();
		this.dx = dx;
		this.dy = dy;
		setFill(color); // Set ball color
	}

	protected void moveBall() {
		// Check boundaries
		// this inte n�dv�ndigt men f�rtydligande i sammanhanget TODO
		if ((this.getCenterX() < this.getRadius()) && (this.dx < 0)) {
			this.dx *= -1; // change direction when hitting the left wall
		} else if ((this.getCenterX() > this.pfWidth - this.getRadius())
				&& (this.dx > 0)) {
			this.dx *= -1; // change direction when hitting the right wall
		}
		if ((this.getCenterY() < this.getRadius()) && (this.dy < 0)) {
			this.dy *= -1; // change direction when hitting the top wall
		} else if ((this.getCenterY() > this.pfHeight - this.getRadius())
				&& (this.dy > 0)) {
			this.dy *= -1; // change direction when hitting the bottom wall
		}

		// Ball movement
		this.setCenterX(this.dx + this.getCenterX());
		this.setCenterY(this.dy + this.getCenterY());
		// Decreasing the speed
		this.dx *= 0.97;
		this.dy *= 0.97;
		// Make the ball stop a bit quicker
		if ((Math.abs(this.dx) < 0.05) && (Math.abs(this.dy) < 0.05)) {
			this.dx = 0;
			this.dy = 0;
		}
		// The ball has stopped
		if ((this.dx == 0) && (this.dy == 0)) {
			ballStopped = true;
			animation.stop(); // Stop animation
			stopXCoord = this.getCenterX();
			stopYCoord = this.getCenterY();
			ballRadius = this.getRadius();
		}
		// A marker is made when the ball has stopped moving
		if (ballStopped) {
			stoppedBall = new Circle(stopXCoord, stopYCoord, ballRadius);
			stoppedBall.setFill(color);
			stoppedBall.setStroke(Color.BLACK);
			playingField.getChildren().add(stoppedBall);
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
