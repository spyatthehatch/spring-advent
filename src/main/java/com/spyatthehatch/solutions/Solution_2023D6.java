package com.spyatthehatch.solutions;


/**
 * Solution for Advent of Code 2023, Day 6.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D6 extends AbstractSolution {

	/**
	 * Constructor.
	 */
   public Solution_2023D6(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){     
      int firstRace = waysToWin(56, 499);
      int secondRace = waysToWin(97, 2210);
      int thirdRace = waysToWin(77, 1097);
      int fourthRace = waysToWin(93, 1440);

      this.partOneAnswer = String.valueOf(firstRace * secondRace * thirdRace * fourthRace);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {      
      this.partTwoAnswer = String.valueOf(waysToWin(56977793L, 499221010971440L));
      return this.partTwoAnswer;
   }
   
   public int waysToWin(int time, int distance) {
      int wins = 0;
      
      for(int i=1; i < time; i++) {
         int dist = i * (time - i);
         if(dist > distance) {
            wins++;
         }
      }
      
      return wins;
   }
   
   public long waysToWin(long time, long distance) {
      long wins = 0;
      
      for(long i=1; i < time; i++) {
         long dist = i * (time - i);
         if(dist > distance) {
            wins++;
         }
      }
      
      return wins;
   }
}