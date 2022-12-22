package com.spyatthehatch.objects;

import com.spyatthehatch.util.CoordinateUtils;

/**
 * Object to represent a 3-dimensional point in space.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Point3D extends Point {
   /**
    * Height of the Point3D object.
    */
   protected int height;
   
   /**
    * Constructor.
    * 
    * @param x X coordinate.
    * @param y Y coordinate.
    * @param height Height.
    */
   public Point3D (final int x, final int y, final int height){
      super(x, y);
      this.height = height;
   }
   
   /**
    * Get the height of this Node object.
    * 
    * @return the height.
    */
   public int getHeight() {
      return height;
   }
   
   /**
    * Get the Manhattan distance (X distance + Y distance + Height) between
    * this Point3D object and a given Point3D p.
    * 
    * @param p Point3D to compare.
    * @return Manhattan distance between this Point3D object and the given
    * Point3D p.
    * @see com.spyatthehatch.util.CoordinateUtils#getManhattanDistance(int,
    * int, int, int, int, int)
    */
   public int getManhattanDistance(final Point3D p){
      return CoordinateUtils.getManhattanDistance(this.x, this.y, this.height,
         p.x, p.y, p.height);
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.height;
      result = prime * result + this.x;
      result = prime * result + this.y;
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
      Point3D other = (Point3D) obj;
      if (this.height != other.height)
         return false;
      if (this.x != other.x)
         return false;
      if (this.y != other.y)
         return false;
      return true;
   }
}