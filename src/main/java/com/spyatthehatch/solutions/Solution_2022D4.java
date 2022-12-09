package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 4.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D4 extends AbstractSolution {   
   public Solution_2022D4(){
      super();
   }

   public String solvePartOne(){
      int count = 0;
      
      for(final String pairs : this.rawList){
         final String elfOne = pairs.split(",")[0];
         final String elfTwo = pairs.split(",")[1];
         final int oneMin = Integer.parseInt(elfOne.split("-")[0]);
         final int oneMax = Integer.parseInt(elfOne.split("-")[1]);
         final int twoMin = Integer.parseInt(elfTwo.split("-")[0]);
         final int twoMax = Integer.parseInt(elfTwo.split("-")[1]); 
         
         /*
          * Check conditions where Elf One's region completely overlaps Elf
          * Two's region, or if Elf Two's region completely overlaps Elf One's
          * region.
          */
         if((oneMin <= twoMin && oneMax >= twoMax)||
            (twoMin <= oneMin && twoMax >= oneMax)){
            count++;
         }
      }
      
      this.partOneAnswer = String.valueOf(count);
      return this.partOneAnswer;
   }
 
   public String solvePartTwo() {
      int count = 0;

      
      for(String pairs : this.rawList){
         final String elfOne = pairs.split(",")[0];
         final String elfTwo = pairs.split(",")[1];
         final int oneMin = Integer.parseInt(elfOne.split("-")[0]);
         final int oneMax = Integer.parseInt(elfOne.split("-")[1]);
         final int twoMin = Integer.parseInt(elfTwo.split("-")[0]);
         final int twoMax = Integer.parseInt(elfTwo.split("-")[1]); 
         
         /*
          * Checking the no overlap cases are easier.  Check the opposite of
          * Elf Two's region greater or less than Elf One's region (just
          * checking the mins and maxes are necessary since min < max, and max
          * > min).
          */
         if(!(twoMin > oneMax || twoMax < oneMin)){
            count++;
         }
      }
      
      this.partTwoAnswer = String.valueOf(count);
      return this.partTwoAnswer;
   }
}