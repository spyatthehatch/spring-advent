package com.spyatthehatch.puzzles;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spyatthehatch.util.ResourceReader;
import com.spyatthehatch.advent.Constants;
import com.spyatthehatch.solutions.Solution;
import com.spyatthehatch.solutions.SolutionFactory;

/**
 * Object to read puzzles from XML and manage as a cache.  During read of the
 * puzzles, the PuzzleManager will call the SolutionFactory to instantiate
 * solution objects as well.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class PuzzleManager {
   /**
    * Logger.
    */
   private static final Logger LOGGER = 
      LoggerFactory.getLogger(PuzzleManager.class);
      
   /**
    * Map of Puzzle objects, arranged by Puzzle ID.
    */
   private Map<String, Puzzle> puzzleMap;
      
   /**
    * Constructor.
    */
   public PuzzleManager(){
      this.puzzleMap = new HashMap<String, Puzzle>();
      final List<Puzzle> puzzles = getPuzzlesFromResource(Constants.PUZZLES_2022);
      for(Puzzle p : puzzles){
         final String id = p.getId();
         final Solution s = SolutionFactory.newInstance(id);
         
         if(s != null){
            p.setSolution(s);
         }
         
         this.puzzleMap.put(id, p);
         LOGGER.debug("Added " + p.toString());
      }
      
      LOGGER.info("Added " + this.puzzleMap.size() + " puzzles.");
   }

   /**
    * Get a Puzzle object from the Puzzle Manager.
    * 
    * @param id ID of Puzzle object to retrieve.
    * @return Puzzle object if  it exists, null otherwise.
    */
   public Puzzle getPuzzle(final String id){
      return this.puzzleMap.get(id);
   }
   
   /**
    * Read puzzles from an XML file and return a list of Puzzle objects.
    * 
    * @param resource Resource XML file.
    * @return List of Puzzle objects read from XML file.
    */
   public List<Puzzle> getPuzzlesFromResource(final String resource){
      List<Puzzle> puzzleList = null;
      Puzzles puzzles = null;
      
      try {
         final ResourceReader reader = new ResourceReader(resource);
         final InputStreamReader is = reader.getInputStreamReader();
         final JAXBContext jaxbContext = JAXBContext.newInstance(Puzzles.class);
         final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         final Object o = jaxbUnmarshaller.unmarshal(is);
         
         if(o instanceof Puzzles){
           puzzles = (Puzzles)o; 
         } else {
            LOGGER.warn("Resource " + resource + " does not contain Puzzles objects.");
            throw new RuntimeException("Unable to unmarshal Puzzles resource.");
         }
         
         puzzleList = puzzles.getPuzzles();
      } catch (final JAXBException e){       
         LOGGER.warn(e.getMessage());
         e.printStackTrace();
      }
      
      return puzzleList;
   }
   
   /**
    * Solve the Puzzle (Part One and Two) for the provided ID.
    * 
    * @param id Puzzle to solve.
    */
   public void solve(final String id){
      final Puzzle p = getPuzzle(id);
      
      if(p != null){
         p.solvePartOne();
         p.solvePartTwo();
      } else {
         LOGGER.warn("Unable to locate Puzzle " + id + ". Cannot solve.");
      }
   }
   
   /**
    * Solve all Puzzles.
    */
   public void solve(){
      this.puzzleMap.forEach((k, v) -> solve(k));
   }
   
   /**
    * Print the Solution answers (Part One and Two) for a particular Puzzle ID.
    * 
    * @param id Puzzle ID to print answers.
    */
   public void printReport(final String id){
      final Puzzle p = getPuzzle(id);
      
      if(p != null){
         LOGGER.info("Puzzle " + id + " Part One solution: " + p.getPartOneAnswer());
         LOGGER.info("Puzzle " + id + " Part Two solution: " + p.getPartTwoAnswer());
      } else {
         LOGGER.warn("Unable to locate Puzzle " + id + ". Cannot print.");
      }
   }
   
   /**
    * Print the Solution answers (Part One and Two) for all Puzzles managed by
    * this Puzzle Manager.
    */
   public void printReport(){
      this.puzzleMap.forEach((k, v) -> printReport(k));
   }
}