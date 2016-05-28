package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * 
 * @author Jannike
 *
 *         A class that represents a ball and its functions
 */
public class Ball extends Circle {
	private double dx, dy;
	private double pfWidth, pfHeight;
	private Timeline animation;
	private PlayingField playingField;
	private boolean isBallStopped = false;
	private boolean isInAZone = false;

	/**
	 * Constructor of the class
	 * 
	 * @param x
	 *            - A double, defines the x-coordinate of the centre of the ball
	 * @param y
	 *            - A double, defines the y-coordinate of the centre of the ball
	 * @param radius
	 *            - A double, defines the radius of the ball
	 * @param color
	 *            - A Color, the default color of a ball
	 * @param playingField
	 *            - A PlayingField, the playingField that the ball is associated
	 *            with
	 * @param dx
	 *            - A double, the speed in the x-direction of the ball
	 * @param dy
	 *            - A double, the speed in the y-direction of the ball
	 */
	public Ball(double x, double y, double radius, Color color, PlayingField playingField,
			double dx, double dy) {
		super(x, y, radius);
		this.playingField = playingField;
		this.pfWidth = playingField.getWidth();
		this.pfHeight = playingField.getHeight();
		this.dx = dx;
		this.dy = dy;
		setFill(color);
	}

	/**
	 * moveBall() GRUNDKOD FRÅN
	 * http://www.cs.armstrong.edu/liang/intro10e/html/MultipleBounceBall.html
	 * 
	 * The method handles speed and direction of the ball TODO bättre
	 * formulering
	 * 
	 * Sets a variable to true when the ball stops
	 * 
	 * When the ball stops it gets a black border, if it stops in a zone it also
	 * changes color
	 */
	protected void moveBall() {

		if ((getCenterX() < getRadius()) && (dx < 0)) {
			dx *= -1; // change direction when hitting the left wall
		} else if ((getCenterX() > pfWidth - getRadius()) && (dx > 0)) {
			dx *= -1; // change direction when hitting the right wall
		}
		if ((getCenterY() < getRadius()) && (dy < 0)) {
			dy *= -1; // change direction when hitting the top wall
		} else if ((getCenterY() > pfHeight - getRadius()) && (dy > 0)) {
			dy *= -1; // change direction when hitting the bottom wall
		}

		// Ball movement
		setCenterX(dx + getCenterX());
		setCenterY(dy + getCenterY());
		// Decreasing the speed
		dx *= 0.995;
		dy *= 0.995;
		// Make the ball stop a bit quicker
		if ((Math.abs(dx) < 0.05) && (Math.abs(dy) < 0.05)) {
			dx = 0;
			dy = 0;
		}
		// The ball has stopped
		if ((dx == 0) && (dy == 0)) {
			isBallStopped = true;
			animation.stop();
		}
	}

	private void whenBallStopped() {
		// When the ball has stopped moving it gets a black border and a check
		// if it is in a zone is made
		if (isBallStopped()) {
			setStroke(Color.BLACK);
			playingField.calculatePoints(this);
		}
		// When the balls stops in a zone it changes color
		if (isInAZone()) {
			setFill(Color.CORNFLOWERBLUE);
		}
	}

	public boolean isBallStopped() {
		return isBallStopped;
	}

	public boolean isInAZone() {
		return isInAZone;
	}

	public void playBall() {
		moveBall();
		whenBallStopped();
	}
	/**
	 * animateBallMovement() GRUNDKOD FRÅN
	 * http://www.cs.armstrong.edu/liang/intro10e/html/MultipleBounceBall.html
	 * 
	 * The animation of the balls movement
	 */
	public void animateBallMovement() {
		animation = new Timeline(new KeyFrame(Duration.millis(10), e -> playBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	/**
	 * inAZone(Zone zone)
	 * 
	 * Check if the ball stopped within a zone - if the distance between the
	 * centre coordinates of the ball and the centre coordinates of the zone is
	 * shorter than the length of the zone's radius the ball is within the zone
	 * 
	 * @param zone
	 *            - A Zone, a part of the playingfield where the player gets
	 *            points if a ball stops there
	 * @return - A boolean, if the ball stopped in a zone the variable is set to
	 *         true, else it remains false
	 */
	public boolean inAZone(Zone zone) {
		double distanceToZoneCenter;
		distanceToZoneCenter = Math.sqrt(Math.pow((getCenterX() - zone.getCenterX()), 2)
				+ (Math.pow((getCenterY() - zone.getCenterY()), 2)));
		if (distanceToZoneCenter <= zone.getRadius()) {
			isInAZone = true;
		}
		return isInAZone;
	}

}
