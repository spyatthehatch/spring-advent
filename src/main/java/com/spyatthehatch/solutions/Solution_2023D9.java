package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2023, Day 8.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D9 extends AbstractSolution {
 
   
	/**
	 * Constructor.
	 */
   public Solution_2023D9(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      List<Integer[]> oasis = new ArrayList<Integer[]>();
      
      for(String s : rawList) {
         String[] line = s.split(" ");
         Integer[] values = new Integer[line.length];
         for(int i=0; i < line.length; i++) {
            values[i] = Integer.valueOf(line[i].trim());
         }
         
         oasis.add(values);
      }
      
      for(Integer[] values : oasis) {
         total += extrapolate(values);
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      List<Integer[]> oasis = new ArrayList<Integer[]>();
      
      for(String s : rawList) {
         String[] line = s.split(" ");
         Integer[] values = new Integer[line.length];
         for(int i=0; i < line.length; i++) {
            values[i] = Integer.valueOf(line[i].trim());
         }
         
         oasis.add(values);
      }
      
      for(Integer[] values : oasis) {
         total += predict(values);
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public int extrapolate(Integer[] values) {
      Integer[] nextLine = new Integer[values.length - 1];
      
      for(int i=0; i < values.length - 1; i++) {
         nextLine[i] = values[i+1] - values[i];
      }
     
      if(nextLine[nextLine.length -1] == 0) {
         return values[values.length -1];
      } else {
         return values[values.length-1] + extrapolate(nextLine);  
      }
   }
   
   public int predict(Integer[] values) {
      Integer[] nextLine = new Integer[values.length - 1];
      
      for(int i=0; i < values.length - 1; i++) {
         nextLine[i] = values[i+1] - values[i];
      }
      
      if(nextLine[nextLine.length -1] == 0) {
         return values[0];
      } else {
         return values[0] - predict(nextLine);  
      }
   }
}