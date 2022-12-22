package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 2.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D2 extends AbstractSolution {  
   /**
    * Character to refer to opponent choice of Rock, 'A'.
    */
   public static final char ROCK = 'A';
   
   /**
    * Character to refer to opponent choice of Paper, 'B'.
    */
   public static final char PAPER = 'B';
   
   /**
    * Character to refer to opponent choice of Scissors, 'C'.
    */
   public static final char SCISSORS = 'C';
   
   public Solution_2022D2(){
      super();
   }

   public String solvePartOne(){
      int score = 0;

      for (String s : this.rawList){
         char them = s.charAt(0);
         char me = s.charAt(2);
         
         score += getScoreWinStrategy(them, me);
      }
      
      this.partOneAnswer = String.valueOf(score);
      return this.partOneAnswer;
   }
   
   public String solvePartTwo() {
      int score = 0;
      
      for (String s : this.rawList){
         final char them = s.charAt(0);
         final char me = s.charAt(2);
         score += getScoreChooseStrategy(them, me);
      }
      
      this.partTwoAnswer = String.valueOf(score);
      return this.partTwoAnswer;
   }
   
   /**
    * Play a game of Rock, Paper, Scissors, using the strategy that the input
    * is the choice of Rock, Paper, or Scissors.
    * 
    * @param them Choice of Rock, Paper, or Scissor, where  'A' is Rock, 'B' is
    * Paper, and anything else is Scissors. 
    * @param me Choice of Rock, Paper, or Scissors, where 'X' is Rock, 'Y' is
    * Paper, and anything else is Scissors.
    * @return Return the score: 6 points for winning, 3 for a draw, and 0 for
    * a loss.  There is also 1 additional point for choosing Rock, 2 for Paper,
    * and 3 for Scissors.
    */
   public int getScoreWinStrategy(final char them, final char me){
      int score;
      final char MY_ROCK = 'X';
      final char MY_PAPER = 'Y';
      
      if(them == ROCK){
         if(me == MY_ROCK){
            score = 4;
         } else if (me == MY_PAPER){
            score = 8;
         } else {
            score = 3;
         }
      } else if (them == PAPER){
         if(me == MY_ROCK){
            score = 1;
         } else if (me == MY_PAPER){
            score = 5;
         } else {
            score = 9;
         }
      } else {
         if(me == MY_ROCK){
            score = 7;
         } else if (me == MY_PAPER){
            score = 2;
         } else {
            score = 6;
         }
      }
      
      return score;
   }
   
   /**
    * Play a game of Rock, Paper, Scissors, using the strategy that the input
    * is the choice of Lose, Tie, or Win.
    * 
    * @param them Choice of Rock, Paper, or Scissor, where  'A' is Rock, 'B' is
    * Paper, and anything else is Scissors. 
    * @param me Choice of Rock, Paper, or Scissors, where 'X' is to lose, 'Y'
    * is to tie, and anything else is to Win.
    * @return Return the score: 6 points for winning, 3 for a draw, and 0 for
    * a loss.  There is also 1 additional point for choosing Rock, 2 for Paper,
    * and 3 for Scissors.
    */
   public int getScoreChooseStrategy(final char them, final char me){
      int score;
      final char LOSE = 'X';
      final char TIE = 'Y';
      
      if(them == ROCK){
         if(me == LOSE){
            score = 3;
         } else if (me == TIE){
            score = 4;
         } else {
            score = 8;
         }
      } else if (them == PAPER){
         if(me == LOSE){
            score = 1;
         } else if (me == TIE){
            score = 5;
         } else {
            score = 9;
         }
      } else {
         if(me == LOSE){
            score = 2;
         } else if (me == TIE){
            score = 6;
         } else {
            score = 7;
         }
      }
      
      return score;
   }
}