package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2023, Day 1.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D1 extends AbstractSolution {
   /**
    * 
    */
	public static String[] NUMBERS = {"one", "two", "three", "four", "five",
	   "six", "seven", "eight", "nine"};

	/**
	 * Constructor.
	 */
   public Solution_2023D1(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;

      for(String s : rawList){
    	  String num = null;
    	  
    	  for (int i = 0; i < s.length(); i++){
    		  char c = s.charAt(i);
    		  if(Character.isDigit(c)){
    			  num = String.valueOf(c);
    			  break;
    		  }
    	  }
    	  
    	  for (int i = s.length() -1; i >= 0; i--){
    		  char c = s.charAt(i);
    		  if(Character.isDigit(c)){
    			  num = num + String.valueOf(c);
    			  break;
    		  }
    	  }
    	  
    	  int number = Integer.valueOf(num);
    	  total += number;
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;

      for(String s : rawList){
    	  String num = null;
        String firstNum = null;
        String lastNum = null;
    	  int firstNumIndex = 0;
    	  int lastNumIndex = s.length() - 1;

    	  for (int i = 0; i < s.length(); i++){
    		  char c = s.charAt(i);
    		  if(Character.isDigit(c)){
    			  firstNumIndex = i;
    			  firstNum = String.valueOf(c);
    			  break;
    		  }
    	  }  
    	  
    	  for(int i = 0; i < NUMBERS.length; i++){
    	     int index = s.indexOf(NUMBERS[i]);
    	     
    	     if(index >= 0 && index < firstNumIndex){
    	        firstNum = String.valueOf(i + 1);
    	        firstNumIndex = index;
    	     }
    	  }
    	  
    	  for (int i = s.length() - 1; i >= 0; i--){
    		  char c = s.charAt(i);
    		  if(Character.isDigit(c)){
    			  lastNumIndex = i;
    			  lastNum = String.valueOf(c);
    			  break;
    		  }
    	  }
    	  
    	  for (int i = 0; i < NUMBERS.length; i++){
    	     int index = s.lastIndexOf(NUMBERS[i]);
    	     
    	     if(index >= 0 && index > lastNumIndex){
    	        lastNum = String.valueOf(i + 1);
    	        lastNumIndex = index;
    	     }
    	  }

    	  num = firstNum + lastNum;
    	  int number = Integer.valueOf(num);
    	  total += number;
    	  
    	 LOGGER.info("Number:" + number + ", total:" + total);
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}