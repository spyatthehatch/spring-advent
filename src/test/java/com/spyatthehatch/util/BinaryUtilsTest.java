package com.spyatthehatch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 * Class of unit tests for BinaryUtils class.
 * @author Bill Everton
 * @version Advent 2021
 */
public class BinaryUtilsTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(BinaryUtilsTest.class);
	
	/**
	 * Executed before any unit test starts.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.debug("Start BinaryUtilsTest suite.");
	}
	
	/**
	 * Test 10-bit conversion.
	 */
	@Test
	public void test10bitBinaryArrayToInt(){
		int[] input = {1, 0, 1, 0, 0, 0, 1, 1, 0, 1};
		assertEquals("FAIL: did not receive expected integer value",
			653, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: test10bitBinaryArrayToInt complete.");
	}
	
	/**
	 * Test 12-bit conversion.
	 */
	@Test
	public void test12bitBinaryArrayToInt(){
		int[] input = {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1};
		assertEquals("FAIL: did not receive expected integer value",
			2615, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: test12bitBinaryArrayToInt complete.");
	}
	
	/**
	 * Test 8-bit conversion.
	 */
	@Test
	public void test8bitBinaryArrayToInt(){
		int[] input = {1, 0, 1, 1, 0, 0, 1, 1};
		assertEquals("FAIL: did not receive expected integer value",
			179, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: test8bitBinaryArrayToInt complete.");
	}
	
	/**
	 * Test 1 zero conversion.
	 */
	@Test
	public void test1zeroBinaryArrayToInt(){
		int[] input = {0};
		assertEquals("FAIL: did not receive expected integer value",
			0, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: test1zeroBinaryArrayToInt complete.");
	}
	
	/**
	 * Test 1 one conversion.
	 */
	@Test
	public void test1oneBinaryArrayToInt(){
		int[] input = {1};
		assertEquals("FAIL: did not receive expected integer value",
			1, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: test1oneBinaryArrayToInt complete.");
	}
	
	/**
	 * Test many leading zeros.
	 */
	@Test
	public void testLeadingZerosBinaryArrayToInt(){
		int[] input = {0, 0, 0, 0, 0, 0, 0, 0, 1};
		assertEquals("FAIL: did not receive expected integer value",
			1, BinaryUtils.binaryArrayToInt(input));
		LOGGER.trace("PASS: testLeadingZerosBinaryArrayToInt complete.");
	}
	
	@Test(expected = RuntimeException.class)
	public void testIllegalCharacterThrowsException(){
	   int [] input = {2, 0, 0, 0};
	   final int result = BinaryUtils.binaryArrayToInt(input);
	   LOGGER.trace("FAIL: testIllegalCharacterThrowsExcception returned result: " + result);
	   fail();
	}
	
	/**
	 * Executed after all unit tests have completed.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.debug("BinaryUtilsTest suite complete.");
	}
}