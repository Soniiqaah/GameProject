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
 * Creating Named Queries
 */

@Entity
@NamedQueries({
@NamedQuery(query = "Select user from UserAccount user", name = "ShowAllUsers"),
@NamedQuery(query = "Select user from UserAccount user where user.username = :uname", name ="CheckingIfUsernameIsAvailable"),

})

public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	private String username;
	private String userpw;


	/**
	 * Empty constructor
	 */

	public UserAccount() {

	}
	/**
	 * 
	 * @param username
	 * @param userpw
	 */

	public UserAccount(String username, String userpw) {
		this.username = username;
		this.userpw = userpw;

	}
	/**
	 * 
	 * @return Getters and Setters
	 */

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPassword() {
		return userpw;
	}

	public void setPassword(String userpw) {
		this.userpw = userpw;
	}

}