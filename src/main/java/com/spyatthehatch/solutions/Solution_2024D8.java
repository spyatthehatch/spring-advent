package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2024, Day 8.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D8 extends AbstractSolution {
   List<Point> antinodes;
	/**
	 * Constructor.
	 */
   public Solution_2024D8(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){            
      antinodes = new ArrayList<Point>();
      List<Character> frequencies = new ArrayList<Character>();
      Map<Character, Antenna> antennas = new HashMap<Character, Antenna>();
      
      int width = rawList.get(0).length();
      int height = rawList.size();
      
      for(int y = 0; y < height; y++) {
         String s = rawList.get(y);
         
         for(int x = 0; x < width; x++) {
            char c = s.charAt(x);
            
            if(c != '.') {
               if(antennas.containsKey(c)) {
                  Antenna a = antennas.get(c);
                  a.addLocation(new Point(x, y));
               } else {
                  Antenna a = new Antenna(c);
                  a.addLocation(new Point(x, y));
                  antennas.put(c, a);
                  frequencies.add(c);
               }
            }
         }
      }
      
      for(char freq : frequencies) {
         Antenna a = antennas.get(freq);
         
         for(int i=0; i < a.locations.size(); i++) {
            Point p = a.locations.get(i);
            
            for(int j=0; j < a.locations.size(); j++) {
               Point q = a.locations.get(j);
               if(p.equals(q)) {
                  continue;
               } else {
                  int xOrientation = p.getX() - q.getX();
                  int yOrientation = p.getY() - q.getY();
                  int hDist = Math.abs(xOrientation);
                  int vDist = Math.abs(yOrientation);
                  
                  int aX = 0;
                  int aY = 0;
                  int bX = 0;
                  int bY = 0;
                  
                  // P is right and down of Q.
                  if (xOrientation >= 0 && yOrientation >= 0) {
                     aX = p.getX() + hDist;
                     aY = p.getY() + vDist;
                     bX = q.getX() - hDist;
                     bY = q.getY() - vDist;
                  }
                  
                  // P right and up of Q.
                  if (xOrientation >= 0 && yOrientation <= 0) {
                     aX = p.getX() + hDist;
                     aY = p.getY() - vDist;
                     bX = q.getX() - hDist;
                     bY = q.getY() + vDist;
                  }
                  
                  // P is left and down of Q.
                  if (xOrientation <= 0 && yOrientation >= 0) {
                     aX = p.getX() - hDist;
                     aY = p.getY() + vDist;
                     bX = q.getX() + hDist;
                     bY = q.getY() - vDist;
                  }
                  
                  // P is left and up of Q.
                  if (xOrientation <= 0 && yOrientation <= 0) {
                     aX = p.getX() - hDist;
                     aY = p.getY() - vDist;
                     bX = q.getX() + hDist;
                     bY = q.getY() + vDist;
                  }
                  
                  Point aAntinode = new Point(aX, aY);
                  Point bAntinode = new Point(bX, bY);
                  
                  if(aX >= 0 && aX < width && aY >= 0 && aY < height && !antinodes.contains(aAntinode)) {
                     antinodes.add(aAntinode);
                  }
                  
                  if(bX >= 0 && bX < width && bY >= 0 && bY < height && !antinodes.contains(bAntinode)) {
                     antinodes.add(bAntinode);
                  }
               }
            }
         }
      }
            
      this.partOneAnswer = String.valueOf(antinodes.size());
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      antinodes = new ArrayList<Point>();
      List<Character> frequencies = new ArrayList<Character>();
      Map<Character, Antenna> antennas = new HashMap<Character, Antenna>();
      
      int width = rawList.get(0).length();
      int height = rawList.size();
      
      for(int y = 0; y < height; y++) {
         String s = rawList.get(y);
         
         for(int x = 0; x < width; x++) {
            char c = s.charAt(x);
            
            if(c != '.') {
               if(antennas.containsKey(c)) {
                  Antenna a = antennas.get(c);
                  a.addLocation(new Point(x, y));
               } else {
                  Antenna a = new Antenna(c);
                  a.addLocation(new Point(x, y));
                  antennas.put(c, a);
                  frequencies.add(c);
               }
            }
         }
      }
      
      for(char freq : frequencies) {
         Antenna a = antennas.get(freq);
         
         for(int i=0; i < a.locations.size(); i++) {
            Point p = a.locations.get(i);
            
            for(int j=0; j < a.locations.size(); j++) {
               Point q = a.locations.get(j);
               if(p.equals(q)) {
                  continue;
               } else {
                  boolean newPoint = true;
                  int multiplier = 1;
                  
                  while(newPoint) {
                     newPoint = resonate(p, q, multiplier);
                     if(newPoint) {
                        multiplier++;
                     }
                  }
                  
                  newPoint = true;
                  multiplier = -1;
                  
                  while(newPoint) {
                     newPoint = resonate(p, q, multiplier);
                     if(newPoint) {
                        multiplier--;
                     }
                  }
               }
            }
         }
      }
 
      for(Point p : antinodes) {
         LOGGER.trace("Antinode:" + p.toString());
      }
      
      this.partTwoAnswer = String.valueOf(antinodes.size());
      return this.partTwoAnswer;
   }
   
   public boolean resonate(Point p, Point q, int multiplier) {
      int width = rawList.get(0).length();
      int height = rawList.size();
      
      int xOrientation = p.getX() - q.getX();
      int yOrientation = p.getY() - q.getY();
      int hDist = Math.abs(xOrientation);
      int vDist = Math.abs(yOrientation);
      
      int aX = 0;
      int aY = 0;
      int bX = 0;
      int bY = 0;
      
      // P is right and down of Q.
      if (xOrientation >= 0 && yOrientation >= 0) {
         aX = p.getX() + (multiplier * hDist);
         aY = p.getY() + (multiplier * vDist);
         bX = q.getX() - (multiplier * hDist);
         bY = q.getY() - (multiplier * vDist);
      }
      
      // P right and up of Q.
      if (xOrientation >= 0 && yOrientation <= 0) {
         aX = p.getX() + (multiplier * hDist);
         aY = p.getY() - (multiplier * vDist);
         bX = q.getX() - (multiplier * hDist);
         bY = q.getY() + (multiplier * vDist);
      }
      
      // P is left and down of Q.
      if (xOrientation <= 0 && yOrientation >= 0) {
         aX = p.getX() - (multiplier * hDist);
         aY = p.getY() + (multiplier * vDist);
         bX = q.getX() + (multiplier * hDist);
         bY = q.getY() - (multiplier * vDist);
      }
      
      // P is left and up of Q.
      if (xOrientation <= 0 && yOrientation <= 0) {
         aX = p.getX() - (multiplier * hDist);
         aY = p.getY() - (multiplier * vDist);
         bX = q.getX() + (multiplier * hDist);
         bY = q.getY() + (multiplier * vDist);
      }
      
      Point aAntinode = new Point(aX, aY);
      Point bAntinode = new Point(bX, bY);
      
      boolean found = false;
      
      if(aX >= 0 && aX < width && aY >= 0 && aY < height) {
         found = true;
         if(!antinodes.contains(aAntinode)) {
            antinodes.add(aAntinode);
         }
      }
      
      if(bX >= 0 && bX < width && bY >= 0 && bY < height) {
         found = true;
         if(!antinodes.contains(bAntinode)) {
            antinodes.add(bAntinode);
         }
      }
      
      return found;
   }
   
   
   
   public class Antenna {
      public char frequency;
      public List<Point> locations;
      
      public Antenna (char freq){
         this.frequency = freq;
         this.locations = new ArrayList<Point>();
      }
      
      public void addLocation(Point p) {
         this.locations.add(p);
      }
   }
}