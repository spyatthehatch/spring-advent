package com.spyatthehatch.solutions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.util.ResourceReader;

/**
 * Abstract class for all Solution classes to inherit.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public abstract class AbstractSolution implements Solution {
   /**
    * "Unsolved".  String to denote no solution.
    */
   public static final String UNSOLVED = "Unsolved";
   
   /**
    * Underscore character "_", as a String.  The separator character between
    * Solution and ID in the class name.  For example, classes are named
    * Solution_2022D1, where the underscore separates the Solution ad ID of
    * "2022D1".
    */
   public static final String CLASS_NAME_SEPARATOR = "_";
   
   /**
    * Logger.
    */
   protected static final Logger LOGGER = 
      LoggerFactory.getLogger(Solution.class);
   
   /**
    * The answer to Part One of the Puzzle.
    */
   protected String partOneAnswer = null;
   
   /**
    * The answer to Part Two of the Puzzle.
    */
   protected String partTwoAnswer = null;
   
   /**
    * The Puzzle input resource file.
    */
   protected String input = null;
   
   /**
    * The list of Strings read from the Puzzle input resource file.
    */
   protected List<String> rawList = null;
   
   /**
    * Unique identifier for this Solution object.  This must match with the
    * respective Puzzle ID for association.
    */
   protected String id = null;
   
   /**
    * Initialize.
    */
   protected AbstractSolution(){
      String className = this.getClass().getName();
      this.id = className.split(CLASS_NAME_SEPARATOR)[1];
      this.input = "input/" + this.id + ".txt";
      final ResourceReader reader = new ResourceReader(this.input);
      this.rawList = reader.toList();
      reader.close();
   }
   
   /**
    * Get the Part 1 solution answer.
    * 
    * @return Answer to Part 1. Or String contained in UNSOLVED, if unsolved.
    */
   public String getPartOneAnswer() {
      if(this.partOneAnswer != null){
         return this.partOneAnswer;
      } else {
         return UNSOLVED;
      }
   }
   
   /**
    * Get the Part 2 solution answer.
    * 
    * @return Answer to Part 2. Or String contained in UNSOLVED, if unsolved.
    */
   public String getPartTwoAnswer() {
      if(this.partTwoAnswer != null){
         return this.partTwoAnswer;
      } else {
         return UNSOLVED;
      }
   }
   
   /**
    * Get the ID for this Solution.
    * 
    * @return ID for this Solution object.
    */
   public String getId(){
      return this.id;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AbstractSolution other = (AbstractSolution) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }
   
   @Override
   public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append("Solution id:")
      .append(this.id)
      .append(", input:")
      .append(this.input)
      .append(", p1:")
      .append(getPartOneAnswer())
      .append(", p2:")
      .append(getPartTwoAnswer());
      
      return sb.toString();
   }
}