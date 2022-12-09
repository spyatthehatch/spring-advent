package com.spyatthehatch.puzzles;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for XML Puzzle list.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
@XmlRootElement(name = "puzzles")
public class Puzzles {
   /**
    * Private list of puzzles.
    */
   private List<Puzzle> puzzles = null;
   
   /**
    * Get the Puzzle object list.
    * 
    * @return List of Puzzle objects.
    */
   @XmlElement(name = "puzzle")
   public List<Puzzle> getPuzzles() {
      return this.puzzles;
   }
   
   /**
    * Set the Puzzle object list.
    * 
    * @param puzzles List of Puzzles.
    */
   public void setPuzzles(final List<Puzzle> puzzles){
      this.puzzles = puzzles;
   }
   
}