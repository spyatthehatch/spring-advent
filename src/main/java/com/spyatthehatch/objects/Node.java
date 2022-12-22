package com.spyatthehatch.objects;

/**
 * Node object to represent 3-dimensional point in space with steps to track
 * shortest path.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Node extends Point3D {  
   /**
    * Shortest number of steps from start.
    */
   private int steps = Integer.MAX_VALUE;
   
   /**
    * Constructor.
    * 
    * @param x X coordinate.
    * @param y Y coordinate.
    * @param height Height.
    */
   public Node(final int x, final int y, final int height){
      super(x, y, height);
   }

   /**
    * Get the shortest number of steps to get to this Node object.
    * 
    * @return Steps taken to get to this Node object.
    */
   public int getSteps(){
      return this.steps;
   }
   
   /**
    * Set the number of steps taken to get to this Node object.
    * 
    * @param s Steps taken.
    */
   public void setSteps(int s){
      if(s < this.steps){
         this.steps = s;
      }
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
      Node other = (Node) obj;
      if (this.height != other.height)
         return false;
      if (this.x != other.x)
         return false;
      if (this.y != other.y)
         return false;
      return true;
   }
}