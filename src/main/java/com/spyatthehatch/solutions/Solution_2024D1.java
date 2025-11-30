package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 1.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D1 extends AbstractSolution {
	/**
	 * Constructor.
	 */
   public Solution_2024D1(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      long total = 0;
      
      List<Integer>lefts = new ArrayList<Integer>();
      List<Integer>rights = new ArrayList<Integer>();
      
      for(final String s : rawList){  
    	  String[] numbers = s.split("   ");
    	  Integer left = Integer.valueOf(numbers[0]);
    	  Integer right = Integer.valueOf(numbers[1]);
    	  lefts.add(left);
    	  rights.add(right);
      }
      
      Collections.sort(lefts);
      Collections.sort(rights);
      
      for(int j=0; j < lefts.size(); j++) {
    	  LOGGER.trace("number " + j + ":" + lefts.get(j));
      }
      
      for(int i = 0; i < lefts.size(); i++) {
    	  total += Math.abs(lefts.get(i) - rights.get(i));
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      
      List<Integer>lefts = new ArrayList<Integer>();
      List<Integer>rights = new ArrayList<Integer>();
      
      for(final String s : rawList){  
    	  String[] numbers = s.split("   ");
    	  Integer left = Integer.valueOf(numbers[0]);
    	  Integer right = Integer.valueOf(numbers[1]);
    	  lefts.add(left);
    	  rights.add(right);
      }
      
      for(int i=0; i < lefts.size(); i++) {
    	  int similarity = 0;
    	  
    	  for(int j=0; j < rights.size(); j++) {
    		  if(lefts.get(i).equals(rights.get(j))) {
    			  similarity++;
    		  }
    	  }
    	  
    	  total += similarity * lefts.get(i);
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}