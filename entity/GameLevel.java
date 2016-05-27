package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Soniiqaah
 *
 */

@Entity
//@NamedQuery(query = "", name = "")
@Table
public class GameLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int levelid;
	/**
	 * instance variables
	 */

	private int numOfTryOuts;
	private int points;
	
	@ManyToOne
	private GameLevel gamelevel;

	public GameLevel() {

	}
/**
 * 
 * @param numOfTryOuts
 */
	public GameLevel(int numOfTryOuts, int points) {
		this.numOfTryOuts = numOfTryOuts;
		this.points = points;
	
	}
	
/**
 * 
 * @return
 */

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
