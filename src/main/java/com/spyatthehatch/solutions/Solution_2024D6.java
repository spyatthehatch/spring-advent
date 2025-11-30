package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2024, Day 6.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D6 extends AbstractSolution {
   public char[][] grid;
   public enum Direction {UP, RIGHT, DOWN, LEFT}
   
	/**
	 * Constructor.
	 */
   public Solution_2024D6(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      int width = rawList.get(0).length();
      int height = rawList.size();
      this.grid = new char[width][height];
      List<Point>points = new ArrayList<Point>();
      Point current = null;
      Direction direction = Direction.UP;
            
      for(int y=0; y < height; y++) {
         String line = rawList.get(y);
         
         for(int x=0; x < width; x++) {
            char c = line.charAt(x);
            grid[x][y] = c;
            
            if(c == '^') {
               current = new Point(x, y);
               points.add(current);
            }
         }
      }

      while(current.getX() < width && current.getY() < height) {
         int x = current.getX();
         int y = current.getY();
         
         if(direction == Direction.UP) {
            if(y - 1 >= 0) {
               if(grid[x][y-1] == '#') {
                  direction = rotate(direction);
               }
               
               else {
                  current = new Point(x, y-1);
                  if(!points.contains(current)) {
                     points.add(current);
                  }
               }
            }
            
            else {
               break;
            }
         }
         
         else if(direction == Direction.RIGHT) {
            if(x + 1 < width) {
               if(grid[x+1][y] == '#') {
                  direction = rotate(direction);
               }
               
               else {
                  current = new Point(x+1, y);
                  if(!points.contains(current)) {
                     points.add(current);
                  }
               }
            }
            
            else {
               break;
            }
         }
         
         else if(direction == Direction.DOWN) {
            if(y + 1 < height) {
               if(grid[x][y+1] == '#') {
                  direction = rotate(direction);
               }
               
               else {
                  current = new Point(x, y+1);
                  if(!points.contains(current)) {
                     points.add(current);
                  }
               }
            }
            
            else {
               break;
            }
         }
         
         else if(direction == Direction.LEFT) {
            if(x - 1 >= 0) {
               if(grid[x-1][y] == '#') {
                  direction = rotate(direction);
               }
               
               else {
                  current = new Point(x-1, y);
                  if(!points.contains(current)) {
                     points.add(current);
                  }
               }
            }
            
            else {
               break;
            }
         }
         
      }
      
      this.partOneAnswer = String.valueOf(points.size());
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      int width = rawList.get(0).length();
      int height = rawList.size();
      this.grid = new char[width][height];
      List<Point>loops = new ArrayList<Point>();
      Bearing start = null;
            
      for(int y=0; y < height; y++) {
         String line = rawList.get(y);
         
         for(int x=0; x < width; x++) {
            char c = line.charAt(x);
            grid[x][y] = c;
            
            if(c == '^') {
               LOGGER.trace("Starting point found at " + x + "," + y + ".");
               start = new Bearing(x, y, Direction.UP);
            }
         }
      }
      
      for(int j=0; j < height; j++) {
         LOGGER.trace("Checking row:" + j + ", current loop count:" + loops.size());
         
         for(int i=0; i < width; i++) {
//            if(grid[i][j] == '#' || grid[i][j] == '^') {
            if(grid[i][j] != '.') {
//               LOGGER.trace("Skipping:" + i +"," + j + "char:" + grid[i][j]);
               continue;
            }
            
            else {
               char[][] board = copyGrid(this.grid);
               board[i][j] = '#';
               Bearing current = start;
               boolean loop = false;
               List<Bearing>history = new ArrayList<Bearing>();
//               int steps = -1;

               while(current.p.getX() < width && current.p.getY() < height && !loop) {
                  int x = current.p.getX();
                  int y = current.p.getY();
                  Direction direction = current.direction;
//                  steps++;
                  
//                  if(steps > 67600) {
//                     LOGGER.trace("Overrun loop detected.");
//                     loops.add(new Point(i,j));
//                     loop = true;
//                  }
//                  LOGGER.trace("Position:" + x + "," + y + "," + direction + ".");
//                  LOGGER.trace("History size:" + history.size());
                  
                  if(direction == Direction.UP) {
                     if(y - 1 >= 0) {
                        if(board[x][y-1] == '#') {
//                           LOGGER.trace("Rotating.");
                           Direction dir = rotate(direction);
                           current = new Bearing(x, y, dir);
                        }
                        
                        else {
                           current = new Bearing(x, y-1, direction);
//                           LOGGER.trace("Creating a new bearing.");
                        }
                           
                        if(!history.contains(current)) {
                           history.add(current);
                        } else {
                           loops.add(new Point(i,j));
                           loop = true;
                        }
                       
                     }
                     
                     else {
                        break;
                     }
                  }
                  
                  else if(direction == Direction.RIGHT) {
                     if(x + 1 < width) {
                        if(board[x+1][y] == '#') {
                           Direction dir = rotate(direction);
                           current = new Bearing(x, y, dir);
                        }
                        
                        else {
                           current = new Bearing(x+1, y, direction);
                        }
                           
                        if(!history.contains(current)) {
                           history.add(current);
                        } else {
                           loops.add(new Point(i,j));
                           loop = true;
                        }
                        
                     }
                     
                     else {
                        break;
                     }
                  }
                  
                  else if(direction == Direction.DOWN) {
                     if(y + 1 < height) {
                        if(board[x][y+1] == '#') {
                           Direction dir = rotate(direction);
                           current = new Bearing(x, y, dir);
                        }
                        
                        else {
                           current = new Bearing(x, y+1, direction);
                        }
                        
                        if(!history.contains(current)) {
                           history.add(current);
                        } else {
                           loops.add(new Point(i,j));
                           loop = true;
                        }
                        
                     }
                     
                     else {
                        break;
                     }
                  }
                  
                  else if(direction == Direction.LEFT) {
                     if(x - 1 >= 0) {
                        if(board[x-1][y] == '#') {
                           Direction dir = rotate(direction);
                           current = new Bearing(x, y, dir);
                        }
                        
                        else {
                           current = new Bearing(x-1, y, direction);
                        }
                        
                        if(!history.contains(current)) {
                           history.add(current);
                        } else {
                           loops.add(new Point(i,j));
                           loop = true;
                        }
                     }
                     
                     else {
                        break;
                     }
                  }
                  
               }
               
               
               
               
               
               
               
               
               
               
            }
         }
      }
      
      this.partTwoAnswer = String.valueOf(loops.size());
      return this.partTwoAnswer;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   public char[][] copyGrid(char[][] original){
      int width = rawList.get(0).length();
      int height = rawList.size();
      char[][] copy = new char[width][height];
      
      for(int y=0; y < height; y++) {         
         for(int x=0; x < width; x++) {
            copy[x][y] = original[x][y];
         }
      }
      
      return copy;
   }
   
   
   
   
   
   
   
   
   public Direction rotate(Direction direction) {
      if(direction == Direction.UP) {
         return Direction.RIGHT;
      }
      
      if(direction == Direction.RIGHT) {
         return Direction.DOWN;
      }
      
      if(direction == Direction.DOWN) {
         return Direction.LEFT;
      }
      
      return Direction.UP;
   }
   
   public class Bearing{
      public Direction direction;
      public Point p;
      
      public Bearing(int x, int y, Direction direction) {
         this.p = new Point(x, y);
         this.direction = direction;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + getEnclosingInstance().hashCode();
         result = prime * result + Objects.hash(direction, p);
         return result;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         Bearing other = (Bearing) obj;
         if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
            return false;
         return direction == other.direction && Objects.equals(p, other.p);
      }

      private Solution_2024D6 getEnclosingInstance() {
         return Solution_2024D6.this;
      }
   }
}