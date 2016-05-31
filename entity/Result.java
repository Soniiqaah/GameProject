package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private int resultId;
	
	@ManyToOne
	private UserAccount account;

	@ManyToOne
	private GameLevel gamelevel;
	
	private int points;
	
	public int getResultId() {
		return resultId;
	}

	public GameLevel getGameLevel() {
		return gamelevel;
	}
	public void setGameLevel(GameLevel gamelevel) {
		this.gamelevel = gamelevel;
	}
}
