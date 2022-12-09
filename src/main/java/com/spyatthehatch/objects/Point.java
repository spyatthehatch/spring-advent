package com.spyatthehatch.objects;

import com.spyatthehatch.util.CoordinateUtils;

/**
 * Object to represent a single 2D point in space.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public final class Point {
   /**
    * X coordinate.
    */
	private int x;
	
	/**
	 * Y coordinate.
	 */
	private int y;
	
	/**
	 * Point object constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
   public Point(final int x, final int y){
		this.x = x;
		this.y = y;
	}
	
   /**
    * Get X coordinate.
    * 
    * @return X coordinate.
    */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Get Y coordinate.
	 * 
	 * @return Y coordinate.
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Get the Manhattan distance (X distance + Y distance) between this Point
	 * and a given Point p.
	 * 
	 * @param p Point to compare.
	 * @return Manhattan distance between this Point and the given Point p.
	 * @see com.spyatthehatch.util.CoordinateUtils#getManhattanDistance(int, int, int, int)
	 */
	public int getManhattanDistance(final Point p){
	   return CoordinateUtils.getManhattanDistance(this.x, this.y, p.x, p.y);
	}

	/**
	 * Checks if this Point is adjacent to a given Point, by +/- 1 coordinate in
	 * the X and/or Y axis.  Diagonal adjacency is included.
	 * 
	 * @param p Point to check adjacency.
	 * @return True, if adjacent.  False, otherwise.
	 */
   public boolean isAdjacent(final Point p){
      final int diffX = this.x - p.x;
      final int diffY = this.y - p.y;
      
      if (diffX >= -1 && diffX <= 1 && diffY >= -1 && diffY <= 1){
         return true;
      } else {
         return false;
      }
   }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
	   return "Point x:" + this.x + ", y:" + this.y;
	}
}