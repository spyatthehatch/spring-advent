package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution for Advent of Code 2023, Day 4.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D4 extends AbstractSolution {

	/**
	 * Constructor.
	 */
   public Solution_2023D4(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      
      for(String s : rawList) {
         String[] numbers = s.split(":");         
         String[] winLoseNumbers = numbers[1].trim().split("\\|");         
         String[] winning = winLoseNumbers[0].split(" ");
         String[] myNumbers = winLoseNumbers[1].split(" ");
         
         List<Integer> win = new ArrayList<Integer>();
         List<Integer> mine = new ArrayList<Integer>();
    
         
         for(String w : winning) {
            if(!w.isEmpty()) {
               win.add(Integer.valueOf(w.trim()));
            }
         }
         
         for(String m : myNumbers) {
            if(!m.isEmpty()) {
               mine.add(Integer.valueOf(m.trim()));
            }
         }
         
         int count = 0;
         for(Integer i : win) {
            if(mine.contains(i)) {
               count++;
            }
         }
         
         int score = 0;
         if(count > 0) {
            score = (int)Math.pow(2, count - 1);
         }
         total += score;
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      int cardNum = 0;
      int cardCount[] = new int[rawList.size()];
      Arrays.fill(cardCount, 1);

      for(String s : rawList) {
         String[] numbers = s.split(":");         
         String[] winLoseNumbers = numbers[1].trim().split("\\|");         
         String[] winning = winLoseNumbers[0].split(" ");
         String[] myNumbers = winLoseNumbers[1].split(" ");
         
         List<Integer> win = new ArrayList<Integer>();
         List<Integer> mine = new ArrayList<Integer>();
    
         
         for(String w : winning) {
            if(!w.isEmpty()) {
               win.add(Integer.valueOf(w.trim()));
            }
         }
         
         for(String m : myNumbers) {
            if(!m.isEmpty()) {
               mine.add(Integer.valueOf(m.trim()));
            }
         }
         
         int count = 0;
         for(Integer i : win) {
            if(mine.contains(i)) {
               count++;
            }
         }
         
         for(int i = 1; i <= count; i++) {
            cardCount[cardNum + i] += cardCount[cardNum];
         }
         
         cardNum++;
      }
      
      for(int i=0; i < cardCount.length; i++) {
         total += cardCount[i];
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}