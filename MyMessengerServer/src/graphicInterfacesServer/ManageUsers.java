package graphicInterfacesServer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.entities.Friend;
import com.entities.User;
import com.util.TransactionManager;

@SuppressWarnings("unchecked")
public class ManageUsers {

	private Session session;

	public User getUser(String username) {
		User result = null;
		try {
			session = TransactionManager.getCurrentSession();

			Query query = session.createQuery("from User u where u.username = :username")
					.setString("username", username);
			List<User> users = query.list();
			if (!users.isEmpty()) {
				result = users.get(0);
			}

			TransactionManager.commit();
		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
		return result;
	}

	/*
	 * Intoarce lista completa de useri
	 */
	public List<User> getListOfUsers() {
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

	public List<String> getFriendsOfUser(String user) {
		List<Friend> listOfUsers = new ArrayList<>();
		List<String> list = new ArrayList<>();

		try {
			session = TransactionManager.getCurrentSession();

			listOfUsers = session
					.createQuery("from Friend f where f.friendPK.user.username = :name")
					.setString("name", user).list();

			for (Friend user1 : listOfUsers)
				list.add(user1.getFriend().getUsername());

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
		return list;
	}

	/*
	 * Seteaza 2 useri ca fiind prieteni
	 */
	public void setAsFriends(String username, String friendUsername) {

		if (username != friendUsername) {
			try {
				session = TransactionManager.getCurrentSession();

				User user = (User) session
						.createQuery("from User o where o.username = :username")
						.setString("username", username).list().get(0);
				User friend = (User) session
						.createQuery("from User o where o.username = :username")
						.setString("username", friendUsername).list().get(0);

				Friend newFriend = new Friend(user, friend);
				session.save(newFriend);

				Friend newFriend1 = new Friend(friend, user);
				session.save(newFriend1);

				TransactionManager.commit();

			} catch (HibernateException he) {
				TransactionManager.rollback();
				System.out.println("EROARE HIBERNATE");
				he.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				System.out.println("NU A FOST ADAUGAT LA PRIETENI");
			}
		}
	}

	/*
	 * Adauga un user nou in baza de date
	 */
	public long addUser(User user) {

		long id = 0;
		try {
			session = TransactionManager.getCurrentSession();

			id = (Long) session.save(user);

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}

		return id;

	}

	/*
	 * Modifica datele unui user
	 */
	public void updateUser(User user) {

		try {
			session = TransactionManager.getCurrentSession();
			session.update(user);
			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}

	}

	/*
	 * Sterge un user din baza de date
	 */

	public void deleteUser(Long id) {
		try {
			session = TransactionManager.getCurrentSession();

			User user = (User) session.get(User.class, id);

			session.delete(user);

			TransactionManager.commit();

		} catch (HibernateException he) {
			TransactionManager.rollback();
			he.printStackTrace();
		}
	}

}
