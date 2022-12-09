package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 1.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D1 extends AbstractSolution {   
   public Solution_2022D1(){
      super();
   }

   public String solvePartOne(){
      int most = 0;
      int count = 0;
      
      for(final String s : rawList){
         if (!s.isEmpty()){
            count += Integer.valueOf(s);
         } else {
            if(count > most){
               most = count;
            }

            count = 0;
         }
      }
      
      this.partOneAnswer = String.valueOf(most);
      return this.partOneAnswer;
   }
   
   public String solvePartTwo() {
      int most = 0;
      int secondMost = 0;
      int thirdMost = 0;
      int count = 0;
      
      for(final String s : rawList){
         if (!s.isEmpty()){
            count += Integer.valueOf(s);
         } else {
            if(count > most){
               thirdMost = secondMost;
               secondMost = most;
               most = count;
            } else if(count > secondMost){
               thirdMost = secondMost;
               secondMost = count;
            } else if (count > thirdMost){
               thirdMost = count;
            }
            
            count = 0;
         }
      }
      
      this.partTwoAnswer = String.valueOf(most + secondMost + thirdMost);
      return this.partTwoAnswer;
   }
}