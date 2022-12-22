package com.spyatthehatch.solutions;

import java.util.HashMap;
import java.util.Map;

import com.spyatthehatch.objects.Valve;

/**
 * Solution for Advent of Code 2022, Day 16.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D16 extends AbstractSolution {  
   private Map<String, Valve> map;
   
   public Solution_2022D16(){
      super();
      
      this.map = new HashMap<String, Valve>();
      
      for(String s : rawList){
         s = s.replace('=', ' ');
         s = s.replace(";", " ");
         final String[] words = s.split(" ");
         this.map.put(words[1], new Valve(words[1],Integer.valueOf(words[5])));
         LOGGER.info("Created valve:" + words[1] + ", flow:" + words[5]);
      }
      
      for(String s : rawList){
         s = s.replace(',', ' ');
         s = s.replaceAll("  ", " ");
         String[] words = s.split(" ");
         Valve valve = this.map.get(words[1]);
         
         for(int i = 9; i < words.length; i++){
            String name = words[i].trim();
            LOGGER.info("Grabbing valve name:" + name);
            Valve v = this.map.get(words[i].trim());
            LOGGER.info("Adding..." + v.toString());
            valve.addValve(v);
         }
         
         LOGGER.info(valve.toString());
      }
   }
   
   public String solvePartOne(){
      int minutes = 30;
      Valve current = this.map.get("AA");
      
      
      
      
      
      
      return getPartOneAnswer();
   }   
   
   public String solvePartTwo() {
      
      
      
      
      
      
      
      
      return getPartTwoAnswer();
   }
}