package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2023, Day 2.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D2 extends AbstractSolution {
	/**
	 * Constructor.
	 */
   public Solution_2023D2(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      final int maxRed = 12;
      final int maxGreen = 13;
      final int maxBlue = 14;
      int gameNum = 1;
      int possible = 0;
      
      for(final String s : rawList){         
         String[] game = s.split(":");
         String[] pulls = game[1].split(";");
         boolean impossible = false;
         
         for(int i=0; i < pulls.length; i++){
            String[] colors = pulls[i].split(",");
            
            for(int j=0; j < colors.length; j++){
               String line = colors[j].trim();
               
               String[] pieces = line.split(" ");
               int count = Integer.valueOf(pieces[0]);
               String color = pieces[1].trim();
               
               if(color.contains("green") && count > maxGreen){
                  impossible = true;
                  break;
               }
               
               else if(color.contains("red") && count > maxRed){
                  impossible = true;
                  break;
               }
               
               else if (color.contains("blue") && count > maxBlue){
                  impossible = true;
                  break;
               }
            }
            
            if(impossible){
               break;
            }
         }
         
         if(!impossible){
            possible += gameNum;
         }
         
         gameNum++;
      }
      
      this.partOneAnswer = String.valueOf(possible);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int power = 0;
      
      for(final String s : rawList){         
         String[] game = s.split(":");
         String[] pulls = game[1].split(";");
         int minRed = 0;
         int minGreen = 0;
         int minBlue = 0;
         for(int i=0; i < pulls.length; i++){
            String[] colors = pulls[i].split(",");
            
            for(int j=0; j < colors.length; j++){
               String line = colors[j].trim();
               
               String[] pieces = line.split(" ");
               int count = Integer.valueOf(pieces[0]);
               String color = pieces[1].trim();
               
               if(color.contains("green") && count > minGreen){
                  minGreen = count;
               }
               
               else if(color.contains("red") && count > minRed){
                  minRed = count;
               }
               
               else if (color.contains("blue") && count > minBlue){
                  minBlue = count;
               }
            }
         }

         power += (minRed * minGreen * minBlue);
      }
      
      this.partTwoAnswer = String.valueOf(power);
      return this.partTwoAnswer;
   }
}