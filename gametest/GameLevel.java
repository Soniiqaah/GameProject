package gametest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Soniiqaah
 *
 */

@Entity
@Table
public class GameLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int numOfTryOuts;
	private int points;

	public GameLevel() {

	}

	public GameLevel(int numOfTryOuts, int points) {
		this.numOfTryOuts = numOfTryOuts;
		this.points = points;
	}

	
	public int getNumOfTryOuts() {
		return numOfTryOuts;
	}

	public void setNumOfTryOuts() {

	}

	public int getPoints() {
		return points;
	}

	public void setPoints(String points) {
	}
}
