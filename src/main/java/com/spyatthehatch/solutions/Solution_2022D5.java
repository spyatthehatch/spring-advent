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
      final String[] stacks = initStacks();
      
      for (int i = getStartRow(); i < this.rawList.size(); i++){
         String command[] = this.rawList.get(i).split(" ");
         int count = Integer.valueOf(command[1]);
         int source = Integer.valueOf(command[3]) - 1;
         int dest = Integer.valueOf(command[5]) - 1;
          
         for(int moves = 0; moves < count; moves++){
            int sourceLength = stacks[source].length();
            stacks[dest] = stacks[dest] + stacks[source].substring(sourceLength - 1);
            stacks[source] = stacks[source].substring(0, sourceLength - 1);
         }
      }
       
      this.partOneAnswer = getStackTops(stacks);
      return this.partOneAnswer;
   }   
      
   public String solvePartTwo() {
      final String[] stacks = initStacks();
      
      for (int i = getStartRow(); i < this.rawList.size(); i++){
         String command[] = this.rawList.get(i).split(" ");
         int count = Integer.valueOf(command[1]);
         int source = Integer.valueOf(command[3]) - 1;
         int dest = Integer.valueOf(command[5]) - 1;
         
         
         int sourceLength = stacks[source].length();
         stacks[dest] = stacks[dest] + stacks[source].substring(sourceLength - count);
         stacks[source] = stacks[source].substring(0, sourceLength - count);
      }
      
      this.partTwoAnswer = getStackTops(stacks);
      return this.partTwoAnswer;
   }
   
   /**
    * Initialize the stacks per the puzzle input.
    * 
    * @return String array of stacks.
    */
   private String[] initStacks(){
      final int STACK_NUM = 9;
      final String[] stacks = new String[STACK_NUM];
      
      for(int i=0; i < stacks.length; i++){
         stacks[i] = new String();
      }
      
      for(String s : this.rawList){
         if(s.charAt(1) != '1'){
            stacks[0] = s.charAt(1) + stacks[0].trim();
            stacks[1] = s.charAt(5) + stacks[1].trim();
            stacks[2] = s.charAt(9) + stacks[2].trim();
            stacks[3] = s.charAt(13) + stacks[3].trim();
            stacks[4] = s.charAt(17) + stacks[4].trim();
            stacks[5] = s.charAt(21) + stacks[5].trim();
            stacks[6] = s.charAt(25) + stacks[6].trim();
            stacks[7] = s.charAt(29) + stacks[7].trim();
            stacks[8] = s.charAt(33) + stacks[8].trim();
         } else {
            break;
         }
      }
      
      return stacks;
   }
   
   /**
    * Find the empty String in the input to determine where the initial state
    * ends and the instructions begin.
    * 
    * @return Row that instructions begin in the puzzle input.
    */
   private int getStartRow(){
      int row = 0;
      
      for(String s : this.rawList){
         row++;
         if(s.isEmpty()){
            break;
         }
      }
      
      return row;
   }
   
   /**
    * Get the top crate of each stack.
    * 
    * @param stacks Array of stacks.
    * @return String of the top crate values.
    */
   private String getStackTops(final String[] stacks){
      String end = new String();
      for(int i=0; i < stacks.length; i++){
         int length = stacks[i].length();
         end = end + stacks[i].substring(length - 1);
      }
      
      return end;
   }
}