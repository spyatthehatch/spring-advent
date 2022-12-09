package com.spyatthehatch.solutions;

import java.util.HashSet;
import java.util.Set;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2022, Day 9.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D9 extends AbstractSolution {  
   public Solution_2022D9(){
      super();
   }
   
   public String solvePartOne(){
      this.partOneAnswer = String.valueOf(pullRope(2));
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      this.partTwoAnswer = String.valueOf(pullRope(10));
      return this.partTwoAnswer;
   }
   
   /**
    * Run the simulation of pulling the rope through the commands in the puzzle
    * input.  Get the number of unique points the tail has visited.
    * 
    * @param count Number of knots in the rope, to include the head and tail.
    * @return Number of unique points the tail has visited.
    */
   private int pullRope(final int count){
      final Set<Point>points = new HashSet<Point>();
      final Point[] knots = new Point[count];
      
      for(int i=0; i < knots.length; i++){
         knots[i] = new Point(0,0);
      }
      
      for(final String s : this.rawList){
         final String[] velocity = s.split(" ");
         final String dir = velocity[0];
         final int mag = Integer.valueOf(velocity[1]);
         int x = 0;
         int y = 0;
         
         if(dir.equals("U")){
            y = 1;
         } else if (dir.equals("D")){
            y = -1;
         } else if (dir.equals("L")){
            x = -1;
         } else {
            x = 1;
         }
         
         for(int i=0; i < mag; i++){
            knots[0] = new Point(knots[0].getX() + x, knots[0].getY() + y);
            
            for(int thisKnot=1; thisKnot < knots.length; thisKnot++){            
               if(!knots[thisKnot].isAdjacent(knots[thisKnot - 1])){
                  knots[thisKnot] = nextMove(knots[thisKnot - 1], knots[thisKnot]);        
               }
            }
            points.add(knots[count - 1]);
         }
      }

      return points.size();
   }
   
   /**
    * Determine the next move that the tail should make, given where the tail
    * and head currently are.  It is assumed that the points are not adjacent,
    * and are at most +2 spaces away.  Next move can be diagonal.
    * 
    * @param head Point where the head is.
    * @param tail Point where the tail is.
    * @return Next Point the tail should go.
    */
   private Point nextMove(final Point head, final Point tail){
      final int diffX = head.getX() - tail.getX();
      final int diffY = head.getY() - tail.getY();
      
      int x = 0;
      int y = 0;
      
      if(diffX > 0){
         x = 1;
      }
      
      if(diffX < 0){
         x = -1;
      }
      
      if (diffY > 0){
         y = 1;
      }
      
      if (diffY < 0){
         y = -1;
      }
      
      return new Point(tail.getX() + x, tail.getY() + y);
   }
}