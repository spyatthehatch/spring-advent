package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solution for Advent of Code 2024, Day 2.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D3 extends AbstractSolution {
	/**
	 * Constructor.
	 */
   public Solution_2024D3(){
      super();
   }

   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
            
      for(final String s : rawList){  
         Pattern pattern = Pattern.compile("mul\\([0-9]*,[0-9]*\\)");
         Matcher matcher = pattern.matcher(s);
         
         while(matcher.find()) {
            total += parseOp(matcher.group());
         }
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      int total = 0;
      boolean doOp = true;
      
      for(final String s : rawList) {
         List<Operation> ops = new ArrayList<Operation>();
         
         Pattern pattern = Pattern.compile("mul\\([0-9]*,[0-9]*\\)");
         Matcher matcher = pattern.matcher(s);
         
         while(matcher.find()) {
            Operation o = new Operation(matcher.start(), matcher.group());
            ops.add(o);
         }
         
         pattern = Pattern.compile("do\\(\\)");
         matcher = pattern.matcher(s);
         
         while(matcher.find()) {
            Operation o = new Operation(matcher.start(), matcher.group());
            ops.add(o);
            
         }
         
         pattern = Pattern.compile("don\\'t\\(\\)");
         matcher = pattern.matcher(s);
         
         while(matcher.find()) {
            Operation o = new Operation(matcher.start(), matcher.group());
            ops.add(o);
         }
         
         ops.sort(new OperationComparator());
         
         for(int i=0; i < ops.size(); i++) {
            String op = ops.get(i).op;
            
            if(op.equals("do()")) {
               doOp = true;
            }
            
            else if(op.equals("don't()")){
               doOp = false;
            }
            
            else if(doOp) {
               total += parseOp(op);
            }
         }
      }

      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   /**
    * 
    * @param op
    * @return
    */
   public int parseOp(String op) {
      int open = op.indexOf("(");
      int comma = op.indexOf(",");
      int close = op.indexOf(")");
      int num1 = Integer.valueOf(op.substring(open + 1,comma));
      int num2 = Integer.valueOf(op.substring(comma + 1,close));
      return num1 * num2;
   }
   
   /**
    * 
    */
   public class Operation {
      Operation(int index, String op){
         this.index = index;
         this.op = op;
      }
      
      public int index;
      public String op;
      
      public String toString() {
         return "Operation(" + this.index + ", " + this.op + ")";
      }
   }
   
   /**
    * 
    */
   public class OperationComparator implements Comparator<Operation> {
      @Override
      public int compare(Operation a, Operation b) {
         return a.index - b.index;
      }
   }
}