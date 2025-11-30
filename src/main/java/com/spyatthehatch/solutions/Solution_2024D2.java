package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 2.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D2 extends AbstractSolution {
	/**
	 * Constructor.
	 */
   public Solution_2024D2(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
            
      for(final String s : rawList){  
    	  String[] line = s.split(" ");
    	  List<Integer>data = new ArrayList<Integer>();
    	  
    	  for(int i=0; i < line.length; i++) {
    		  data.add(Integer.valueOf(line[i]));
    	  }    	  

    	  if(checkSafety(data)) {
    		  total++;
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
      
      for(final String s : rawList){  
    	  String[] line = s.split(" ");
    	  List<Integer>data = new ArrayList<Integer>();
    	  
    	  for(int i=0; i < line.length; i++) {
    		  data.add(Integer.valueOf(line[i]));
    	  }

    	  boolean safe = checkSafety(data);
    	  
    	  if(safe) {
    		  total++;
    	  } else {   		  
    		  for(int i=0; i<data.size() && !safe; i++) {
    			  List<Integer>copy = new ArrayList<Integer>(data);
    			  copy.remove(i);
    			  
    			  safe = checkSafety(copy);
    			  
    			  if(safe) {
    				  total++;
    				  break;
    			  }
    		  }
    	  }
      } 
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
      
   boolean checkSafety(List<Integer> data) {
 	  boolean ascending = true;
 	  boolean safe = true;
 	  
 	  if(data.get(1) - data.get(0) < 0) {
 		  ascending = false;
 	  }
 	  
 	  for(int i=0; i < data.size() - 1; i++) {
 		  if(data.get(i+1) - data.get(i) == 0){
 			  safe = false;
 			  break;
 		  }
 		  
 		  if(ascending && data.get(i+1) - data.get(i) > 0 && data.get(i+1) - data.get(i) < 4) {
 			  continue;
 		  }
 		  
 		  if(!ascending && data.get(i+1) - data.get(i) < 0 && data.get(i+1) - data.get(i) > -4) {
 			  continue;
 		  }
 		  
 		  safe = false;
 		  break;
 	  }
 	  
 	  if(safe) {
 		  return true;
 	  } else {
 		  return false;
 	  }
   }
}