package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2024, Day 4.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D4 extends AbstractSolution {
   
   char[][] grid;
	/**
	 * Constructor.
	 */
   public Solution_2024D4(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      int width = rawList.get(0).length();
      int height = rawList.size();
      grid = new char[width][height];
      
      for(int y=0; y < height; y++) {
         String s = rawList.get(y);
         for(int x=0; x < width; x++) {
            grid[x][y] = s.charAt(x);
         }
      }
      
      for(int y=0; y < height; y++) {
         for(int x=0; x < width; x++) {
            if(grid[x][y] == 'X') {
               // Check right.
               if(x+3 < width) {
                  if(grid[x+1][y] == 'M' && grid[x+2][y] == 'A' && grid[x+3][y] == 'S') {
                     total++;
                  }
               }
               
               // Check right down.
               if(x+3 < width && y+3 < height) {
                  if(grid[x+1][y+1] == 'M' && grid[x+2][y+2] == 'A' && grid[x+3][y+3] == 'S') {
                     total++;
                  }
               }
               
               // Check down.
               if(y+3 < height) {
                  if(grid[x][y+1] == 'M' && grid[x][y+2] == 'A' && grid[x][y+3] == 'S') {
                     total++;
                  }
               }
            
               // Check left down.
               if(x-3 >= 0 && y+3 < height) {
                  if(grid[x-1][y+1] == 'M' && grid[x-2][y+2] == 'A' && grid[x-3][y+3] == 'S') {
                     total++;
                  }
               }
            
               // Check left.
               if(x-3 >= 0) {
                  if(grid[x-1][y] == 'M' && grid[x-2][y] == 'A' && grid[x-3][y] == 'S') {
                     total++;
                  }
               }
            
               //Check left up.
               if(x-3 >= 0 && y-3 >= 0) {
                  if(grid[x-1][y-1] == 'M' && grid[x-2][y-2] == 'A' && grid[x-3][y-3] == 'S') {
                     total++;
                  }
               }
            
               // Check up.
               if(y-3 >= 0) {
                  if(grid[x][y-1] == 'M' && grid[x][y-2] == 'A' && grid[x][y-3] == 'S') {
                     total++;
                  }
               }
            
               // Check up right.
               if(x+3 < width && y-3 >= 0) {
                  if(grid[x+1][y-1] == 'M' && grid[x+2][y-2] == 'A' && grid[x+3][y-3] == 'S') {
                     total++;
                  }
               }
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
      int width = rawList.get(0).length();
      int height = rawList.size();
      grid = new char[width][height];
      
      for(int y=0; y < height; y++) {
         String s = rawList.get(y);
         for(int x=0; x < width; x++) {
            grid[x][y] = s.charAt(x);
         }
      }
      
      for(int y=0; y < height; y++) {
         for(int x=0; x < width; x++) {
            if(grid[x][y] == 'A' && x-1 >= 0 && y-1 >= 0 && x+1 < width && y+1 < height) {
               // Check M's on top.
               if(grid[x-1][y-1] == 'M' && grid[x+1][y-1] == 'M' && grid[x-1][y+1] == 'S' && grid[x+1][y+1] == 'S') {
                  total++;
               }
               
               // Check M's on right.
               if(grid[x-1][y-1] == 'S' && grid[x+1][y-1] == 'M' && grid[x-1][y+1] == 'S' && grid[x+1][y+1] == 'M') {
                  total++;
               }
               
               // Check M's on bottom.
               if(grid[x-1][y-1] == 'S' && grid[x+1][y-1] == 'S' && grid[x-1][y+1] == 'M' && grid[x+1][y+1] == 'M') {
                  total++;
               }
               
               // Check M's on left.
               if(grid[x-1][y-1] == 'M' && grid[x+1][y-1] == 'S' && grid[x-1][y+1] == 'M' && grid[x+1][y+1] == 'S') {
                  total++;
               }
            }
         }
      }

      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}