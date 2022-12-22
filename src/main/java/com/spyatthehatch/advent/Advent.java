package com.spyatthehatch.advent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spyatthehatch.puzzles.PuzzleManager;

/**
 * Advent of Code 2022 Application.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
@SpringBootApplication
public class Advent {
   /**
    * Logger.
    */
   private static final Logger LOGGER = 
      LoggerFactory.getLogger(Advent.class);

   /**
    * Main entry point for Advent 2022 application.
    * 
    * @param args Input arguments for Advent application.
    */
   public static void main(final String[] args){
      LOGGER.info("Starting Advent of Code application.");
      LOGGER.info("URL endpoint: http://127.0.0.1:8080");
      SpringApplication.run(Advent.class, args);
      
      final PuzzleManager pm = new PuzzleManager();
      
      final String latest = "2022D16";
      pm.solve(latest);
      pm.printReport(latest);
   }
}
