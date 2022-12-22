package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

import com.spyatthehatch.util.ResourceReader;

/**
 * Solution for Advent of Code 2022, Day 13.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D13 extends AbstractSolution {  
   public Solution_2022D13(){
      super();
   }
   
   public static final int NOT_INTEGER = -1;
   
   public boolean complete = false;
   
   public String solvePartOne(){
      int currentIndex = 1;
      int correctIndices = 0;
      for(int i=0; i < this.rawList.size(); i += 3){
         String left = this.rawList.get(i);
         String right = this.rawList.get(i + 1);
         
         
         this.complete = false;
         
         if(compare(left, right)){
            correctIndices += currentIndex;
         }        
  
         currentIndex++;
      }

      this.partOneAnswer = String.valueOf(correctIndices);
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      String[] array;
      final ResourceReader reader = new ResourceReader(this.input);
      this.rawList = reader.toList();
      reader.close();
      
      int count = 0;
      for(String s : this.rawList){
         if(!s.isEmpty()){
            count++;
         }
      }
      
      array = new String[count + 2];
      int i = 2;
      array[0] = "[[2]]";
      array[1] = "[[6]]";
      for(String s : this.rawList){
         if(!s.isEmpty()){
            array[i] = s;
            i++;
         }
      }
      
      sort(array, array.length);
      
      int two = 0;
      int six = 0;
      
      for(i=0; i<array.length; i++){
         if(array[i].equals("[[2]]")){
            two = i;
         }
         
         if(array[i].equals("[[6]]")){
            six = i;
         }
      }
      
      int product = (two + 1) * (six + 1);
      
      this.partTwoAnswer = String.valueOf(product);
      return this.partTwoAnswer;
   }
   
   public void sort(String[] array, int n){
      if(n == 1){
         return;
      }
      
      for (int i=0; i < n-1; i++){
         if(!compare(array[i], array[i+1])){
            String temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
         }
      }
      
      sort(array, n-1);
   }
   
   private boolean compare(final String l, final String r){      
      int lint = getInt(l);
      int rint = getInt(r);
           
      if(lint != NOT_INTEGER && rint != NOT_INTEGER){


         if(lint < rint){
            this.complete = true;
            return true;
         } else if (lint == rint){
            return true;
         } else {
            this.complete = true;
            return false;
         }
      }
      
      List<String>leftComparables = splitComparables(l);
      List<String>rightComparables = splitComparables(r);

      for(int i=0; i < leftComparables.size(); i++){
         if(i >= rightComparables.size()){
            this.complete = true;
            return false;
         }
         
         if(compare(leftComparables.get(i), rightComparables.get(i))){
            if(this.complete){
               break;
            } else {
               continue;
            }
         } else {
            this.complete = true;

            return false;
         }
      }
      
      if(rightComparables.size() > leftComparables.size()){
         this.complete = true;
         return true;
      }
      
      return true;
   }
   
   private List<String> splitComparables(final String s){
      List<String>comparables = new ArrayList<String>();
      int openBrace = 0;
      int wordIndex = 0;
      
      for(int i=0; i < s.length(); i++){
         if(s.charAt(i) == '['){
            openBrace++;
            
            if(openBrace == 1){
               wordIndex++;
            }
            
            continue;
         }
      
         if(s.charAt(i) == ']'){
            openBrace--;
            
            if(openBrace == 1){
               comparables.add(s.substring(wordIndex, i + 1));
            }
            
            continue;
         }
      
         if(s.charAt(i) == ',' && openBrace <= 1){
            wordIndex = i + 1;
            continue;
         }
      
         if(i < s.length() - 1){
            if(s.charAt(i) == '1' && s.charAt(i + 1) == '0'){
               continue;
            }
         }
      
         if(i > 0 && s.charAt(i - 1) == '1' && s.charAt(i) == '0' && openBrace <= 1){
            comparables.add(s.substring(wordIndex, i + 1));
            continue;
         }
      
         if(isDigit(s.charAt(i)) && openBrace <= 1){
            comparables.add(s.substring(wordIndex, i + 1));
            continue;
         }

      }

      return comparables;
   }
   
   private boolean isDigit(final char c){
      final String integers = "0123456789";
      if(integers.indexOf(c) < 0){
         return false;
      } else {
         return true;
      }
   }
   
   private boolean isList(final String s){
      if(s.indexOf(',') < 0) {
         return false;
      } else {
         return true;
      }
   }
   
   private boolean isNestedLists(final String s){
      return (s.length() - s.replace("[","").length() > 1);    
   }
   
   private boolean isListOfOne(final String s){
      if(!isList(s) && s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']'){
         return true;
      } else {
         return false;
      }
   }
   
   private int listOfOneToInt(final String s){
      String strip = s.substring(1, s.length()-1).trim();
      if(strip.isEmpty()){
         return NOT_INTEGER;
      } else {
         return Integer.valueOf(strip);
      }
   }
   
   private int getInt(final String s){
      int i = NOT_INTEGER;
      if(!isList(s) && !isNestedLists(s)){
         if(isListOfOne(s)){
            i = listOfOneToInt(s);
         } else {
            i = Integer.valueOf(s.trim());
         }
      }
      
      return i;
   }
}