package graphicInterfaces;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.entities.Friend;
import com.entities.User;
import com.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class ManageUsers {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	public ManageUsers() {
		
	}
	
	public List<User> getListOfUsers() {
		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;
		List<User> result = null;
		try {
			tx = session.beginTransaction();
			result = session.createQuery("from User").list();

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public User getUser(long userId) {
		session = sessionFactory.getCurrentSession();
		org.hibernate.Transaction tx = null;
		User result = null;
		try {
			tx = session.beginTransaction();
			result = (User) session.get(User.class, userId);

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			// session.close();
		}
		return result;
	}

	public void setAsFriends(User user, User friend) {
		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Friend newFriend = new Friend(user, friend);
			session.save(newFriend);

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void setAsFriends(long userId, long friendId) {
		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = (User) session.load(User.class, userId);
			User friend = (User) session.load(User.class, friendId);

			Friend newFriend = new Friend(user, friend);

			session.save(newFriend);

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	public long addUser(String username, String password, String fname, String mName,
			String lName, String mNumber, String hpNumber, String address, String jDate) {

		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;
		long id = 0;

		try {
			tx = session.beginTransaction();

			User user = new User(username, password, fname, mName, lName, mNumber,
					hpNumber, address, jDate);
			id = (Long) session.save(user);

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}

		return id;

	}

	public void updateUser(User user) {

		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteUser(Long id) {
		session = sessionFactory.openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			User user = (User) session.get(User.class, id);
			session.delete(user);

			tx.commit();

		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		}

	}

}
