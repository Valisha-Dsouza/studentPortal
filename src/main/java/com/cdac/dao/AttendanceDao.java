package com.cdac.dao;

import com.cdac.model.Attendance;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AttendanceDao extends BaseHibernateDao {
	
	public List<Attendance> listAttendance(){
		return getList(Attendance.class);
	}
	
	public void delete(Attendance attendance){
		delete(attendance);
	}
	
	public List getAttendanceById( String userid )
	{
		 Session session = getSession();
			Transaction tx = null;
			List<Attendance> user=null;
			Criteria cr=null;
			try
			{
				tx = session.beginTransaction();
				Query hql = session.createQuery("from Attendance r where r.attendanceId.userId=:usid");
				hql.setParameter("usid", userid);
			// cr=session.createCriteria(Results.class);
			 
				// cr.add(Restrictions.eq("userid", userid));
				 //cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				 user=hql.list();
				 tx.commit();
			}
			catch (HibernateException ex) {
				if (tx != null)
					tx.rollback();
				ex.printStackTrace();
			} finally {
				session.close();
			}
			return user; 
	}
	public void insert(List<Attendance> attendance){
		for(Attendance attn:attendance){
			insert(attn);
		}
	}
	 
}
