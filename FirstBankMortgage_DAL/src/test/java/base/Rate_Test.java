package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class Rate_Test{

	private double intrstRate = 0;
	
	@Test //test to get all of the rates and make sure the db is not empty
	public void testGetRates() {
		assertTrue(RateDAL.getRates().size() > 0);
	}
	
	@Test //test the db against a entered credit of 600
	public void test600(){
		intrstRate = RateDAL.getRate(600);
		assertTrue(intrstRate == 5);
	}
	
	@Test //test the db against a entered credit of 625, rounds down to the 600 min credit rate
	public void test625(){
		intrstRate = RateDAL.getRate(625);
		assertTrue(intrstRate == 5);
	}	

	@Test //test the db against a entered credit of 650
	public void test650(){
		intrstRate = RateDAL.getRate(650);
		assertTrue(intrstRate == 4.5);
	}
	
	@Test  //test the db against a entered credit of 675, rounds down to the 650 min credit rate
	public void test675(){
		intrstRate = RateDAL.getRate(675);
		assertTrue(intrstRate == 4.5);
	}
	
	@Test //test the db against a entered credit of 700
	public void test700(){
		intrstRate = RateDAL.getRate(700);
		assertTrue(intrstRate == 4);
	}
	
	@Test //test the db against a entered credit of 725, rounds down to the 700 min credit rate
	public void test725(){
		intrstRate = RateDAL.getRate(725);
		assertTrue(intrstRate == 4);
	}

	@Test //test the db against a entered credit of 750
	public void test750(){
		intrstRate = RateDAL.getRate(750);
		assertTrue(intrstRate == 3.75);
	}
	

	//test the db against a entered credit of 755, rounds down to the 750 min credit rate
	@Test
	public void test755(){
		intrstRate = RateDAL.getRate(755);
		assertTrue(intrstRate == 3.75);
	}	

	//test the db against a entered credit of 800
	@Test
	public void test800(){
		intrstRate = RateDAL.getRate(800);
		assertTrue(intrstRate == 3.5);
	}
	
	
}
