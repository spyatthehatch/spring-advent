package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2024, Day 15.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D15 extends AbstractSolution {
   public Space[][] map;
   
	/**
	 * Constructor.
	 */
   public Solution_2024D15(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      long total = 0;
      int height = 0;
      int width = rawList.get(0).length();
      List<Direction> instructions = new ArrayList<Direction>(Arrays.asList(Direction.values()));
      
      for(String s : rawList) {
         if(s.isEmpty()) {
            continue;
         }
         
         if(s.charAt(0) == '#') {
            height++;
         } else {
            for(int i=0; i<s.length(); i++) {
               switch(s.charAt(i)) {
                  case '^':
                     instructions.add(Direction.UP);
                     break;
                  case 'v':
                     instructions.add(Direction.DOWN);
                     break;
                  case '>':
                     instructions.add(Direction.RIGHT);
                     break;
                  case '<':
                     instructions.add(Direction.LEFT);
                     break;
                  default:
                     LOGGER.trace("Unknown direction read at index:" + i);
               }
            }
         }
      }
      
      LOGGER.trace("Map height:" + height);
      LOGGER.trace("Instructions size:" + instructions.size());
      this.map = new Space[width][height];
      Point current = null;
      
      for(int y=0; y<height; y++) {
         String s = rawList.get(y);
         
         for(int x=0; x<width; x++) {
            switch(s.charAt(x)) {
               case '#':
                  this.map[x][y] = Space.WALL;
                  break;
               case '.':
                  this.map[x][y] = Space.EMPTY;
                  break;
               case '@':
                  this.map[x][y] = Space.EMPTY;
                  current = new Point(x, y);
                  break;
               case 'O':
                  this.map[x][y] = Space.BOX;
                  break;
               default:
                  LOGGER.trace("Unknown Space type.");
                  
            }
         }
      }
      
      for(Direction instruction : instructions) {
         current = updatePosition(instruction, current);
      }
      
      for(int y=0; y<height; y++) {
         for(int x=0; x<width; x++) {
            if(map[x][y] == Space.BOX) {
               total += (100 * y) + x;
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
      int count = 0;

      this.partTwoAnswer = String.valueOf(count);
      return this.partTwoAnswer;
   }
   
   public Point updatePosition(Direction dir, Point p) {
      int x = p.getX();
      int y = p.getY();
      
      switch(dir) {
         // Handle the instruction for going LEFT *****************************
         case LEFT:
            // The neighbor space is empty.
            if(map[x-1][y] == Space.EMPTY) {
               if(map[x][y] == Space.BOX) {
                  map[x][y] = Space.EMPTY;
                  map[x-1][y] = Space.BOX;
               }
               
               return new Point(x-1, y);
            }
            
            // The neighbor space is a wall.
            else if(map[x-1][y] == Space.WALL) {
               return p;
            }
            
            // Else, the neighbor space is a box.
            else {
               updatePosition(dir, new Point(x-1, y));
               if(map[x-1][y] == Space.EMPTY) {
                  if(map[x][y] == Space.BOX) {
                     map[x][y] = Space.EMPTY;
                     map[x-1][y] = Space.BOX;
                  }
                  
                  return new Point(x-1, y);
               } else {
                  return p;
               }
            }
         // Handle the instruction for going right ****************************
         case RIGHT:
            // The neighbor space is empty.
            if(map[x+1][y] == Space.EMPTY) {
               if(map[x][y] == Space.BOX) {
                  map[x][y] = Space.EMPTY;
                  map[x+1][y] = Space.BOX;
               }
               
               return new Point(x+1, y);
            }
            
            // The neighbor space is a wall.
            else if(map[x+1][y] == Space.WALL) {
               return p;
            }
            
            // Else, the neighbor space is a box.
            else {
               updatePosition(dir, new Point(x+1, y));
               if(map[x+1][y] == Space.EMPTY) {
                  if(map[x][y] == Space.BOX) {
                     map[x][y] = Space.EMPTY;
                     map[x+1][y] = Space.BOX;
                  }
                  
                  return new Point(x+1, y);
               } else {
                  return p;
               }
            }
            
         // Handle the instruction for going down *****************************
         case DOWN:
            if(map[x][y+1] == Space.EMPTY) {
               if(map[x][y] == Space.BOX) {
                  map[x][y] = Space.EMPTY;
                  map[x][y+1] = Space.BOX;
               }
               
               return new Point(x, y+1);
            }
            
            else if(map[x][y+1] == Space.WALL) {
               return p;
            }
            
            else {
               updatePosition(dir, new Point(x, y+1));
               if(map[x][y+1] == Space.EMPTY) {
                  if(map[x][y] == Space.BOX) {
                     map[x][y] = Space.EMPTY;
                     map[x][y+1] = Space.BOX;
                  }
                  
                  return new Point(x, y+1);
               } else {
                  return p;
               }
            }
         
         // Handle the instruction for going up *******************************
         case UP:
            if(map[x][y-1] == Space.EMPTY) {
               if(map[x][y] == Space.BOX) {
                  map[x][y] = Space.EMPTY;
                  map[x][y-1] = Space.BOX;
               }
               
               return new Point(x, y-1);
            }
            
            else if(map[x][y-1] == Space.WALL) {
               return p;
            }
            
            else {
               updatePosition(dir, new Point(x, y-1));
               if(map[x][y-1] == Space.EMPTY) {
                  if(map[x][y] == Space.BOX) {
                     map[x][y] = Space.EMPTY;
                     map[x][y-1] = Space.BOX;
                  }
                  
                  return new Point(x, y-1);
               } else {
                  return p;
               }
            }
         default:
            LOGGER.trace("Unknown space.");
      }
      
      return null;
   }
   
   public enum Space {
      EMPTY,
      BOX,
      WALL
   }
   
   public enum Direction {
      UP,
      RIGHT,
      DOWN,
      LEFT
   }
}