package graphicInterfacesServer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.Friend;
import com.entities.User;
import com.util.HibernateUtil;
import com.util.TransactionManager;

//am de modificat cu warning-urile de la transaction si cu dead code-ul


@SuppressWarnings("unchecked")
public class ManageUsers extends Thread {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	public ManageUsers() {

	}

	public List<User> getListOfUsers() {
		session = sessionFactory.openSession();
		TransactionManager transaction = null;
		List<User> listOfUsers = null;
		try {
			session = TransactionManager.getCurrentSession();
			
			listOfUsers = session.createQuery("from User").list();

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
		return listOfUsers;
	}

	public User getUser(long userId) {
		session = sessionFactory.getCurrentSession();
		TransactionManager transaction = null;
		User result = null;
		try {
			transaction = (TransactionManager) session.beginTransaction();
			result = (User) session.get(User.class, userId);

			transaction.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				transaction.rollback();
			he.printStackTrace();
		}
		return result;
	}

	public void setAsFriends(User user, User friend) {
		session = sessionFactory.openSession();
		TransactionManager transaction = null;
		try {
			transaction = (TransactionManager) session.beginTransaction();

			Friend newFriend = new Friend(user, friend);
			session.save(newFriend);

			transaction.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				transaction.rollback();
			he.printStackTrace();
		}
	}

	public void setAsFriends(long userId, long friendId) {
		session = sessionFactory.openSession();
		TransactionManager transaction = null;
		try {
			transaction = (TransactionManager) session.beginTransaction();

			User user = (User) session.load(User.class, userId);
			User friend = (User) session.load(User.class, friendId);

			Friend newFriend = new Friend(user, friend);

			session.save(newFriend);

			transaction.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				transaction.rollback();
			he.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public long addUser(User user) {

		session = sessionFactory.openSession();
		TransactionManager transaction = null;
		long id = 0;

		try {
			session = TransactionManager.getCurrentSession();

			id = (Long) session.save(user);

			TransactionManager.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				TransactionManager.rollback();
			he.printStackTrace();
		}

		return id;

	}

	@SuppressWarnings("unused")
	public long addUser(String username, String password, String firstName,
			String middleName, String lastName, String mobileNumber,
			String homePhoneNumber, String address, String joinDate) {

		session = sessionFactory.openSession();
		TransactionManager transaction = null;
		long id = 0;

		try {
			session = TransactionManager.getCurrentSession();

			User user = new User(username, password, firstName, middleName, lastName,
					mobileNumber, homePhoneNumber, address, joinDate);
			id = (Long) session.save(user);

			TransactionManager.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				TransactionManager.rollback();
			he.printStackTrace();
		}

		return id;

	}

	public void updateUser(User user) {

		session = sessionFactory.openSession();
		TransactionManager transaction = null;

		try {
			session = TransactionManager.getCurrentSession();
			session.update(user);
			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}

	}

	public void deleteUser(Long id) {
		session = sessionFactory.openSession();
		TransactionManager transaction = null;

		try {

			transaction = (TransactionManager) session.beginTransaction();

			User user = (User) session.get(User.class, id);
			session.delete(user);

			transaction.commit();

		} catch (HibernateException he) {
			if (transaction != null)
				transaction.rollback();
			he.printStackTrace();
		}
	}

}
