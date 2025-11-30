package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;
import com.spyatthehatch.objects.Equation;


/**
 * Solution for Advent of Code 2024, Day 7.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D7 extends AbstractSolution {   
	/**
	 * Constructor.
	 */
   public Solution_2024D7(){
      super();
   }
   
   /**
    * 
    */
   public String solvePartOne(){            
      long total = 0;
      List<Equation>equations = new ArrayList<Equation>();
      
      for(String s : rawList) {
         Equation eq = new Equation(s);
         equations.add(eq);
      }
      
      for(Equation eq : equations) {
         if(eq.calibrate()) {
            total += eq.getTarget();
         }
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
 
   /**
    * 
    */
   public String solvePartTwo() {
       long total = 0;
       List<Equation>equations = new ArrayList<Equation>();
       
       for(String s : rawList) {
          Equation eq = new Equation(s);
          equations.add(eq);
       }
       
       for(Equation eq : equations) {
          if(eq.calibrate()) {
             total += eq.getTarget();
          }
       }
       
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   
}