package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 9.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D9 extends AbstractSolution {
   public List<Integer>disk;
   
	/**
	 * Constructor.
	 */
   public Solution_2024D9(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){            
      long total = 0;

      String map = rawList.get(0);
      int length = map.length();
  
      disk = new ArrayList<Integer>();
      int id = 0;
      
      for(int i=0; i<length-1; i += 2) {
         int fileSize = Integer.valueOf(String.valueOf(map.charAt(i)));
         int emptySize = Integer.valueOf(String.valueOf(map.charAt(i+1)));
         
         for(int j=0; j<fileSize; j++) {
            disk.add(id);
         }
         
         id++;
         
         for(int j=0; j<emptySize; j++) {
            disk.add(-1);
         }
      }

      int fileSize = Integer.valueOf(String.valueOf(map.charAt(length-1)));
      
      for(int j=0; j<fileSize; j++) {
         disk.add(id);
      }
      
      List<Integer>sorted = new ArrayList<Integer>();
      int index = 0;
      int postex = disk.size() - 1;
      
      while(index <= postex) {
         if(disk.get(index) < 0) {
            while(disk.get(postex) < 0) {
               postex--;
            }
            
            sorted.add(disk.get(postex));
            postex--;
         } else {
            sorted.add(disk.get(index));
         }
         
         index++;
      }
      
      for(int i=0; i<sorted.size(); i++) {
         total += i * sorted.get(i);
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
      long total = 0;
      int[] sorted = new int[disk.size()];
      
      for(int i=0; i<disk.size(); i++) {
         sorted[i] = disk.get(i);
      }
      
      int postex = sorted.length -1;
      int id = Integer.MAX_VALUE;
      
      while(postex >= 0) {
         if(sorted[postex] > 0 && sorted[postex] < id) {
            id = sorted[postex];
            int index = postex;
            int fileSize = 1;
            
            for(int i=1; i<9; i++) {
               int testId = sorted[index-i];
               
               if(testId == id) {
                  postex--;
                  fileSize++;
               } else {
                  break;
               }
            }
            
            for(int i=0; i < postex; i++) {
               if(sorted[i] < 0) {
                  boolean acceptable = true;
                  
                  for(int j=0; j<fileSize; j++) {
                     if(sorted[i+j] >= 0) {
                        acceptable = false;
                        break;
                     }
                     
                     acceptable = true;
                  }
                  
                  if(acceptable) {                     
                     for(int j=0; j<fileSize; j++) {
                        sorted[i+j] = id;
                     }
                     
                     for(int j=0; j<fileSize; j++) {
                        sorted[postex + j] = -1;
                     }
                     
                     break;
                  }
               }
            }
         }
         
         postex--;
      }

      
      for(int i=0; i<sorted.length; i++) {
         if(sorted[i] > 0) {
            total += i * sorted[i];
         }
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}