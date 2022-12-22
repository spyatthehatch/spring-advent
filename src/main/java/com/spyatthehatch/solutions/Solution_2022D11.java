package com.spyatthehatch.solutions;

import java.util.HashMap;

import com.spyatthehatch.objects.Monkey;

/**
 * Solution for Advent of Code 2022, Day 11.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Solution_2022D11 extends AbstractSolution {
   /**
    * HashMap of Monkey objects, sorted by number/name as key.
    */
   private HashMap<Integer, Monkey> monkeys;
     
   public Solution_2022D11(){
      super();
   }
   
   public String solvePartOne(){
      this.monkeys = parseInput();
      final boolean useReduction = true;
      performRounds(20, useReduction);

      this.partOneAnswer = String.valueOf(calcMonkeyBusiness());
      return this.partOneAnswer;
   }   
   
   public String solvePartTwo() {
      this.monkeys = parseInput();
      final boolean noReduction = false;
      performRounds(10000, noReduction);
      
      this.partTwoAnswer = String.valueOf(calcMonkeyBusiness());
      return this.partTwoAnswer;
   }
   
   /**
    * Parse the input to build the initial state.
    * 
    * @return HashMap of Monkey objects, sorted by number/name as key.
    */
   public HashMap<Integer, Monkey> parseInput(){
      Monkey.resetCommonMultiple();
      final HashMap<Integer, Monkey>monkeys = new HashMap<Integer, Monkey>();
      int monkeyCount = 0;
      
      for (final String s : this.rawList){
         if(!s.isEmpty()){
            String[] words = s.split(" ");
            if(words[0].equals("Monkey")){
               monkeyCount++;
            }
         }
      }
      
      for(int i=0; i<monkeyCount; i++){
         monkeys.put(i, new Monkey(i));
      }
      
      int monkeyNum = 0;
      Monkey monkey = monkeys.get(monkeyNum);
      
      for (final String s : this.rawList){
         if(s.isEmpty()){
            monkeyNum++;
            monkey = monkeys.get(monkeyNum);
            continue;
         }
         
         final String[] words = s.trim().split(" ");
         
         if(words[0].equals("Monkey")){
            continue;
         }
         
         if(words[0].equals("Starting")){
            final String[] line = s.split(":");
            final String list = line[1].trim().replace(",", "");
            final String[] items = list.split(" ");
            
            for(int i=0; i < items.length; i++){
               monkey.addItem(Integer.valueOf(items[i]));
            }
            
            continue;
         }
            
         if(words[0].equals("Operation:")){
            monkey.setOperation(words[4] + " " + words[5]);
            continue;
         }
         
         if(words[0].equals("Test:")){
            monkey.setDivisor(Integer.valueOf(words[3]));
            continue;
         }
         
         if(words[1].equals("true:")){
            monkey.setTrueMonkey(monkeys.get(Integer.valueOf(words[5])));
            continue;
         }
         
         if(words[1].equals("false:")){
            monkey.setFalseMonkey(monkeys.get(Integer.valueOf(words[5])));
            continue;
         }
      }

      return monkeys;
   }
   
   /**
    * Perform the desired number of rounds for Monkey inspection.
    * 
    * @param rounds Rounds for Monkey objects to perform inspection.
    * @param reduce Boolean to note use of worry reduction (divide by three).
    * True to use worry reduction (divide by three), false to use Least Common
    * Multiple.  
    */
   public void performRounds(final int rounds, final boolean reduce){
      for(int i=0; i < rounds; i++){
         for(int m=0; m < this.monkeys.size(); m++){
            this.monkeys.get(m).inspect(reduce);
         }
      }
   }
   
   /**
    * Determine the total monkey business (multiplying top two Monkey
    * inspections).
    * 
    * @return Total monkey business (Product of top two Monkey inspections).
    */
   public long calcMonkeyBusiness(){
      long first = Long.MIN_VALUE;
      long second = Long.MIN_VALUE;

      for(int m=0; m < this.monkeys.size(); m++){
         long count = this.monkeys.get(m).getCount();
         
         if(count > first){
            second = first;
            first = count;
         } else if(count > second){
            second = count;
         }
      }
      
      return first * second;
   }
}