package com.spyatthehatch.solutions;

import java.util.HashMap;
import java.util.Map;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2023, Day 3.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D3 extends AbstractSolution {
   char[][] map;
   
   int height;
   
   int width;
	/**
	 * Constructor.
	 */
   public Solution_2023D3(){
      super();
      this.height = rawList.size();
      this.width = rawList.get(0).length();
      this.map = new char[width][height];
      
      LOGGER.info("Initialized map width: " + this.width + ", height:" + height);
      
      for(int y=0; y < this.height; y++){
         for(int x=0; x < this.width; x++){
            this.map[x][y] = rawList.get(y).charAt(x);
         }
      }
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      
      for(int y=0; y < this.height; y++){
         String number = null;
         boolean verified = false;
         
         for(int x=0; x < this.width; x++){
            if(!Character.isDigit(this.map[x][y]) && number != null){
               if(verified){
                  int value = Integer.valueOf(number);          
                  total += value;
               }
               
               number = null;
               verified = false;
            }
            
            else if(!Character.isDigit(this.map[x][y]) && number == null){
               continue;
            }
            
            else if(Character.isDigit(this.map[x][y])){
               verified = (isValid(x, y) || verified);

               if(number == null){
                  number = String.valueOf(this.map[x][y]);
               } else {
                  number = number + String.valueOf(this.map[x][y]);
               }    
            }
         }
         
         if(verified){
            int value = Integer.valueOf(number);
            total += value;
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
      Map<Point, Integer> gears = new HashMap<Point, Integer>();
      
      for(int y=0; y < this.height; y++){
         String number = null;
         Point nearGear = null;
         
         for(int x=0; x < this.width; x++){
            if(!Character.isDigit(this.map[x][y]) && number != null){
               if(nearGear != null){
                  if(gears.containsKey(nearGear)){
                     int value = gears.get(nearGear) * Integer.valueOf(number);
                     total += value;
                  } else {
                     gears.put(nearGear, Integer.valueOf(number));
                  }
               }
               
               number = null;
               nearGear = null;
            }
            
            else if(!Character.isDigit(this.map[x][y]) && number == null){
               continue;
            }
            
            else if(Character.isDigit(this.map[x][y])){
               Point p = isNearGear(x, y);
               if(p != null){
                  nearGear = p;
               }

               if(number == null){
                  number = String.valueOf(this.map[x][y]);
               } else {
                  number = number + String.valueOf(this.map[x][y]);
               }
            }
         }
         
         if(nearGear != null){
            if(gears.containsKey(nearGear)){
               int value = gears.get(nearGear) * Integer.valueOf(number);
               total += value;
            } else {
               gears.put(nearGear, Integer.valueOf(number));
            }
         }
      }

      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   private Point isNearGear (int x, int y){      
      for(int j = -1; j >= -1 && j < 2; j++){
         if(y + j >= 0 && y + j < this.height){
            
            for(int i = -1; i >= -1 && i < 2; i++){
               if(x + i >= 0 && x + i < this.width){
                  if(this.map[x + i][y + j] == '*'){
                     return new Point(x + i, y + j);
                  }
               }
            }
         }
      }
      
      return null;  
   }
   
   private boolean isValid (int x, int y){
      boolean valid = false;
      
      for(int j = -1; j >= -1 && j < 2; j++){
         if(y + j >= 0 && y + j < this.height){
            
            for(int i = -1; i >= -1 && i < 2; i++){
               if(x + i >= 0 && x + i < this.width){
                  if(!Character.isDigit(this.map[x + i][y + j]) && this.map[x + i][y + j] != '.'){
                     return true;
                  }
               }
            }
         }
      }
      
      return valid;
   }
}