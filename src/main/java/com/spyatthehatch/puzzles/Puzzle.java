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
   private int year;
   
   /**
    * The day number for this year, without leading zeros.
    */
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
    * URL to this Advent of Code puzzle.
    */
   private String url;
   
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
    * Set the URL for this Puzzle object.
    * 
    * @param url URL for this Puzzle object.
    */
   public void setUrl(final String url){
      this.url = url;
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
    * Get the year of this Puzzle object.
    * 
    * @return Year of this Puzzle object.
    */
   @XmlAttribute(required = true)
   public int getYear() {
      return this.year;
   }
   
   /**
    * Get the day of this Puzzle object.
    * 
    * @return Day of this Puzzle object.
    */
   @XmlAttribute(required = true)
   public int getDay() {
      return this.day;
   }
   
   /**
    * Get the title for this Puzzle object.
    * 
    * @return Title for this Puzzle object.
    */
   @XmlElement
   public String getTitle() {
      return this.title;
   }

   /**
    * Get the URL for this Puzzle object.
    * 
    * @return URL for this Puzzle object.
    */
   @XmlElement
   public String getUrl() {
      return this.url;
   }
   
   /**
    * Get the Part One description for this Puzzle object.
    * 
    * @return Part One description for this Puzzle object.
    */
   @XmlElement
   public String getPartOneDescription() {
      return this.partOneDescription;
   }

   /**
    * Get the Part Two description for this Puzzle object.
    * 
    * @return Part Two description for this Puzzle object.
    */
   @XmlElement
   public String getPartTwoDescription() {
      return this.partTwoDescription;
   }
   
   /**
    * Get the ID for this Puzzle object, defined as YYYY + "D" + D.  For
    * example, "2022D1" or "2022D14".
    * 
    * @return Unique identifier fort his puzzle.
    */
   public String getId(){
      if(this.id == null){
         this.id = this.year + ID_DELIMITER + this.day;
      }
      
      return this.id;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.day;
      result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
      result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
      result = prime * result + this.year;
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
      if (this.day != other.day)
         return false;
      if (this.id == null) {
         if (other.id != null)
            return false;
      } else if (!this.id.equals(other.id))
         return false;
      if (this.title == null) {
         if (other.title != null)
            return false;
      } else if (!this.title.equals(other.title))
         return false;
      if (this.year != other.year)
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "Puzzle id:" + this.id + ", year:" + this.year + ", day:" +
         this.day + ", title:" + this.title;
   }
}