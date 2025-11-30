package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution for Advent of Code 2024, Day 11.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D11 extends AbstractSolution {
	/**
	 * Constructor.
	 */
   public Solution_2024D11(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){            
      List<Long> stones = new ArrayList<Long>();
      final int BLINKS = 25;

      for(String s : rawList) {
         String[] line = s.split(" ");
         
         for(int i=0; i<line.length; i++) {
            stones.add(Long.valueOf(line[i]));
         }
      }
      
      List<Long>next = null;
      
      for(int i=1; i<=BLINKS; i++) {
         next = new ArrayList<Long>();
         
         for(Long stone : stones) {
            if(stone == 0) {
               next.add(1L);
            }
            
            else if(Long.toString(stone).length() % 2 == 0) {
               String s = Long.toString(stone);
               int length = s.length();
               
               long left = Integer.valueOf(s.substring(0, length/2));
               long right = Integer.valueOf(s.substring(length/2, length));
               next.add(left);
               next.add(right);
            }
            
            else {
               next.add(stone * 2024);
            }
         }
         
         stones = next;
      }

      this.partOneAnswer = String.valueOf(stones.size());
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      Map<Long, Stone> stones = new HashMap<Long, Stone>();
      final int BLINKS = 75;

      for(String s : rawList) {
         String[] line = s.split(" ");
         
         for(int i=0; i<line.length; i++) {
            long value = Long.valueOf(line[i]);
            if(stones.containsKey(value))
               stones.get(value).count++;
            else {
               stones.put(value, new Stone(value));
            }
         }
      }
      
      Map<Long, Stone>next = null;
      
      for(int i=1; i<=BLINKS; i++) {
         next = new HashMap<Long, Stone>();
         
         for(Stone stone : stones.values()) {
            int length = Long.toString(stone.value).length();
            
            if(stone.value == 0L) {
               Stone s = new Stone(1L);
               s.count = stone.count;
               next.put(1L, s);
            }
            
            else if(length % 2 == 0) {
               String value = Long.toString(stone.value);
               
               long left = Long.valueOf(value.substring(0, length/2));
               long right = Long.valueOf(value.substring(length/2, length));
               
               if(next.containsKey(left)) {
                  Stone l = next.get(left);
                  l.count += stone.count;
               } else {
                  Stone l = new Stone(left);
                  l.count = stone.count;
                  next.put(left, l);
               }

               if(next.containsKey(right)) {
                  Stone r = next.get(right);
                  r.count += stone.count;
               } else {
                  Stone r = new Stone(right);
                  r.count = stone.count;
                  next.put(right, r);
               }
            }
            
            else {
               Stone s = new Stone(stone.value * 2024);
               s.count = stone.count;
               next.put(s.value, s);
            }
         }
         
         stones = next;
      }
      
      long size = 0L;
      for(Stone stone : stones.values()) {
         size += stone.count;
      }
      
      
      this.partTwoAnswer = String.valueOf(size);
      return this.partTwoAnswer;
   }
   
   public class Stone {
      public long value;
      public long count;
      
      public Stone (long value) {
         this.value = value;
         count = 1L;
      }
   }
}