package com.spyatthehatch.solutions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 10.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D10 extends AbstractSolution {
   public final int WIDTH = rawList.get(0).length();
   public final int HEIGHT = rawList.size();
   public int[][] map = new int[WIDTH][HEIGHT];
   
	/**
	 * Constructor.
	 */
   public Solution_2024D10(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){            
      int total = 0;

      
      for(int y=0; y<HEIGHT; y++) {
         String s = rawList.get(y);
         
         for(int x=0; x<WIDTH; x++) {
            map[x][y] = Integer.valueOf(String.valueOf(s.charAt(x)));
         }
      }

      for(int y=0; y<HEIGHT; y++) {
         for(int x=0; x<WIDTH; x++) {
            if(map[x][y] == 0) {
               List<Point> points = new ArrayList<Point>();
               points = checkTrail(points, x, y);
               total += points.size();
            }
         }
      }

      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      
      for(int y=0; y<HEIGHT; y++) {
         String s = rawList.get(y);
         
         for(int x=0; x<WIDTH; x++) {
            map[x][y] = Integer.valueOf(String.valueOf(s.charAt(x)));
         }
      }

      for(int y=0; y<HEIGHT; y++) {
         for(int x=0; x<WIDTH; x++) {
            if(map[x][y] == 0) {
               total += rateTrail(x, y);
            }
         }
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public List<Point> checkTrail(List<Point> points, int x, int y) {      
      if(map[x][y] == 9) {
         Point p = new Point(x,y);
         if(!points.contains(p)) {
            points.add(p);
         }
         
         return points;
      }

      int target = map[x][y] + 1;
      
      if(x + 1 < WIDTH) {
         if(map[x+1][y] == target) {
            points = checkTrail(points, x+1, y);
         }
      }
      
      if (x - 1 >= 0) {
         if(map[x-1][y] == target) {
            points = checkTrail(points, x-1, y);
         }
      }
      
      if(y + 1 < HEIGHT) {
         if(map[x][y+1] == target) {
            points = checkTrail(points, x, y+1);
         }
      }
      
      if(y - 1 >= 0) {
         if(map[x][y-1] == target) {
            points = checkTrail(points, x, y-1);
         }
      }
      
      return points;
   }
   
   public int rateTrail(int x, int y) {      
      if(map[x][y] == 9) {         
         return 1;
      }

      int count = 0;
      int target = map[x][y] + 1;
      
      if(x + 1 < WIDTH) {
         if(map[x+1][y] == target) {
            count += rateTrail(x+1, y);
         }
      }
      
      if (x - 1 >= 0) {
         if(map[x-1][y] == target) {
            count += rateTrail(x-1, y);
         }
      }
      
      if(y + 1 < HEIGHT) {
         if(map[x][y+1] == target) {
            count += rateTrail(x, y+1);
         }
      }
      
      if(y - 1 >= 0) {
         if(map[x][y-1] == target) {
            count += rateTrail(x, y-1);
         }
      }
      
      return count;
   }
}