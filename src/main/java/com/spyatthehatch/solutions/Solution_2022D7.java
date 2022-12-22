package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.objects.Directory;

/**
 * Solution for Advent of Code 2022, Day 7.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D7 extends AbstractSolution {
   /**
    * Root Directory object.
    */
   private Directory root = null;
   
   /**
    * List of all Directory objects.
    */
   private List<Directory>dirList = null;
   
   /**
    * Total hard drive space, 70M.
    */
   private final int HARDDRIVE_SIZE = 70000000;
   
   /**
    * Space required by system update, 30M;
    */
   private final int REQUIRED_SPACE = 30000000;
   
   public Solution_2022D7(){
      super();
      this.root = new Directory("/", null);
      this.dirList = new ArrayList<Directory>();
      this.dirList.add(root);
   }
   
   public String solvePartOne(){
      Directory current = null;
      
      for(final String s : this.rawList){
         String[] parts = s.split(" ");
         
         if(s.equals("$ cd /")){
            current = this.root;
            continue;
         }
         
         else if(s.equals("$ ls")){
            continue;
         }
         
         else if(parts[0].equals("$")){
            if(parts[2].equals("..")){
               current = current.getParent();
               continue;
            } else {
               current = current.getSubdirectory(parts[2]);
               continue;
            }
         }
         
         else if(parts[0].equals("dir")){
            final Directory d = new Directory(parts[1], current);
            current.addSubdirectory(d);
            this.dirList.add(d);
            continue;
         }
         
         else {
            current.addFileSize(Integer.valueOf(parts[0]));
            continue;
         }
      }
      
      this.root.calcTotalSize();
      this.partOneAnswer = String.valueOf(Directory.getBigTotal());
      return getPartOneAnswer();
   }   
   
   public String solvePartTwo() {
      final int currentFreeSpace = HARDDRIVE_SIZE - this.root.getTotalSize();
      final int needFreeSpace = REQUIRED_SPACE - currentFreeSpace;
      int smallest = Integer.MAX_VALUE;      
      
      for(final Directory d : this.dirList){
         final int dirSize = d.getTotalSize();
         if(dirSize >= needFreeSpace && dirSize < smallest){
            smallest = dirSize;
         }
      }
      
      this.partTwoAnswer = String.valueOf(smallest);
      return this.partTwoAnswer;
   }
}