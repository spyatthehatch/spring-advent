package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 2.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D2 extends AbstractSolution {  
   public Solution_2022D2(){
      super();
   }

   public String solvePartOne(){
      int score = 0;

      for (String s : this.rawList){
         char them = s.charAt(0);
         char me = s.charAt(2);
         
         if(them == 'A'){
            if(me == 'X'){
               score += 4;
            } else if (me == 'Y'){
               score += 8;
            } else {
               score += 3;
            }
         } else if (them == 'B'){
            if(me == 'X'){
               score += 1;
            } else if (me == 'Y'){
               score += 5;
            } else {
               score += 9;
            }
         } else {
            if(me == 'X'){
               score += 7;
            } else if (me == 'Y'){
               score += 2;
            } else {
               score += 6;
            }
         }
      }
      
      this.partOneAnswer = String.valueOf(score);
      return this.partOneAnswer;
   }
   
   public String solvePartTwo() {
      int score = 0;
      
      for (String s : this.rawList){
         char them = s.charAt(0);
         char me = s.charAt(2);
         
         if(them == 'A'){
            if(me == 'X'){
               score += 3;
            } else if (me == 'Y'){
               score += 4;
            } else {
               score += 8;
            }
         } else if (them == 'B'){
            if(me == 'X'){
               score += 1;
            } else if (me == 'Y'){
               score += 5;
            } else {
               score += 9;
            }
         } else {
            if(me == 'X'){
               score += 2;
            } else if (me == 'Y'){
               score += 6;
            } else {
               score += 7;
            }
         }
      }
      
      this.partTwoAnswer = String.valueOf(score);
      return this.partTwoAnswer;
   }
}