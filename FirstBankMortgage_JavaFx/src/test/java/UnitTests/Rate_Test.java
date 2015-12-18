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

}
