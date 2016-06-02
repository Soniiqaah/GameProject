package connectivity;

import javax.persistence.Persistence;

import entity.UserAccount;
import entity.GameLevel;
import entity.Result;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 
 * @author Soniiqaah
 *
 */
public class GetNSetDb {
	private EntityManager em;

	/**
	 * creating the GetNSetdb method with persistence
	 */

	public GetNSetDb() {
		/**
		 * creating EntityManagerFactory
		 */
		em = Persistence.createEntityManagerFactory("Eclipselink_JPA").createEntityManager();
	}

	public List<UserAccount> getAccounts() {
		List<UserAccount> allAccounts = em.createQuery("Select a from UserAccount a").getResultList();
		return allAccounts;
	}

	public List<GameLevel> getGameLevels() {
		List<GameLevel> allGameLevels = em.createQuery("Select g from GameLevel g").getResultList();
		return allGameLevels;
	}

	public void setGameLevels(int[] numOfTryOutsArray) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (int numOfTryOuts : numOfTryOutsArray) {
			GameLevel gameLevel = new GameLevel(numOfTryOuts);
			em.persist(gameLevel);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public List<Result> getResults() {
		List<Result> allResults = em.createQuery("Select r from Result r").getResultList();
		return allResults;
	}

	public List<Result> getSortedResults(int level) {
		List<Result> sortedResults = em
				.createQuery("Select r from Result r where r.gamelevel.numOfTryOuts = :level order by r.points desc")
				.setParameter("level", level).setMaxResults(10).getResultList();
		return sortedResults;
	}
	
	public List<Result> getUnlimitedSortedResults(int level) {
		List<Result> sortedUnlimitedResults = em
				.createQuery("Select r from Result r where r.gamelevel.numOfTryOuts = :level order by r.points desc")
				.setParameter("level", level).getResultList();
		return sortedUnlimitedResults;
	}

	public List<Result> getSortedResultsOneUser(String username, int level) {
		System.out.println(" \n\n\n\n\n username sorted: "+ username);
		List<Result> sortedResultsOneUser = em
				.createQuery("Select r from Result r where r.gamelevel.numOfTryOuts = :level and r.account.username = :uname order by r.points desc")
				.setParameter("level", level).setParameter("uname", username).setMaxResults(10).getResultList();
		return sortedResultsOneUser;
	}

	public GameLevel getGameLevel(int level) {
		GameLevel gameLevel = (GameLevel) em.createQuery("Select g from GameLevel g where g.numOfTryOuts = :level")
				.setParameter("level", level).getSingleResult();
		return gameLevel;
	}

	public void setResult(UserAccount user, int points, int level) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager em = emf.createEntityManager();
		GameLevel gameLevel = getGameLevel(level);
		Result result = new Result();
		result.setAccount(user);
		result.setPoints(points);
		result.setGameLevel(gameLevel);
		em.getTransaction().begin();
		em.persist(result);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
