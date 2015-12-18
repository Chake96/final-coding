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
import util.HibernateUtil;

public class RateDAL {
	
	//this method returns the interestrate as a double, takes in a user's credit score and compares it against the database
	public static double getRate(int GivenCreditScore) {
		//FinalExam - please implement		
		// Figure out which row makes sense- return back the 
		// right interest rate from the table based on the given credit score
		
		//most efficient found way to round credit score to acceptable credit score
		if(GivenCreditScore % 50 != 0){
			GivenCreditScore = GivenCreditScore - (GivenCreditScore % 50);
		}
		
		double result = 0; //the final result from the database
		
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
			System.out.println(result);
			result= (result/100);
			System.out.println(result);
			
			
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
	
	
	//this method returns all the rates in the database, mostly used to make sure hibernate connection is connected and working properly
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