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
 *         A class that represents a ball, and its functions
 */
public class Ball extends Circle {
	private double dx, dy;
	private double pfWidth, pfHeight;
	private Timeline animation;
	private PlayingField playingField;
	private boolean isBallStopped = false;
	private boolean isInAZone = false;
	private int ballPoints = 0;
	private GameStage gameStage;

	/**
	 * TODO Constructor of the class
	 * 
	 */
	public Ball(double x, double y, double radius, Color color, PlayingField playingField,
			GameStage gameStage, double dx, double dy) {
		super(x, y, radius);
		this.playingField = playingField;
		this.pfWidth = playingField.getWidth();
		this.pfHeight = playingField.getHeight();
		this.gameStage = gameStage;
		this.dx = dx; // sets the x component of the speed
		this.dy = dy; // sets the y component of the speed
		setFill(color);
	}

	/**
	 * moveBall() based on
	 * http://www.cs.armstrong.edu/liang/intro10e/html/MultipleBounceBall.html
	 * 
	 * The method handles speed and direction of the ball 
	 * 
	 * Sets a variable to true when the ball stops
	 * 
	 */
	private void moveBall() {

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

	/**
	 * whenBallStopped()
	 * 
	 * When the ball has stopped moving it gets a black border.
	 * 
	 * If it stopped in a zone the color of the ball is changed.
	 */
	private void whenBallStopped() {
		if (isBallStopped()) {
			setStroke(Color.BLACK);
			gameStage.setBallPoints(playingField.calculatePoints(this));
		}
		if (isInAZone()) {
			setFill(Color.CORNFLOWERBLUE);
		}
	}

	/**
	 * isBallStopped()
	 * 
	 * @return a Boolean, set to true if the ball has stopped
	 */
	private boolean isBallStopped() {
		return isBallStopped;
	}

	/**
	 * isInAZone()
	 * 
	 * @returna a Boolean, set to true if the ball is in a zone
	 */
	public boolean isInAZone() {
		return isInAZone;
	}

	/**
	 * playBall()
	 * 
	 * Method calling other methods
	 */
	public void playBall() {
		moveBall();
		whenBallStopped();
	}

	/**
	 * animateBallMovement() based on
	 * http://www.cs.armstrong.edu/liang/intro10e/html/MultipleBounceBall.html
	 * 
	 * The animation of the balls movement
	 */
	public void animateBallMovement() {
		animation = new Timeline(
				new KeyFrame(Duration.millis(1000 / GameStage.FRAME_RATE), e -> playBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	/**
	 * inAZone(Zone zone)
	 * 
	 * Check if the ball stopped within a zone - if the distance between the
	 * centre coordinates of the ball and the centre coordinates of the zone is
	 * shorter than the length of the zone's radius the ball is within the zone.
	 * In other words, the centre of the ball must be inside the zone.
	 * 
	 * @param zone
	 *            - A Zone, a part of the playingfield where the player will get
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
