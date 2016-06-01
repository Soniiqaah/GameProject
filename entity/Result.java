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
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * declare instance variables
	 */
	private int resultId;

	private int points;

	@ManyToOne
	private UserAccount account;

	@ManyToOne
	private GameLevel gamelevel;

	public int getResultId() {
		return resultId;
	}

	public GameLevel getGameLevel() {
		return gamelevel;
	}

	public void setGameLevel(GameLevel gamelevel) {
		this.gamelevel = gamelevel;
	}

	public int getPoints() {
		return points;

	}

	public void setPoints(int points) {
		this.points = points;

	}

	public UserAccount getAccounts() {
		return account;

	}

	public void setAccount(UserAccount user) {
		this.account = user;

	}
}
