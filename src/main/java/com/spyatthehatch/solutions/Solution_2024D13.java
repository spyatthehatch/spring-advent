package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 13.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D13 extends AbstractSolution {
   public List<Prize>prizes;
   
	/**
	 * Constructor.
	 */
   public Solution_2024D13(){
      super();
      this.prizes = new ArrayList<Prize>();

      for(int i=0; i<rawList.size(); i += 4) {
         String aLine = rawList.get(i);
         String[] s = aLine.split(",");
         String[] aXString = s[0].split("\\+");
         String[] aYString = s[1].split("\\+");
         int aX = Integer.valueOf(aXString[1]);
         int aY = Integer.valueOf(aYString[1]);
         
         String bLine = rawList.get(i+1);
         s = bLine.split(",");
         String[] bXString = s[0].split("\\+");
         String[] bYString = s[1].split("\\+");
         int bX = Integer.valueOf(bXString[1]);
         int bY = Integer.valueOf(bYString[1]);
         
         String xLine = rawList.get(i+2);
         s = xLine.split(",");
         String[] xXString = s[0].split("=");
         String[] xYString = s[1].split("=");
         int x = Integer.valueOf(xXString[1]);
         int y = Integer.valueOf(xYString[1]);
         
         Prize p = new Prize(aX, aY, bX, bY, x, y);
         prizes.add(p);
      }
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      long total = 0;

      for(Prize prize : prizes) {
         long a = prize.calcA();
         long b = prize.calcB(a);

         if(a >= 0 && b >= 0 && a <= 100 && b <= 100) {                 
            total += (3 * a) + b;
         }
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      long total = 0;

      for(Prize prize : this.prizes) {
         prize.x += 10000000000000L;
         prize.y += 10000000000000L;
      }
      
      for(Prize price : prizes) {
         long a = price.calcA();
         long b = price.calcB(a);
         
         if(a >= 0 && b >= 0) {

            total += (3 * a) + b;
         }
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public class Prize {
      public int aX;
      public int aY;
      public int bX;
      public int bY;
      public long x;
      public long y;
      
      public Prize(int aX, int aY, int bX, int bY, int x, int y) {
         this.aX = aX;
         this.aY = aY;
         this.bX = bX;
         this.bY = bY;
         this.x = x;
         this.y = y;
      }
      
      public String toString() {
         return "aX:" + this.aX + ", aY:" + this.aY + ", bX:" + this.bX + ", bY:" + this.bY + ", x:" + this.x + ", y:" + y;
      }
      
      public long calcA() {
         if(((this.bX * this.y) - (this.bY * this.x))%((this.bX * this.aY) - (this.bY * this.aX)) == 0) {
            return ((this.bX * this.y) - (this.bY * this.x))/((this.bX * this.aY) - (this.bY * this.aX));
         } else {
            return -1;
         }
      }
      
      public long calcB(long a) {
         if((this.y - (a * this.aY))%this.bY == 0) {
            return (this.y - (a * this.aY))/this.bY;
         } else {
            return -1;
         }
         
      }
      
      public long getCost() {
         long least = Long.MAX_VALUE;
         long cost = 0;
         int aCost = 3;
         int bCost = 1;
         
         for(int aPress=0; aPress<=100; aPress++) {

            long remainX = this.x - (aPress * this.aX);
            long remainY = this.y - (aPress * this.aY);
            
            long multipleBx = remainX / this.bX;
            long modBx = remainX % this.bX;
            long multipleBy = remainY / this.bY;
            long modBy = remainY % this.bY;
            
            if(multipleBx == multipleBy && modBx == 0 && modBy == 0) {
               cost = (aPress * aCost) + (multipleBx * bCost);
               if(cost < least && cost > 0) {
                  least = cost;
               }
               
            }
            
         }
         
         if(least != Long.MAX_VALUE) {
            return least;
         } else {
            return 0;
         }
      }
   }
}