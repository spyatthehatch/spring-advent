package com.spyatthehatch.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Directory object for Advent of Code 2022, Day 7.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Directory {
   /**
    * The size of files in this directory only.
    */
   private int fileSize = 0;
   
   /**
    * The size of all contents within this directory.
    */
   private int totalSize = 0;
   
   /**
    * The name of this directory.
    */
   private String name = null;
   
   /**
    * A map of subdirectories.
    */
   private Map<String, Directory> subdirectories = null;
   
   /**
    * The parent directory of this directory.
    */
   private Directory parent = null;
   
   /**
    * Running sum of directories that are <= 100000.
    */
   private static int bigTotal = 0;
   
   /**
    * Constructor.
    * 
    * @param name Directory name.
    * @param parent parent Directory object.
    */
   public Directory(final String name, final Directory parent){
      this.name = name;
      this.parent = parent;
      this.subdirectories = new HashMap<String, Directory>();
   }
   
   /**
    * Get the parent Directory.
    * 
    * @return parent Directory.
    */
   public Directory getParent(){
      return this.parent;
   }
   
   /**
    * Add a file (size) to this directory.
    * 
    * @param size Size of file to add.
    */
   public void addFileSize(final int size){
      this.fileSize += size;
   }
   
   /**
    * Add a Directory as a subdirectory to this Directory object.
    * @param directory Subdirectory.
    */
   public void addSubdirectory(final Directory directory){
      this.subdirectories.put(directory.getName(), directory);
   }
   
   /**
    * Get a subdirectory by name.
    * 
    * @param name Name of subdirectory Directory object.
    * @return subdirectory Directory object, or null if it does not exist.
    */
   public Directory getSubdirectory(final String name){
      return this.subdirectories.get(name);
   }
   
   /**
    * Get the name of this Directory.
    * 
    * @return Name of this Directory.
    */
   public String getName() {
      return this.name;
   }
   
   /**
    * Get the total size of all contents (files and subdirectories).
    * 
    * @return total size of all contents.
    */
   public int getTotalSize(){
      return this.totalSize;
   }
   
   /**
    * The cumulative sum of all Directories that are < 100000 in size.
    * 
    * @return sum of all directories < 100000 in size.
    */
   public static int getBigTotal(){
      return bigTotal;
   }
   
   /**
    * Recursive method to calculate the total size of all subdirectories.  Also
    * calculates BigTotal, the cumulative sum of all Directories that are
    * < 100000 in size.
    * 
    * @return total size of this Directory object's contents (files and
    * subdirectories).
    */
   public int calcTotalSize(){
      this.totalSize = this.fileSize;
      this.subdirectories.forEach((k, v) -> this.totalSize += v.calcTotalSize());
      
      if(this.totalSize <= 100000){
         bigTotal += this.totalSize;
      }
      return this.totalSize;
   }
}