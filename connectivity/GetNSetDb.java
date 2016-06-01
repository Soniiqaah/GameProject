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
	 *  creating the GetNSetdb method with persistence
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
	public List<Result> getResults(){
		List<Result> allResults = em.createQuery("Select r from Result r").getResultList();
		return allResults;
	}
	public List<Result> getSortedResults(int level){
		List<Result> sortedResults = em.createQuery("Select r from Result r where r.gamelevel = :level order by points desc").setParameter("level",level).getResultList();
		return sortedResults;
	}
	public GameLevel getGameLevel(int level) {
	GameLevel gameLevel= (GameLevel) em.createQuery("Select g from GameLevel g where g.numOfTryOuts = :level").setParameter("level", level).getSingleResult();
	
	return gameLevel;

	}
	

	
	public void setResult(UserAccount user, int points, int level){
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
				
			em.close();
			emf.close();

		}
	}


