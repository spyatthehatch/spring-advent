package com.spyatthehatch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;

/**
 * Unit tests for ListUtils.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class ListUtilsTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(ListUtilsTest.class);
	
	/**
	 * Superset list under test.
	 */
	private static List<String> superset = new ArrayList<String>();
	
	/**
	 * Subset list under test.
	 */
	private static List<String> subset = new ArrayList<String>();
	
	/**
	 * String under test.
	 */
	private static final String TEST_STRING = "TEST";
	
	/**
	 * Executed before any unit test starts.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.debug("Start ListUtilsTest suite.");
		
		final String s1 = "First String";
		final String s2 = "Second String";
		final String s3 = "Third String";
		
		superset.add(s1);
		superset.add(s2);
		superset.add(s3);
		superset.add(TEST_STRING);
		
		subset.add(s2);
	}
	
	/**
	 * Test with a list that contains no similar objects, expect false.
	 */
	@Test
	public void testContainsAnyDoesNotExistExpectFalse() {
		final List<String> list = new ArrayList<String>();
		list.add("Fourth String");
		list.add("Fifth String");
		assertFalse("FAIL: Did not receive false return as expected.", 
			ListUtils.containsAny(superset, list));
		
		LOGGER.trace("PASS: testContainsAnyDoesNotExistExpectFalse complete.");
	}
	
	/**
	 * Test with a list that does contain similar objects, expect true.
	 */
	@Test
	public void testContainsAnyDoesExistExpectTrue() {
		assertTrue("FAIL: Did not receive true return as expected.",
			ListUtils.containsAny(superset, subset));
		LOGGER.trace("PASS: testContainsAnyDoesExistExpectTrue complete.");
	}
	
	/**
	 * Test with a single object that is within the test list, expect true.
	 */
	@Test
	public void testContainsAnyWithSingleObjectExpectTrue() {
		assertTrue("FAIL: Did not receive true return as expected",
			ListUtils.containsAny(superset, TEST_STRING));
		LOGGER.trace("PASS: testContainsAnyWithSingleObjectExpectTrue complete.");
	}
	
	/**
	 * Test with a single object that is not within the list, expect false.
	 */
	@Test
	public void testContainsAnyWithSingleObjectExpectFalse() {
		assertFalse("FAIL: Did not receive false return as expected.",
			ListUtils.containsAny(superset, "FOO"));
		LOGGER.trace("PASS: testContainsAnyWithSingleObjectExpectFalse complete.");
	}
	
	/**
	 * Test that although that a different string (equals) is not in the
	 * list under test, it will return true as TEST_STRING.equals("TEST").
	 */
	@Test
	public void testContainsAnyWithNotEqualObjectExpectTrue() {
		assertTrue("FAIL: Did not receive true return as expected.",
			ListUtils.containsAny(superset, "TEST"));
		LOGGER.trace("PASS: testContainsAnyWithNotEqualObjectExpectFalse complete.");
	}
	
	/**
	 * Test that different multiple different, but equals objects will be found
	 * within the list under test.
	 */
	@Test
	public void testContainsAnyWithNotEqualObjectsFromListExpectTrue() {
		final List<String> list = new ArrayList<String>();
		list.add("First String");
		list.add("Second String");
		assertTrue("FAIL: Did not receive true return as expected.",
			ListUtils.containsAny(superset, list));
		LOGGER.trace("PASS: testContainsAnyWithNotEqualObjectsFromListExpectTrue complete.");
	}
	
	/**
	 * Executed after all unit tests have completed.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.debug("ListUtilsTest suite complete.");
	}
}