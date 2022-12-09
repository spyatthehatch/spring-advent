package com.spyatthehatch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import org.junit.*;

/**
 * Class of unit tests for BinaryUtils class.
 * @author Bill Everton
 * @version Advent 2021
 */
public class TriangularTableTest {
	/**
	 * FibonacciTable Object under test.
	 */
	private static TriangularTable table;
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(TriangularTableTest.class);
	/**
	 * Executed before any unit test starts.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.debug("Start TriangularTable Test suite.");
		table = new TriangularTable(2000);
	}
	
	@Test
	public void testIndex5Expect15(){
		assertEquals("FAIL: did not receive expected integer value",
				15, table.getValue(5));
			LOGGER.trace("PASS: testIndex5Expect15 complete.");
	}
	
	@Test
	public void testIndex9Expect45(){
		assertEquals("FAIL: did not receive expected integer value",
				45, table.getValue(9));
			LOGGER.trace("PASS: testIndex9Expect45 complete.");
	}
	
	@Test
	public void testIndex15Expect120(){
		assertEquals("FAIL: did not receive expected integer value",
				120, table.getValue(15));
			LOGGER.trace("PASS: testIndex15Expect120 complete.");
	}
	
	@Test
	public void testIndex30Expect465(){
		assertEquals("FAIL: did not receive expected integer value",
				465, table.getValue(30));
			LOGGER.trace("PASS: testIndex15Expect120 complete.");
	}
	
	/**
	 * Executed after all unit tests have completed.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.debug("TriangularTable Test suite complete.");
	}
}