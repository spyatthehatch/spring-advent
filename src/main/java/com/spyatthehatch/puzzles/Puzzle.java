package com.spyatthehatch.puzzles;

import javax.xml.bind.annotation.*;

import com.spyatthehatch.solutions.Solution;

/**
 * This object represents one Advent of Code day puzzle.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
@XmlRootElement (name = "puzzle")
public class Puzzle {
   /**
    * Delimiter in Puzzle ID.
    */
   public static final String ID_DELIMITER = "D";
   
   /**
    * The year for this Puzzle.
    */
   @XmlAttribute(required = true)
   private int year;
   
   /**
    * The day number for this year, without leading zeros.
    */
   @XmlAttribute(required = true)
   private int day;
   
   /**
    * The title of this Puzzle.
    */
   private String title;
   
   /**
    * The Part 1 description of this Puzzle.
    */
   private String partOneDescription;
   
   /**
    * The Part 2 description of this Puzzle.
    */
   private String partTwoDescription;
   
   /**
    * Unique identifier for this Puzzle object.  This must match with the
    * respective Solution ID for association.
    */
   private String id = null;

   /**
    * Solution to this Puzzle.
    */
   private Solution solution;
   
   /**
    * Set the year for this Puzzle object.
    * 
    * @param year Year of this Puzzle object.
    */
   public void setYear(final int year){
      this.year = year;
   }

   /**
    * Set the day for this Puzzle object.
    * 
    * @param day Day of this Puzzle object.
    */
   public void setDay(final int day){
      this.day = day;
   }
   
   /**
    * Set the title for this Puzzle object.
    * 
    * @param title Title of this Puzzle object.
    */
   public void setTitle(final String title){
      this.title = title;
   }
   
   /**
    * Set the Part One description for this Puzzle object.
    * 
    * @param desc Part One description for this Puzzle object.
    */
   public void setPartOneDescription(final String desc){
      this.partOneDescription = desc;
   }
   
   /**
    * Set the Part Two description for this Puzzle object.
    * 
    * @param desc Part Two description for this Puzzle object.
    */
   public void setPartTwoDescription(final String desc){
      this.partTwoDescription = desc;
   }
   
   /**
    * Set the Solution to this Puzzle object.
    * 
    * @param s Solution to this Puzzle object.
    */
   public void setSolution(final Solution s){
      this.solution = s;
   }
   
   /**
    * Get the answer to Part One of this Puzzle, if it has been solved
    * already.
    * 
    * @return the answer to Part One of this Puzzle.
    */
   public String getPartOneAnswer(){
      return this.solution.getPartOneAnswer();      
   }
   
   /**
    * Get the answer to Part Two of this Puzzle, if it has been solved
    * already.
    * 
    * @return the answer to Part Two of this Puzzle.
    */
   public String getPartTwoAnswer(){
      return this.solution.getPartTwoAnswer();
   }
   
   /**
    * Solve Part One of this Puzzle and return the answer.
    * 
    * @return the answer to Part One of this Puzzle.
    */
   public String solvePartOne(){
      return this.solution.solvePartOne();
   }
   
   /**
    * Solve Part Two of this Puzzle and return the answer.
    * 
    * @return the answer to Part Two of this Puzzle.
    */
   public String solvePartTwo(){
      return this.solution.solvePartTwo();
   }
   
   /**
    * Get the title for this Puzzle object.
    * 
    * @return Title for this Puzzle object.
    */
   @XmlElement
   public String getTitle() {
      return title;
   }

   /**
    * Get the Part One description for this Puzzle object.
    * 
    * @return Part One description for this Puzzle object.
    */
   @XmlElement
   public String getPartOneDescription() {
      return partOneDescription;
   }

   /**
    * Get the Part Two description for this Puzzle object.
    * 
    * @return Part Two description for this Puzzle object.
    */
   @XmlElement
   public String getPartTwoDescription() {
      return partTwoDescription;
   }
   
   /**
    * Get the ID for this Puzzle object, defined as YYYY + "D" + D.  For
    * example, "2022D1" or "2022D14".
    * 
    * @return Unique identifier fort his puzzle.
    */
   public String getId(){
      if(this.id == null){
         this.id = year + ID_DELIMITER + day;
      }
      
      return this.id;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + day;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      result = prime * result + year;
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
      Puzzle other = (Puzzle) obj;
      if (day != other.day)
         return false;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (title == null) {
         if (other.title != null)
            return false;
      } else if (!title.equals(other.title))
         return false;
      if (year != other.year)
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "Puzzle id:" + this.id + ", year:" + this.year + ", day:" +
         this.day + ", title:" + this.title;
   }
}