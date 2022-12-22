package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.Point;

/**
 * Solution for Advent of Code 2022, Day 14.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D14 extends AbstractSolution {
   
   
   private int maxX = Integer.MIN_VALUE;
   
   private int maxY = Integer.MIN_VALUE;
   
   private static final boolean SOLID = true;

   private boolean[][] map;
   
   public Solution_2022D14(){
      super();
   }
   
   public String solvePartOne(){
      this.map = scanRock(false);
      int count = 0;

      while(dropSand()){
         count++;
      }
      
      this.partOneAnswer = String.valueOf(count);
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      this.map = scanRock(true);
      int count = 0;
      
      while(!map[500][0]){
         dropSand();
         count++;
      }
       
      this.partTwoAnswer = String.valueOf(count);
      return this.partTwoAnswer;
   }
   
   private boolean dropSand(){
      int x = 500;
      int y = 0;
      boolean changed = false;
      
      while(true){         
         if(y + 1 > this.maxY){
            break;
         } else if (map[x][y + 1] != SOLID){
            y++;
            continue;
         } else if (x - 1 < 0){
            break;
         } else if (map[x - 1][y + 1] != SOLID){
            x--;
            y++;
            continue;
         } else if (x + 1 > maxX){
            break;
         } else if (map[x + 1][y + 1] != SOLID){
            x++;
            y++;
            continue;
         } else if (map[x][y + 1] == SOLID && map[x - 1][y + 1] == SOLID
            && map[x + 1][y + 1] == SOLID){
            
            map[x][y] = SOLID;
            changed = true;
            break;
         }
      }
      
      return changed;
   }
   
   public boolean[][] scanRock(final boolean addFloor){     
      for(final String s : this.rawList){
         String[] points = s.split(" -> ");
         for(int i=0; i < points.length; i++){
            String[] coords = points[i].split(",");
            int x = Integer.valueOf(coords[0]);
            int y = Integer.valueOf(coords[1]);
            
            if(x > this.maxX){
               this.maxX = x;
            }
            
            if(y > this.maxY){
               this.maxY = y;
            }
         }
      }
      
      if(addFloor){
         maxY += 2;
         maxX += maxY;
      }
      
      boolean[][] map = new boolean[this.maxX + 1][this.maxY + 1];
      
      for(int y=0; y < this.maxY; y++){
         for(int x=0; x < this.maxX; x++){
            map[x][y] = !SOLID;
         }
      }
      
      for(final String s : this.rawList){
         String[] points = s.split(" -> ");
         List<Point>pointList = new ArrayList<Point>();
         
         for(int i=0; i < points.length; i++){
            String[] coords = points[i].split(",");
            int x = Integer.valueOf(coords[0]);
            int y = Integer.valueOf(coords[1]);
            pointList.add(new Point(x, y));
         }

         for(int i=0; i < pointList.size() - 1; i++){
            map = drawLine(map, pointList.get(i), pointList.get(i + 1));
         }
      }
      
      if(addFloor){
         map = drawLine(map, new Point(0, maxY), new Point (maxX, maxY));
      }
      
      return map;
   }
   
   public boolean[][] drawLine(boolean[][] map, final Point from, final Point to){
      int x = from.getX();
      int y = from.getY();
            
      if(from.getX() == to.getX()){         
         if(from.getY() > to.getY()){
            while(y >= to.getY()){
               map[x][y] = SOLID;
               y--;
            }
         } else {
            while(y <= to.getY()){
               map[x][y] = SOLID;
               y++;
            }
         }
      } else {
         if(from.getX() > to.getX()){
            while(x >= to.getX()){
               map[x][y] = SOLID;
               x--;
            }
         } else {
            while(x <= to.getX()){
               map[x][y] = SOLID;
               x++;
            }
         }
      }

      return map;
   }
}