package com.cdac.dao;

//import com.dao.HibernateUtil;
import com.cdac.model.Results;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ResultsDao extends BaseHibernateDao {
	
	
	public void insert(List<Results> results){
		for(Results result:results){
			insert(result);
		}
	}
	
	
	public List<Results> listCourse(){
		return getList(Results.class);
	}
	
	public void delete(Results results){
		delete(results);
	}
	
	public int updateResult(String uid,String sid,int lab,int theory)
	{
		Session session = getSession();
		Transaction tx = null;
		int result=0;
		// boolean flag=false;
		try {
			tx = session.beginTransaction();
			String updt="update Results r  set r.labmarks=:labm,r.theorymarks=:theorym where r.userid=:usid and r.subjectid=:usubid";
			Query hql = session.createQuery(updt);
			hql.setParameter("usid", uid);
			hql.setParameter("usubid", sid);
			hql.setParameter("labm", lab);
			hql.setParameter("theorym",theory);
			result=hql.executeUpdate();
			tx.commit();
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return result;

	} 
	
}