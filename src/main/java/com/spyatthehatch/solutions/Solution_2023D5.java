package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2023, Day 5.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D5 extends AbstractSolution {

	/**
	 * Constructor.
	 */
   public Solution_2023D5(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      List<Long>seeds = new ArrayList<Long>();
      List<List<LinkMap>>layers = new ArrayList<List<LinkMap>>();
      
      for(int i = 0; i < 8; i++) {
         layers.add(new ArrayList<LinkMap>());
      }
      
      String[] firstLine = rawList.get(0).split(":");
      String[] rawSeeds = firstLine[1].split(" ");
      
      for (int i=0; i < rawSeeds.length;  i++) {
         if(!rawSeeds[i].isEmpty()) {
            seeds.add(Long.valueOf(rawSeeds[i].trim()));
         }
      }
      
      int layerNum = 0;
      
      for (int i=3; i < rawList.size(); i++) {
         List<LinkMap>thisLayer = layers.get(layerNum);
         
         if(!rawList.get(i).isEmpty()) {
            String[] input = rawList.get(i).split(" ");
            long destRangeStart = Long.valueOf(input[0].trim());
            long sourceRangeStart = Long.valueOf(input[1].trim());
            long range = Long.valueOf(input[2].trim());
            thisLayer.add(new LinkMap(destRangeStart, sourceRangeStart, range));
         } else {
            layerNum++;
            i++;
         }  
      }

      long closestLoc = Long.MAX_VALUE;
      
      for(int i=0; i < seeds.size(); i++) {
         long loc = seeds.get(i);
         
         for(List<LinkMap> thisLayer : layers) {
            for(LinkMap map : thisLayer) {
               if(map.inRange(loc)) {
                  loc = map.getDestination(loc);
                  break;
               }
            }
         }
         
         if(loc < closestLoc) {
            closestLoc = loc;
         }
      }

      this.partOneAnswer = String.valueOf(closestLoc);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      List<SeedRange>seeds = new ArrayList<SeedRange>();
      
      List<LinkMap>soil = new ArrayList<LinkMap>();
      List<LinkMap>fertilizer = new ArrayList<LinkMap>();
      List<LinkMap>water = new ArrayList<LinkMap>();
      List<LinkMap>light = new ArrayList<LinkMap>();
      List<LinkMap>temp = new ArrayList<LinkMap>();
      List<LinkMap>humid = new ArrayList<LinkMap>();
      List<LinkMap>location = new ArrayList<LinkMap>();
      List<List<LinkMap>>layers = new ArrayList<List<LinkMap>>();
      layers.add(soil);
      layers.add(fertilizer);
      layers.add(water);
      layers.add(light);
      layers.add(temp);
      layers.add(humid);
      layers.add(location);
      
      String[] firstLine = rawList.get(0).split(":");
      String[] rawSeeds = firstLine[1].split(" ");
      
      for (int i=0; i < rawSeeds.length - 1;  i += 2) {
         if(!rawSeeds[i].isEmpty()) {
            seeds.add(new SeedRange(Long.valueOf(rawSeeds[i].trim()), Long.valueOf(rawSeeds[i + 1].trim())));
         }
      }
      
      int layerNum = 0;
      
      for (int i=3; i < rawList.size(); i++) {
         List<LinkMap> thisLayer = layers.get(layerNum);
         
         if(!rawList.get(i).isEmpty()) {
            String[] input = rawList.get(i).split(" ");
            long destRangeStart = Long.valueOf(input[0].trim());
            long sourceRangeStart = Long.valueOf(input[1].trim());
            long range = Long.valueOf(input[2].trim());
            thisLayer.add(new LinkMap(destRangeStart, sourceRangeStart, range));
         } else {
            layerNum++;
            i++;
         }  
      }
   
      
      
      
      long closestLoc = Long.MAX_VALUE;
      SeedRange closestRange = null;
      
      for(int i=0; i < seeds.size(); i++) {
         
         SeedRange seedRange = seeds.get(i);
         long loc = seedRange.sourceStart + (long)Math.sqrt((double)seedRange.getRange());
         LOGGER.info("Trying:" + loc);
         
         for(List<LinkMap> thisLayer : layers) {
            for(LinkMap map : thisLayer) {
               if(map.inRange(loc)) {
                  loc = map.getDestination(loc);
                  break;
               }
            }
         }
         
         if(loc < closestLoc) {
            closestLoc = loc;
            closestRange = seedRange;
         }
      }
      
      LOGGER.info("Attempting range: " + closestRange.sourceStart + ", " + closestRange.sourceEnd);
      
      for(long i = closestRange.sourceStart; i < closestRange.sourceStart; i++) {
         long loc = i;
         
         for(List<LinkMap> thisLayer : layers) {
            for(LinkMap map : thisLayer) {
               if(map.inRange(loc)) {
                  loc = map.getDestination(loc);
                  break;
               }
            }
         }
         
         if(loc < closestLoc) {
            closestLoc = loc;
         }
         
      }
      
      
      

      this.partTwoAnswer = String.valueOf(closestLoc);
      return this.partTwoAnswer;
   }
   
   
   public class LinkMap {     
      public long sourceStart;
      public long sourceEnd;
      public long destStart;
      public long destEnd;
      
      public LinkMap (long destRangeStart, long sourceRangeStart, long range) {
         this.destStart = destRangeStart;
         this.sourceStart = sourceRangeStart;
         this.destEnd = this.destStart + range - 1;
         this.sourceEnd = this.sourceStart + range - 1;
         
      }
      
      public long getSource(long destination) {
         long distance = destination - this.destStart;
         return this.sourceStart + distance;
      }
      
      public long getDestination(long source) {
         long distance = source - sourceStart;
         return this.destStart + distance;
      }
      
      public boolean inRange(long source) {
         if(source >= this.sourceStart && source <= this.sourceEnd) {
            return true;
         } else {
            return false;
         }
      }
   }
   
   public class SeedRange {
      public long sourceStart;
      public long sourceEnd;
      public long range;
      
      public SeedRange (long sourceStart, long range) {
         this.sourceStart = sourceStart;
         this.sourceEnd = sourceStart + range - 1;
         this.range = range;
      }
      
      public boolean inRange(long source) {
         if(source >= this.sourceStart && source <= this.sourceEnd) {
            return true;
         } else {
            return false;
         }
      }
      
      public long getRange() {
         return this.range;
      }
   }
}