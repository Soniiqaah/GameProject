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

	public GetNSetDb() {

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

	
	public List<Result> setResult(UserAccount user, levelId){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink_JPA");
			EntityManager em = emf.createEntityManager();
				
			Result result = new Result();
				result.getResultId();
				user.setPassword(password);

				em.getTransaction().begin();
				em.persist(user);

				em.getTransaction().commit();
				em.close();
				emf.close();
				return true;
			em.close();
			emf.close();
			return false;

		}
		
		
	


