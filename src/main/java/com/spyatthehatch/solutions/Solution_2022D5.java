package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 5.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D5 extends AbstractSolution {  
   public Solution_2022D5(){
      super();
   }
   
   public String solvePartOne(){
      int row = 0;
      String[] stacks = new String[10];

      for(int i=0; i<10; i++){
         stacks[i] = "";
      }
      
      for(String s : this.rawList){
         if(s.charAt(1) != '1'){
            stacks[1] = s.charAt(1) + stacks[1].trim();
            stacks[2] = s.charAt(5) + stacks[2].trim();
            stacks[3] = s.charAt(9) + stacks[3].trim();
            stacks[4] = s.charAt(13) + stacks[4].trim();
            stacks[5] = s.charAt(17) + stacks[5].trim();
            stacks[6] = s.charAt(21) + stacks[6].trim();
            stacks[7] = s.charAt(25) + stacks[7].trim();
            stacks[8] = s.charAt(29) + stacks[8].trim();
            stacks[9] = s.charAt(33) + stacks[9].trim();
            row++;
         } else {
            row += 2;
            break;
         }
      }
      
      for (int i = row; i < this.rawList.size(); i++){
         String command[] = this.rawList.get(i).split(" ");
         int count = Integer.valueOf(command[1]);
         int source = Integer.valueOf(command[3]);
         int dest = Integer.valueOf(command[5]);
          
         for(int moves = 0; moves < count; moves++){
            int sourceLength = stacks[source].length();
            stacks[dest] = stacks[dest] + stacks[source].substring(sourceLength -1);
            stacks[source] = stacks[source].substring(0, sourceLength - 1);
         }
      }
       
      String end = "";
      for(int i=1; i < 10; i++){
         int length = stacks[i].length();
         end = end + stacks[i].substring(length-1);
      }
       
      this.partOneAnswer = end;
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      int row = 0;
      String[] stacks = new String[10];

      for(int i=0; i<10; i++){
         stacks[i] = "";
      }
      
      for(String s : this.rawList){
         if(s.charAt(1) != '1'){
            stacks[1] = s.charAt(1) + stacks[1].trim();
            stacks[2] = s.charAt(5) + stacks[2].trim();
            stacks[3] = s.charAt(9) + stacks[3].trim();
            stacks[4] = s.charAt(13) + stacks[4].trim();
            stacks[5] = s.charAt(17) + stacks[5].trim();
            stacks[6] = s.charAt(21) + stacks[6].trim();
            stacks[7] = s.charAt(25) + stacks[7].trim();
            stacks[8] = s.charAt(29) + stacks[8].trim();
            stacks[9] = s.charAt(33) + stacks[9].trim();
            row++;
         } else {
            row += 2;
            break;
         }
      }

      for (int i = row; i < this.rawList.size(); i++){
         String command[] = this.rawList.get(i).split(" ");
         int count = Integer.valueOf(command[1]);
         int source = Integer.valueOf(command[3]);
         int dest = Integer.valueOf(command[5]);
         
         
         int sourceLength = stacks[source].length();
         stacks[dest] = stacks[dest] + stacks[source].substring(sourceLength - count);
         stacks[source] = stacks[source].substring(0, sourceLength - count);
      }
      
      
      String end = "";
      for(int i=1; i < 10; i++){
         int length = stacks[i].length();
         end = end + stacks[i].substring(length-1);
      }
      
      this.partTwoAnswer = end;
      return this.partTwoAnswer;
   }
}