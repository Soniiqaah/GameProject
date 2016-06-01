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

@Table
public class GameLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int levelId;
	/**
	 * declare instance variables
	 */
	private int numOfTryOuts;

	public GameLevel() {

	}

	/**
	 * 
	 * @param numOfTryOuts
	 */
	public GameLevel(int numOfTryOuts) {
		this.numOfTryOuts = numOfTryOuts;

	}

	/**
	 * 
	 * @return get and set methods
	 */

	public int getNumOfTryOuts() {
		return numOfTryOuts;
	}

	public void setNumOfTryOuts(int numOfTryOuts) {
		this.numOfTryOuts = numOfTryOuts;
	}

}
