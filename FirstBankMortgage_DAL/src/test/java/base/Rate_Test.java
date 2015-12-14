package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import domain.RateDomainModel;

public class Rate_Test {
	
	private static ArrayList<RateDomainModel> rtes;

	@Test
	public void test() {
		System.out.println(RateDAL.getRates().size());
		
		assertTrue(RateDAL.getRates().size() > 0);
	}
	
//	@Test
//	public void testGetRate(){
//		double intrstRate = 0;
//		intrstRate = RateDAL.getRate(600);
//		assertTrue(intrstRate == 5);
//	}

}
