package com.spyatthehatch.solutions;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2023, Day 10.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D10 extends AbstractSolution {
   private char[][] maze;
   private int height;
   private int width;
   private Point origin;
   
	/**
	 * Constructor.
	 */
   public Solution_2023D10(){
      super();
      this.height = rawList.size();
      this.width = rawList.get(0).length();
      this.maze = new char[width][height];
      
      for(int y=0; y < height; y++) {
         String line = rawList.get(y);
         for(int x=0; x < width; x++) {
            this.maze[x][y] = line.charAt(x);
            if(this.maze[x][y] == 'S') {
               this.origin = new Point(x,y);
               LOGGER.info("Located origin at x:" + x + ", y:" + y);
            }
         }
      }
   }

   /**
    * 
    */
   public String solvePartOne(){
      int steps = 0;

      Point history = origin;
      Point here = origin;
      Point next = findNextPoint(here, history);
      
      while(!next.equals(origin)) {
         history = here;
         here = next;
         next = findNextPoint(here, history);
         steps++;
      }
      
      LOGGER.info("Total steps taken:" + steps);
      int farthest = (steps/2) + (steps % 2);
      this.partOneAnswer = String.valueOf(farthest);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;

      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public Point findNextPoint(final Point here, final Point history) {
      char tile = this.maze[here.getX()][here.getY()];
      Point next = null;
      char nextTile;
      
      switch(tile) {
         case '|':
            next = new Point(here.getX(), here.getY() - 1);
            if(history.equals(next)) {
               return new Point(here.getX(), here.getY() + 1);
            } else {
               return next;
            }
         case '-':
            next = new Point(here.getX() - 1, here.getY());
            if(history.equals(next)) {
               return new Point(here.getX() + 1, here.getY());
            } else {
               return next;
            }
         case 'L':
            next = new Point(here.getX(), here.getY() - 1);
            if(history.equals(next)) {
               return new Point(here.getX() + 1, here.getY());
            } else {
               return next;
            }
         case 'J':
            next = new Point(here.getX(), here.getY() - 1);
            if(history.equals(next)) {
               return new Point(here.getX() - 1, here.getY());
            } else {
               return next;
            }
         case '7':
            next = new Point(here.getX() - 1, here.getY());
            if(history.equals(next)) {
               return new Point(here.getX(), here.getY() + 1);
            } else {
               return next;
            }
         case 'F':
            next = new Point(here.getX() + 1, here.getY());
            if(history.equals(next)) {
               return new Point(here.getX(), here.getY() + 1);
            } else {
               return next;
            }
         case 'S':
            if(here.getX() - 1 >= 0) {
               next = new Point(here.getX() - 1, here.getY());
               nextTile = this.maze[next.getX()][next.getY()];
               if(nextTile != '.') {
                  return next;
               }
            }
            
            if(here.getX() + 1 < this.width) {
               next = new Point(here.getX() + 1, here.getY());
               nextTile = this.maze[next.getX()][next.getY()];
               if(nextTile != '.') {
                  return next;
               }
            }
            
            if(here.getY() - 1 >= 0) {
               next = new Point(here.getX(), here.getY() - 1);
               nextTile = this.maze[next.getX()][next.getY()];
               if(nextTile != '.') {
                  return next;
               }
            }
            
            if(here.getY() + 1 < this.height) {
               next = new Point(here.getX(), here.getY() + 1);
               nextTile = this.maze[next.getX()][next.getY()];
               if(nextTile != '.') {
                  return next;
               }
            }
            
            LOGGER.warn("Unable to leave the origin.");
         case '.':
         default:
            LOGGER.warn("Unexpected tile received at " + here.toString());
            break;
      }
      
      return null;
   }
}