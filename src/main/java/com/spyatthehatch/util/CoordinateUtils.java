package com.spyatthehatch.util;

/**
 * Coordinate Utilities.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class CoordinateUtils {
	/**
	 * Get the Manhattan distance (X distance + Y distance) between two 2D
	 * points.
	 * 
	 * @param x1 First point X coordinate.
	 * @param y1 First point Y coordinate.
	 * @param x2 Second point X coordinate.
	 * @param y2 Second point Y coordinate.
	 * @return Manhattan distance between the two points.
	 */
	public static int getManhattanDistance(final int x1, final int y1, final
		int x2, final int y2){
		
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
	
	/**
	 * Get the Manhattan distance (X distance + Y distance + Z distance) between
	 * two 3D points.
	 * 
	 * @param x1 First point X coordinate.
	 * @param y1 First point Y coordinate.
	 * @param z1 First point Z coordinate.
	 * @param x2 Second point X coordinate.
	 * @param y2 Second point Y coordinate.
	 * @param z2 Second point Z coordinate.
	 * @return Manhattan distance between the two points.
	 */
	public static long getManhattanDistanace(final long x1, final long y1,
			final long z1, final long x2, final long y2, final long z2){
		
		return Math.abs(x2 - x1) + Math.abs(y2 - y1) + Math.abs(z2 - z1);
	}
	
	/**
	 * Get the Manhattan distance (X distance + Y distance + Z distance + T
	 * distance) between two 4D points.
	 * 
	 * @param x1 First point X coordinate.
	 * @param y1 First point Y coordinate.
	 * @param z1 First point Z coordinate.
	 * @param t1 First point T coordinate.
	 * @param x2 Second point X coordinate.
	 * @param y2 Second point Y coordinate.
	 * @param z2 Second point Z coordinate.
	 * @param t2 Second point T coordinate.
	 * @return Manhattan distance between the two points.
	 */
	public static int getManhattanDistance(final int x1, final int y1,
			final int z1, final int t1, final int x2, final int y2,
			final int z2, final int t2){
		
		return Math.abs(x2 - x1) + Math.abs(y2 - y1) + Math.abs(z2 - z1) +
			Math.abs(t2 - t1);
	}
	
	/**
	 * Get the reading order between two points.  Reading order prioritizes Y
	 * magnitude, such that if Y1 &lt; Y2, Point 1 is considered before Point 2,
	 * and vice versa.  Only when Y1 = Y2 is the X magnitude considered; at 
	 * which, X1 &lt; X2 Point 1 is considered before Point 2.  If X1 &gt;= X2,
	 * Point 1 is considered after Point 2.
	 * 
	 * @param x1 First point X coordinate.
	 * @param y1 First point Y coordinate.
	 * @param x2 Second point X coordinate.
	 * @param y2 Second point Y coordinate.
	 * @return Reading order between two points.  -1 if Point 1 is before
	 * Point 2.  Otherwise, return 1 (if Point 1 is after/even with Point 2).
	 */
	public static int readingOrder(final int x1, final int y1, final int x2,
		final int y2){
		
		final int BEFORE = -1;
		final int AFTER = 1;
		
		if(y1 < y2){
			return BEFORE;
		} else if(y1 > y2){
			return AFTER;
		} else {
			if(x1 < x2){
				return BEFORE;
			} else {
				return AFTER;
			}
		}
	}
}