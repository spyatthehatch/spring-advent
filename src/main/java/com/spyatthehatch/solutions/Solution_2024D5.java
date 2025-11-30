package com.spyatthehatch.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for Advent of Code 2024, Day 5.
 * 
 * @author Bill Everton
 * @version Advent 2024
 */
public class Solution_2024D5 extends AbstractSolution {
   private List<Rule> rules;
         
   private List<List<Page>> incorrect;
   
	/**
	 * Constructor.
	 */
   public Solution_2024D5(){
      super();
      this.rules = new ArrayList<Rule>();
      this.incorrect = new ArrayList<List<Page>>();
   }
   
   /**
    * 
    */
   public String solvePartOne(){
      int total = 0;
      
      List<List<Page>> updates = new ArrayList<List<Page>>();
      boolean readRules = true;
      
      for(String s : rawList) {
         if(s.isEmpty()) {
            readRules = false;
            continue;
         }
         
         if(readRules) {
            String[] line = s.split("\\|");
            Rule rule = new Rule(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
            this.rules.add(rule);
         } else {
            List<Page> update = new ArrayList<Page>();
            
            String[] line = s.split(",");
            
            for(int i=0; i<line.length; i++) {
               int p = Integer.valueOf(line[i].trim());
               List<Rule> myRules = new ArrayList<Rule>();
               
               for(Rule r : this.rules) {
                  if(r.x == p) {
                     myRules.add(r);
                  }
               }
               
               Page page = new Page(p, myRules);
               update.add(page);
            }
            
            updates.add(update);
            
         }
      }
      
      for(List<Page> update : updates) {
         List<Integer> history = new ArrayList<Integer>();
         boolean correct = true;
           
         for(Page p : update) {
            for(Rule r : p.rules) {
               if(history.contains(r.y)) {
                  correct = false;
                  this.incorrect.add(update);
                  break;
               }
            }
             
            if(correct) {
               history.add(p.page);
            } else {
               break;
            }
         }
            
         if(correct) {
            int index = (update.size()-1)/2;
            total += update.get(index).page;
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
      for(List<Page>update : this.incorrect) {         
         List<Page>order = reorder(update);
                  
         int index = (order.size()-1)/2;
         total += order.get(index).page;
      }
      
      this.partTwoAnswer = String.valueOf(total);
      return this.partTwoAnswer;
   }
   
   public List<Page> reorder(List<Page> order) {
      List<Page>pages = new ArrayList<Page>(order);
      
      while(true) {
         boolean correct = true;
         List<Integer> history = new ArrayList<Integer>();
         
         for(int i=0; i<pages.size(); i++) {
            Page page = pages.get(i);
            
            for(Rule rule : page.rules) {
               if(history.contains(rule.y)) {
                  correct = false;
               }
            }

            history.add(page.page);
            
            if(!correct) {
               pages.remove(i);
               pages.add(i-1, page);
               break;
            }
         }
         
         if(correct) {
            break;
         }
      }

      return pages;
   }

   public String pagesToString(List<Page> pages) {

      StringBuilder sb = new StringBuilder();
      sb.append("Page:{");
      
      for(int i=0; i<pages.size()-1; i++) {
         sb.append(pages.get(i).page);
         sb.append(",");
      }
      
      sb.append(pages.get(pages.size()-1).page);
      
      sb.append("}");
      return sb.toString();
   }

   public class Rule {
      int x;
      int y;
      public Rule(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      public String toString() {
         return "Rule(" + this.x + "," + this.y + ")";
      }
   }
   
   public class Page {
      int page;
      List<Rule> rules;
      
      public Page(int page, List<Rule> rules) {
         this.page = page;
         this.rules = rules;
      }
   }
}