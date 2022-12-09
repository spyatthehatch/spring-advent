package com.spyatthehatch.solutions;

/**
 * Solution for Advent of Code 2022, Day 8.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D8 extends AbstractSolution {
   /**
    * Array of visible trees.
    */
   private boolean[][] visible;
   
   /**
    * Array of forest heights.
    */
   private int[][] forest;
   
   public Solution_2022D8(){
      super();
   }
   
   public String solvePartOne(){
      final int width = this.rawList.get(0).length();
      final int height = this.rawList.size();
      this.visible = new boolean[width][height];
      this.forest = new int[width][height];
      
      for(int y=0; y < height; y++){
         final String row = this.rawList.get(y);
         
         for(int x=0; x < width; x++){
            this.forest[x][y] = Integer.valueOf(row.charAt(x));
            this.visible[x][y] = false;
         }
      }
            
      int tallest;
      for(int x=0; x < width; x++){
         tallest = 0;
         for(int y=0; y < height; y++){
            if(this.forest[x][y] > tallest){
               this.visible[x][y] = true;
               tallest = this.forest[x][y];
            }
            
            if(tallest == 9){
               break;
            }
         }
      }
      
      for(int y=0; y < height; y++){
         tallest = 0;
         for(int x=0; x < width; x++){
            if(this.forest[x][y] > tallest){
               this.visible[x][y] = true;
               tallest = this.forest[x][y];
            }
            
            if(tallest == 9){
               break;
            }
         }
      }
      
      for(int x=0; x < width; x++){
         tallest = 0;
         for(int y = height-1; y >= 0; y--){
            if(this.forest[x][y] > tallest){
               this.visible[x][y] = true;
               tallest = this.forest[x][y];
            }
            
            if(tallest == 9){
               break;
            }
         }
      }
      
      for(int y=0; y < height; y++){
         tallest = 0;
         for(int x = width-1; x >= 0; x--){
            if(this.forest[x][y] > tallest){
               this.visible[x][y] = true;
               tallest = this.forest[x][y];
            }
            
            if(tallest == 9){
               break;
            }
         }
      }
      
      int count = 0;
      for(int y=0; y < height; y++){
         for(int x=0; x < width; x++){
            if(this.visible[x][y]){
               count++;
            }
         }
      }
      
      this.partOneAnswer = String.valueOf(count);
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      final int width = this.rawList.get(0).length();
      final int height = this.rawList.size();
      int highest = Integer.MIN_VALUE;
      
      for(int y=0; y < height; y++){
         for(int x=0; x < width; x++){
            
            int upScore = 1;  
            int leftScore = 1;
            int downScore = 1;
            int rightScore = 1;
            int score = 1;
         
         
            for(int i=1; x+i < width; i++){
               rightScore = i;
               if(forest[x+i][y] >= forest[x][y]){
                  break;
               }
            }
         
            for(int i=1; x-i >= 0; i++){
               leftScore = i;
               if(forest[x-i][y] >= forest[x][y]){
                  break;
               }
            }

            for(int j=1; y+j < height; j++){
               downScore = j;
               if(forest[x][y+j] >= forest[x][y]){
                  score *= j;
                  break;
               }
            }
         
            for(int j=1; y-j >= 0; j++){
               upScore = j;
               if(forest[x][y-j] >= forest[x][y]){
                  score *= j;
                  break;
               }
            }
         
            score = upScore * leftScore * downScore * rightScore;
            
            if(score > highest){
               highest = score;
            }
         }
      }
           
      this.partTwoAnswer = String.valueOf(highest);
      return this.partTwoAnswer;
   }
}