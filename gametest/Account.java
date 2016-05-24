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
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	private String user;
	private String password;

	// Empty Constructor

	public Account() {

	}

	public Account(String user, String password) {
		this.user = user;
		this.password = password;

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	}

}