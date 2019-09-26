package crud;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import model.JePrijatelj;
import model.Korisnik;
import model.Status;
import utils.PersistenceUtil;

public class Crud {

	public static boolean addUser(Korisnik k) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			em.persist(k);
			em.flush();
			
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean usernameExists(String username){
		EntityManager em = PersistenceUtil.getEntityManager();
		String hql = "select korisnickoIme from Korisnik k";
		List<String> lista = castList(String.class, em.createQuery(hql).getResultList());
		if(lista.contains(username))
			return true;
		return false;
	}
	
	public static boolean emailExists(String email){
		EntityManager em = PersistenceUtil.getEntityManager();
		String hql = "select email from Korisnik k";
		List<String> lista = castList(String.class, em.createQuery(hql).getResultList());
		if(lista.contains(email))
			return true;
		return false;
	}
	
	public static Korisnik getKorisnik(String username) {
		EntityManager em = PersistenceUtil.getEntityManager();
		String hql = "select k from Korisnik k where korisnickoIme=:ime";
		Query q = em.createQuery(hql);
		q.setParameter("ime", username);
		List<Korisnik> lista = castList(Korisnik.class, q.getResultList());
		if(lista.size() == 0)
			return null;
		return lista.get(0);
	}
	
	public static boolean updateUsername(String oldUsername, String newUsername) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			Korisnik k = em.find(Korisnik.class, oldUsername);
			if(!usernameExists(newUsername)) {
				k.setKorisnickoIme(newUsername);
				em.merge(k);
				em.flush();
				et.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean updateName(String username, String newName) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			Korisnik k = em.find(Korisnik.class, username);
		
			k.setPunoIme(newName);
			em.merge(k);
			em.flush();
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean updateSurname(String username, String newSurname) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			Korisnik k = em.find(Korisnik.class, username);
		
			k.setPunoPrezime(newSurname);
			em.merge(k);
			em.flush();
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean updateEmail(String username, String email) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
		
			Korisnik k = em.find(Korisnik.class, username);
			if(!emailExists(email)) {
				k.setEmail(email);
				em.merge(k);
				em.flush();
				et.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean updatePassword(String username, String oldPass, String newPass) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
		
			Korisnik k = em.find(Korisnik.class, username);
			if(k.getPassword().equals(oldPass)) {
				k.setPassword(newPass);
				em.merge(k);
				em.flush();
				et.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static void updateImagePath(Korisnik user, String path) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
		
			user = em.merge(user);
			user.setPutanjaSlike(path);
			em.merge(user);
			em.flush();
			et.commit();
		} catch (Exception e) {
			if(et != null)
				et.rollback();
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static List<Korisnik> getFriends(Korisnik k){ // with all statuses
		EntityManager em = PersistenceUtil.getEntityManager();
		k = em.merge(k);
		String hql1 = "select korisnik1 from JePrijatelj jp where jp.korisnik2=:k1";
		Query query = em.createQuery(hql1);
		query.setParameter("k1", k);
		List<Korisnik> users1 = castList(Korisnik.class, query.getResultList());
		String hql2 = "select korisnik2 from JePrijatelj jp where jp.korisnik1=:k2";
		Query query1 = em.createQuery(hql2);
		query1.setParameter("k2", k);
		List<Korisnik> users2 = castList(Korisnik.class, query1.getResultList());
		
		List<Korisnik> allFriends = Stream.concat(users1.stream(), users2.stream()).collect(Collectors.toList());
		
		em.close();
		return allFriends;
	}
	
	public static List<Korisnik> getRequests(Korisnik k){
		EntityManager em = PersistenceUtil.getEntityManager();
		try {
			k = em.merge(k);
			String hql1 = "select korisnik1 from JePrijatelj jp where jp.korisnik2=:k1 and status=:st";
			Query query = em.createQuery(hql1);
			query.setParameter("k1", k); query.setParameter("st", "pending");
			List<Korisnik> users = castList(Korisnik.class, query.getResultList());
			return users;
		} catch (Exception e) {
			return new ArrayList<Korisnik>();
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean acceptFriend(Korisnik k1, Korisnik k2){
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			k1 = em.merge(k1);
			k2 = em.merge(k2);
			String hql1 = "select jp from JePrijatelj jp where jp.korisnik1=:k1 and jp.korisnik2=:k2 and status=:st";
			Query query = em.createQuery(hql1);
			query.setParameter("k1", k2); 
			query.setParameter("k2", k1); 
			query.setParameter("st", "pending");
			JePrijatelj jp = (JePrijatelj) query.getSingleResult();
			jp.setStatus("accepted");
			em.merge(jp);
			em.flush();
			
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
		
	}
	
	public static List<Korisnik> getFriends1(Korisnik k){ // friends are with "accepted" status
		EntityManager em = PersistenceUtil.getEntityManager();
		//k = em.merge(k);
		String hql1 = "select korisnik1 from JePrijatelj jp where jp.korisnik2=:k1 and status=:st";
		Query query = em.createQuery(hql1);
		query.setParameter("k1", k); query.setParameter("st", "accepted");
		List<Korisnik> users1 = castList(Korisnik.class, query.getResultList());
		String hql2 = "select korisnik2 from JePrijatelj jp where jp.korisnik1=:k2 and status=:st";
		Query query1 = em.createQuery(hql2);
		query1.setParameter("k2", k);  query1.setParameter("st", "accepted");
		List<Korisnik> users2 = castList(Korisnik.class, query1.getResultList());
		
		List<Korisnik> allFriends = Stream.concat(users1.stream(), users2.stream()).collect(Collectors.toList());
		
		em.close();
		return allFriends;
	}
	
	public static List<Korisnik> getUsersNotFriends(Korisnik k){
		EntityManager em = PersistenceUtil.getEntityManager();

		//k = em.merge(k);
		String hql1 = "select k from Korisnik k";
		Query query = em.createQuery(hql1);
		List<Korisnik> allUsers = castList(Korisnik.class, query.getResultList());
		List<Korisnik> userFriends = getFriends(k);
		allUsers.remove(k);
		allUsers.removeAll(userFriends);
		
		em.close();
		return allUsers;
	}
	
	public static boolean addFriend(Korisnik from, Korisnik to) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			from = em.merge(from);
			to = em.merge(to);
			
			JePrijatelj jp = new JePrijatelj();
			jp.setKorisnik1(from);
			jp.setKorisnik2(to);
			jp.setStatus("pending");
			em.persist(jp);
			
			from.addJePrijateljs1(jp);
			em.merge(from);
			to.addJePrijateljs2(jp);
			em.merge(to);
			
			em.flush();
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null) {
				et.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public static boolean deleteFriend(Korisnik user1, Korisnik user2) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			Korisnik user1_2 = em.find(Korisnik.class, user1.getKorisnikID());
			Korisnik user2_2 = em.find(Korisnik.class, user2.getKorisnikID());
			
			try {
				String hql1 = "select jp from JePrijatelj jp where jp.korisnik1=:k1 and jp.korisnik2=:k2";
				Query query1 = em.createQuery(hql1);
				query1.setParameter("k1", user1_2); query1.setParameter("k2", user2_2);
				JePrijatelj jp1 = (JePrijatelj) query1.getSingleResult();
				
				user1.getJePrijateljs1().remove(jp1);
				user2.getJePrijateljs2().remove(jp1);
				em.remove(jp1);
			} catch (Exception e) {
				String hql2 = "select jp from JePrijatelj jp where jp.korisnik1=:k1 and jp.korisnik2=:k2";
				Query query2 = em.createQuery(hql2);
				query2.setParameter("k1", user2_2); query2.setParameter("k2", user1_2);
				JePrijatelj jp2 = (JePrijatelj) query2.getSingleResult();
				user1.getJePrijateljs1().remove(jp2);
				user2.getJePrijateljs2().remove(jp2);
				em.remove(jp2);
			}
			
			em.flush();
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null) {
				e.printStackTrace();
				et.rollback();
			}
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static boolean addStatus(String text, Korisnik k) {
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			
			k = em.merge(k);
			Status s = new Status();
			s.setDate(Calendar.getInstance());
			s.setKorisnik(k);
			s.setStatus(text);
			em.persist(s);
			em.flush();
		
			et.commit();
			return true;
		} catch (Exception e) {
			if(et != null)
				et.rollback();
			return false;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	public static List<Status> getStatuses(){
		EntityManager em = PersistenceUtil.getEntityManager();
		String hql = "select s from Status s order by id desc";
		Query q = em.createQuery(hql);
		q.setMaxResults(5);
		List<Status> statuses = castList(Status.class, q.getResultList());
		
		em.close();
		return statuses;
	}
	
	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
