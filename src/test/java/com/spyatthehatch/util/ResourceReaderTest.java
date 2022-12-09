package com.spyatthehatch.util;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests for ResourceReader.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public class ResourceReaderTest {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(ResourceReaderTest.class);
	
	/**
	 * ResourceReader under test.
	 */
	private static ResourceReader reader;
	
	/**
	 * Resource to read.
	 */
	private static final String RESOURCE = "resource.txt";
	
	/**
	 * List representation of resource file.
	 */
	private static List<String> rawList;
	
	/**
	 * Executed before any unit test.
	 */
	@BeforeClass
	public static void beforeClass() {
		LOGGER.debug("Start ResourceReaderTest suite.");
		reader = new ResourceReader(RESOURCE);
		rawList = reader.toList();
	}
	
	/**
	 * Check that there are 963 elements read.
	 */
	@Test
	public void testSizeExpect963Elements() {
		assertEquals("FAIL: did not receive expected size of 963 elements.",
			963, rawList.size());
		
		LOGGER.trace("PASS: testSizeExpect963Elements complete.");
	}
	
	/**
	 * Check that the sum of the numbers is 411.
	 */
	@Test
	public void testSumExpect411() {
		int sum = 0;
		for(String s : rawList) {
			sum += Integer.valueOf(s);
		}
		assertEquals("FAIL: did not sum to expected value of 411.", 411, sum);
		
		LOGGER.trace("PASS: testSumExpect411 complete.");
	}
	
	/**
	 * Executed after all unit tests have completed.
	 */
	@AfterClass
	public static void afterClass() {
		LOGGER.debug("ResourceReaderTest suite complete.");
	}
}