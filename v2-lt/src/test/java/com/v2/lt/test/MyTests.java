package com.v2.lt.test;

import org.junit.Assert;
import org.junit.Test;

import com.v2.lt.test.dummyclasses.DummyCaculator;

import static org.junit.Assert.assertEquals;

public class MyTests {
	@SuppressWarnings("deprecation")
	@Test
	  public void multiplicationOfZeroIntegersShouldReturnZero() {

	    // MyClass is tested
		DummyCaculator tester = new DummyCaculator();

	    // assert statements
	  //  Assert.assertEquals("10 x 0 must be 0", 0, tester.multiply(10.5, 0.0));
	    assertEquals("0 x 10 must be 0", 0.0, tester.multiply(0.0, 10.32).doubleValue(), 0);
	    assertEquals("0 x 0 must be 0", 0.0, tester.multiply(0.0, 0.0).doubleValue(), 0);
	  }

}
