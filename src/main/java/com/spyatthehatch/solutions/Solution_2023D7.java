package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.spyatthehatch.objects.CamelHand;

/**
 * Solution for Advent of Code 2023, Day 7.
 * 
 * @author Bill Everton
 * @version Advent 2023
 */
public class Solution_2023D7 extends AbstractSolution {
//   private List<CamelHand> hands = new ArrayList<CamelHand>();

	/**
	 * Constructor.
	 */
   public Solution_2023D7(){
      super();
      
   }

   /**
    * 
    */
   public String solvePartOne(){
      long total = 0L;
      final List<CamelHand> hands = new ArrayList<CamelHand>();
      
      for (final String s : rawList) {
         String[] parts = s.split(" ");
         final CamelHand ch = new CamelHand(parts[0].trim(), Integer.valueOf(parts[1].trim()));
         ch.setType();
         hands.add(ch);
      }
      
      Collections.sort(hands);
      
      int rank = hands.size();
      for (CamelHand ch : hands) {
         total += ch.getBid() * rank;
         rank--;
      }
      
      this.partOneAnswer = String.valueOf(total);
      return this.partOneAnswer;
   }
   
   /**
    * 
    */
   public String solvePartTwo() {
      long total = 0L;
      final List<CamelHand> hands = new ArrayList<CamelHand>();
      
      for (final String s : rawList) {
         String[] parts = s.split(" ");
         final CamelHand ch = new CamelHand(parts[0].trim(), Integer.valueOf(parts[1].trim()));
         ch.setTypeWithJokers();
         hands.add(ch);
      }
      
      Collections.sort(hands);
      
      int rank = hands.size();
      for (CamelHand ch : hands) {
         total += ch.getBid() * rank;
         rank--;
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
}