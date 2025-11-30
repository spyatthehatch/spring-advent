package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2024, Day 12.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D12 extends AbstractSolution {
   public char[][] map;
   public int width;
   public int height;
   public List<Point>history;
   public List<Region>regions;
   
	/**
	 * Constructor.
	 */
   public Solution_2024D12(){
      super();
      this.width = rawList.get(0).length();
      this.height = rawList.size();
      this.map = new char[this.width][this.height];
      
      for(int y=0; y<this.height; y++) {
         String s = this.rawList.get(y);
         
         for(int x=0; x<this.width; x++) {
            this.map[x][y] = s.charAt(x);
         }
      }
      
      this.history = new ArrayList<Point>();
      this.regions = new ArrayList<Region>();
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;

      for(int y=0; y<this.height; y++) {
         for(int x=0; x<this.width; x++) {
            Point point = new Point(x, y);
            char name = map[x][y];
            
            if(this.history.contains(point)) {
               continue;
            } else {
               Region region = new Region(name, point);
               this.history.add(point);
               region = checkNeighbors(region, point, name);
               this.regions.add(region);
            }
         }
      }
      
      for(Region region : this.regions) {
         int area = region.getArea();
         int perimeter = region.getPerimeter();
         total += (area * perimeter);
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      
      for(Region region : this.regions) {
         int area = region.getArea();
         int sides = region.getSides();
         total += (area * sides);
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public Region checkNeighbors(Region region, Point point, char name) {
      int x = point.getX();
      int y = point.getY();
      
      if(x+1 < this.width) {
         if(this.map[x+1][y] == name) {
            Point p = new Point(x+1, y);
            
            if(!history.contains(p)) {
               region.addPoint(p);
               this.history.add(p);
               region = checkNeighbors(region, p, name);
            }
         }
      }
      
      if(y+1 < this.height) {
         if(this.map[x][y+1] == name) {
            Point p = new Point(x, y+1);
            
            if(!history.contains(p)) {
               region.addPoint(p);
               this.history.add(p);
               region= checkNeighbors(region, p, name);
            }
         }
      }
      
      if(x-1 >= 0) {
         if(this.map[x-1][y] == name) {
            Point p = new Point(x-1, y);
            
            if(!history.contains(p)) {
               region.addPoint(p);
               this.history.add(p);
               region = checkNeighbors(region, p, name);
            }
         }
      }
      
      if(y-1 >= 0) {
         if(this.map[x][y-1] == name) {
            Point p = new Point(x, y-1);
            
            if(!history.contains(p)) {
               region.addPoint(p);
               this.history.add(p);
               region = checkNeighbors(region, p, name);
            }
         }
      }
      
      return region;
   }
   
   public class Region {
      public List<Point>points;
      public char name;
      
      public Region(char name, Point p) {
         this.name = name;
         this.points = new ArrayList<Point>();
         this.points.add(p);
      }
      
      public boolean contains(Point p) {
         return this.points.contains(p);
      }
      
      public void addPoint(Point p) {
         if(!this.points.contains(p)) {
            this.points.add(p);  
         }
      }
      
      public int getArea() {
         return this.points.size();
      }
      
      public int getSides() {
         int sides = 0;
         
         for(Point p : this.points) {
            int x = p.getX();
            int y = p.getY();
            
            if(!this.points.contains(new Point(x-1, y)) && !this.points.contains(new Point(x, y-1))) {
               sides++;
            }
            
            if(!this.points.contains(new Point(x+1, y)) && !this.points.contains(new Point(x, y-1))) {
               sides++;
            }
            
            if(!this.points.contains(new Point(x-1, y)) && !this.points.contains(new Point(x, y+1))) {
               sides++;
            }
            
            if(!this.points.contains(new Point(x+1, y)) && !this.points.contains(new Point(x, y+1))) {
               sides++;
            }
            
            if(this.points.contains(new Point(x-1, y)) && this.points.contains(new Point(x, y-1)) && !this.points.contains(new Point(x-1, y-1))){
               sides++;
            }
            
            if(this.points.contains(new Point(x+1, y)) && this.points.contains(new Point(x, y-1)) && !this.points.contains(new Point(x+1, y-1))){
               sides++;
            }
            
            if(this.points.contains(new Point(x-1, y)) && this.points.contains(new Point(x, y+1)) && !this.points.contains(new Point(x-1, y+1))){
               sides++;
            }
            
            if(this.points.contains(new Point(x+1, y)) && this.points.contains(new Point(x, y+1)) && !this.points.contains(new Point(x+1, y+1))){
               sides++;
            }
         }
         
         return sides;
      }
      
      public int getPerimeter() {
         int perimeter = 0;
         
         for(Point point : this.points) {
            int x = point.getX();
            int y = point.getY();

            if(x+1 < width) {
               Point p = new Point(x+1, y);
               if(!this.points.contains(p)) {
                  perimeter++;
               }
            } else {
               perimeter++;
            }
            
            if(y+1 < height) {
               Point p = new Point(x, y+1);
               if(!this.points.contains(p)) {
                  perimeter++;
               }
            } else {
               perimeter++;
            }
            
            if(x-1 >= 0) {
               Point p = new Point(x-1, y);
               if(!this.points.contains(p)) {
                  perimeter++;
               }
            } else {
               perimeter++;
            }
            
            if(y-1 >= 0) {
               Point p = new Point(x, y-1);
               if(!this.points.contains(p)) {
                  perimeter++;
               }
            } else {
               perimeter++;
            }
         }
         
         return perimeter;
      }
   }
}