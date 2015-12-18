package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import base.RateDAL;
import ch.makery.address.model.Rate;

public class Rate_Test {

	double result = 0;
	
	@Test
	public void test() {
		Rate payment = new Rate(700, 300000);
		result = Math.floor(payment.getPayment(360));
		assertTrue(result == 1427);
	}

	
	@Test
	public void testHPV() {
		Rate payment = new Rate(700, 500000);
		result = Math.floor(payment.getPayment(360));
		assertTrue(result == 2379);
	}
	
	@Test
	public void testDRATE() {
		Rate payment = new Rate(600, 500000);
		result = Math.floor(payment.getPayment(360));
		assertTrue(result == 2672);
	}
	
	@Test
	public void testFull() {
		Rate payment = new Rate(600, 500000);
		result = payment.getPayment(360);
		assertEquals(result, 2672.97,.001);
	}
	

}
