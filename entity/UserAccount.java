package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author Soniiqaah
 *
 */

@Entity
@NamedQueries({
@NamedQuery(query = "Select user from UserAccount user", name = "Show all users"),
@NamedQuery(query = "Select user from UserAccount user where user.username = :uname", name ="Checking if username is avaible"),

})
@Table
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	
	private String username;
	private String password;


	// Empty Constructor

	public UserAccount() {

	}
	/**
	 * 
	 * @param user
	 * @param password
	 */

	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;

	}
	/**
	 * 
	 * @return
	 */

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