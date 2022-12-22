package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 10.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D10 extends AbstractSolution {
   /**
    * Width of CRT, 40.
    */
   private final int WIDTH = 40;
   
   /**
    * HEIGHT of CRT, 6.
    */
   private final int HEIGHT = 6;
   
   public Solution_2022D10(){
      super();
   }
   
   public String solvePartOne(){
      int register = 1;
      int clock = 1;
      int i = 0;
      int[] values = new int[HEIGHT];
      
      for(String s : this.rawList){
         if(s.equals("noop")){
            clock++;
         } else {
            int mag = Integer.valueOf(s.split(" ")[1]);
            clock++;
            
            if(checkClock(clock)){
               values[i] = register * clock;
               i++;
            }
            
            clock++;
            register += mag;
         }
         
         if(checkClock(clock)){

            values[i] = register * clock;
            i++;
         }
      }
      
      int total = 0;
      for(int j=0; j < HEIGHT; j++){
         total += values[j];
      }
      
      this.partOneAnswer = String.valueOf(total); 
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      int register = 1;
      int clock = 1;
      char[][] crt = new char[HEIGHT][WIDTH];     

      crt[(clock - 1) / WIDTH][(clock - 1) % WIDTH] = isVisible(clock, register);
      
      for(String s : this.rawList){
         if(s.equals("noop")){
            clock++;
            
            if(clock > 240){
               break;
            }
           
            crt[(clock - 1) / WIDTH][(clock - 1) % WIDTH] = isVisible(clock, register);
            
         } else {
            int mag = Integer.valueOf(s.split(" ")[1]);
            clock++;
            
            if(clock > 240){
               break;
            }

            crt[(clock - 1) / WIDTH][(clock - 1) % WIDTH] = isVisible(clock, register);
      
            clock++;
            if(clock > 240){
               break;
            }

            register += mag;
         }
         
         crt[(clock - 1) / WIDTH][(clock - 1) % WIDTH] = isVisible(clock, register);
      }
      
      printCrt(crt);
      this.partTwoAnswer = "See CRT output.";
      return this.partTwoAnswer;
   }
   
   /**
    * Check the clock for the 20th, 60the, 100th, 140th, 180th, and 220th
    * ticks.
    *  
    * @param clock Clock ticks.
    * @return True if this is the 20th, 60the, 100th, 140th, 180th, or
    * 220th tick of the clock.  False, otherwise.
    */
   private boolean checkClock(final int clock){
      switch(clock){
         case 20:
         case 60:
         case 100:
         case 140:
         case 180:
         case 220:
            return true;
         default:
            return false;
      }
   }
   
   /**
    * Determine it a spite is visible given the current clock tick.  The sprite
    * is lit as character '#' or dark as '.'.
    * 
    * @param clock Tick of the clock.
    * @param sprite Sprite location.
    * @return Lit as '#' or dark as '.'.
    */
   private char isVisible(int clock, int sprite){
      final char LIT = '#';
      final char DARK = '.';
      int column = (clock - 1) % WIDTH;
      
      if(column == (sprite - 1) || column == sprite || column == (sprite + 1)){
         return LIT;
      } else {
         return DARK;
      }
   }
   
   /**
    * 
    * @param crt
    */
   private void printCrt(char[][] crt){
      for(int i=0; i < HEIGHT; i++){
         LOGGER.info(String.valueOf(crt[i]));
      }
   }
}