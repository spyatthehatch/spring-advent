package com.spyatthehatch.solutions;

/**
 * Interface for all solution classes to implement.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public interface Solution {
   /**
    * Get the Part 1 solution answer.
    * 
    * @return Answer to Part 1.
    */
   public String getPartOneAnswer();
   
   /**
    * Get the Part 2 solution answer.
    * 
    * @return Answer to Part 2.
    */
   public String getPartTwoAnswer();
   
   /**
    * Get the ID for this Solution object.
    * 
    * @return ID of this Solution.
    */
   public String getId();
   
   /**
    * Performs the algorithm for solving Part 1 of the puzzle.  Returns the 
    * answer, if solved.
    * 
    * @return the answer to Part 1 of the puzzle, if solved.
    */
   public String solvePartOne();
   
   /**
    * Performs the algorithm for solving Part 2 of the puzzle.  Returns the 
    * answer, if solved.
    * 
    * @return the answer to Part 2 of the puzzle, if solved.
    */
   public String solvePartTwo();
}