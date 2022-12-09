package com.spyatthehatch.solutions;

import com.spyatthehatch.util.AlphabetUtils;

/**
 * Solution for Advent of Code 2022, Day 6.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D6 extends AbstractSolution {  
   public Solution_2022D6(){
      super();
   }
   
   public String solvePartOne() {
      this.partOneAnswer = String.valueOf(checkStream(4));
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      this.partTwoAnswer = String.valueOf(checkStream(14));
      return this.partTwoAnswer;
   }
   
   /**
    * Check the stream for duplicate characters. Find the index of the last
    * character that does not have duplicates within the given buffer width.
    * 
    * @param width Width of the buffer to check.
    * @return the index of the last character to not have duplicate characters
    * within the provided width.
    */
   private int checkStream(final int width){
      final String stream = this.rawList.get(0);
      int marker = 0;

      for(int i=0; i < stream.length(); i++){
         final String buffer = stream.substring(i, i + width);
         
         if(!AlphabetUtils.containsDupes(buffer)){
            marker = i + width;
            break;
         }
      }
      
      return marker;
   }
}