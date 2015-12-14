package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class RateDAL {


	public static double getRate(int GivenCreditScore) { //add the rest of the parameters and do all calculations in this method, use a button for user to calculate (run this method)
		//FinalExam - please implement		
		// Figure out which row makes sense- return back the 
		// right interest rate from the table based on the given credit score
		
		double result = 0;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel rateGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from RateDomainModel where minCreditScore = :minCreditScore");
			query.setParameter("minCreditScore", GivenCreditScore);
			
			List<?> list = query.list();
			rateGet = (RateDomainModel)list.get(0);
			result = rateGet.getInterestRate();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return result;
	}
	
	public static ArrayList<RateDomainModel> getRates() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel rateGets = null;		
		ArrayList<RateDomainModel> rtes = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List rates = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = rates.iterator(); iterator.hasNext();) {
				RateDomainModel rte = (RateDomainModel) iterator.next();
				rtes.add(rte);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rtes;
	}

}