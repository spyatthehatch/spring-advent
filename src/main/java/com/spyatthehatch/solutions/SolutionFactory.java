package com.spyatthehatch.solutions;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class to instantiate Solution objects per a given Puzzle ID.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class SolutionFactory {
   /*
    * Prevent constructor for this class.
    */
   private SolutionFactory(){};
   
   /**
    * The common portion of package and class name for all Solution classes.
    * Value is: "com.spyatthehatch.solutions.Solution_".
    */
   public static final String SOLUTION_COMMON_NAME =
      "com.spyatthehatch.solutions.Solution_";
   
   /**
    * Logger.
    */
   private static final Logger LOGGER = 
      LoggerFactory.getLogger(SolutionFactory.class);
   
   /**
    * Get a new Solution instance from a Puzzle ID.
    * 
    * @param id Puzzle object ID to create a Solution for.
    * @return Solution for given Puzzle ID, it one exists.  If it does not,
    * this method will return null.
    */
   public static Solution newInstance(final String id){
      Optional<Solution> opt = null;
      
      try{
         opt = Optional.of((Solution) Class
            .forName(SOLUTION_COMMON_NAME + id).newInstance());
      } catch (InstantiationException ie) {
         LOGGER.warn("Count not instantiate Solution object.");
      } catch (IllegalAccessException iae) {
         LOGGER.warn("Illegal access exception for Solution object.");
      } catch (ClassNotFoundException e) {
         LOGGER.warn("Solution class not found.");
      }
      
      if(opt == null || !opt.isPresent()){
         LOGGER.info("Unable to create Solution instance for id:" + id + ".");
         return null;
      } else {
         return opt.get();
      }
   }
}