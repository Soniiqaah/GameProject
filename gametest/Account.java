package gametest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author Soniiqaah
 *
 */

@Entity
@NamedQuery (query = "Select user from Account user", name = "Show all users")

@Table
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	
	private String username;
	private String password;

	// Empty Constructor

	public Account() {

	}

	public Account(String user, String password) {
		this.username = username;
		this.password = password;

	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	}

}