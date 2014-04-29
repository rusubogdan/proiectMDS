package graphicInterfacesServer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Friend;
import com.util.HibernateUtil;
import com.util.TransactionManager;

public class ManageFriends extends Thread {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	public ManageFriends() {

	}

	@SuppressWarnings("unchecked")
	public List<Friend> getListOfUsers() {
		session = sessionFactory.openSession();
		List<Friend> listOfFriends = null;
		try {
			session = TransactionManager.getCurrentSession();

			listOfFriends = session.createQuery("from Friends").list();

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
		return listOfFriends;
	}
	
	public Friend getFriend(long userId) {
		session = sessionFactory.getCurrentSession();
		Friend result = null;
		try {
			session = TransactionManager.getCurrentSession();

			result = (Friend) session.get(Friend.class, userId);

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
		return result;
	}
	

}
