package com.cdac.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.model.User;

public class UserDao extends BaseHibernateDao {

	public List<User> listCourse() {
		return getList(User.class);
	}

	public void delete(User subject) {
		delete(subject);
	}

	public User getUserById(String usid) {
		Session session = getSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			Query hql = session.createQuery("from User u where u.userId=:uid ");
			hql.setParameter("uid", usid);
			List<User> result = hql.list();

			Iterator<User> iterator = result.iterator();
			while (iterator.hasNext()) {
				user = (User) iterator.next();
			}
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public List<User> getStudentByCourseId(String courseId) {
		Session session = getSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query hql = session.createQuery("from User u where u.userCourse=:cid and u.userRole=1 ");
			hql.setParameter("cid", courseId);
			users = hql.list();
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	public User authenticate(String uid, String upass) {
		Session session = getSession();
		Transaction tx = null;
		User user = null;
		// boolean flag=false;
		try {
			tx = session.beginTransaction();
			Query hql = session.createQuery("from User u where u.userId=:usid and u.userPass=:uspass ");
			hql.setParameter("usid", uid);
			hql.setParameter("uspass", upass);
			List<User> result = hql.list();

			Iterator<User> iterator = result.iterator();
			while (iterator.hasNext()) {
				user = (User) iterator.next();
			}
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public int updateUser(String uid, String uname, String uemail, String uphone) {
		Session session = getSession();
		Transaction tx = null;
		int result = 0;
		// boolean flag=false;
		try {
			tx = session.beginTransaction();
			String updt = "update User u set u.username=:usname,u.useremail=:usemail,u.userphone=:usphone where u.userid=:usid";
			Query hql = session.createQuery(updt);
			hql.setParameter("usid", uid);
			hql.setParameter("usname", uname);
			hql.setParameter("usemail", uemail);
			hql.setParameter("usphone", uphone);
			result = hql.executeUpdate();
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return result;

	}

}
