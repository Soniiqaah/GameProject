package connectivity;

import javax.persistence.Persistence;

import entity.Account;

import java.util.List;

import javax.persistence.EntityManager;

public class GetNSetDb {
	private EntityManager em;

	public GetNSetDb() {

		em = Persistence.createEntityManagerFactory("skeeballdb").createEntityManager();
	}

	public List<Account> getAccount() {
		List<Account> allAccounts = em.createQuery("Select a from Account a").getResultList();
		return allAccounts;
	}

public List<GameLevel> getGameLevel(){
	List<GameLevel> allGameLevels = em.createQuery("Select g from GameLevel g").getResultList();
	return allGameLevels;
	
}
}
