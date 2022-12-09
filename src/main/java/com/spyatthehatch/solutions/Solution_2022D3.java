package com.spyatthehatch.solutions;

import com.spyatthehatch.util.AlphabetUtils;

/**
 * Solution for Advent of Code 2022, Day 3.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D3 extends AbstractSolution {   
   public Solution_2022D3(){
      super();
   }

   public String solvePartOne(){
      int priority = 0;
      
      for (String s : this.rawList){
         final int length = s.length();
         final String first = s.substring(0, (length/2));
         final String last = s.substring(length/2, length);

         final char common = AlphabetUtils.getCommon(first, last);
         priority += AlphabetUtils.getAlphaIndex(common);
      }
      
      this.partOneAnswer = String.valueOf(priority);
      return this.partOneAnswer;
   }
   
   public String solvePartTwo() {
      int priority = 0;
      
      for(int i=0; i < this.rawList.size(); i += 3){
         final String first = this.rawList.get(i);
         final String second = this.rawList.get(i + 1);
         final String third = this.rawList.get(i + 2);
         
         final char common = AlphabetUtils.getCommon(first, second, third);
         priority += AlphabetUtils.getAlphaIndex(common); 
      }
      
      this.partTwoAnswer = String.valueOf(priority);
      return this.partTwoAnswer;
   }
}