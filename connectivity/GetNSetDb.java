package connectivity;

import javax.persistence.Persistence;

import entity.UserAccount;
import entity.GameLevel;
import entity.Result;

import java.util.List;

import javax.persistence.EntityManager;

public class GetNSetDb {
	private EntityManager em;

	public GetNSetDb() {

		em = Persistence.createEntityManagerFactory("skeeballdb").createEntityManager();
	}

	public List<UserAccount> getAccounts() {
		List<UserAccount> allAccounts = em.createQuery("Select a from Account a").getResultList();
		return allAccounts;
	}

	public List<GameLevel> getGameLevels() {
		List<GameLevel> allGameLevels = em.createQuery("Select g from GameLevel g").getResultList();
		return allGameLevels;

	}
	public List<Result> getResults(){
		List<Result> allResults = em.createQuery("Select r from Result r").getResultList();
		return allResults;
	}
}

