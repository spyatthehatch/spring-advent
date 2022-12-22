package com.spyatthehatch.objects;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Monkey object for 2022, Day 11.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class Monkey {
   /**
    * Queue of items this Monkey will inspect.
    */
   private Queue<Long> items;
   
   /**
    * Operation this Monkey performs on items during inspection.
    */
   private Operation op;
   
   /**
    * The magnitude of the operation this Monkey performs during inspection.
    */
   private int opMag;
   
   /**
    * The prime number divisor this Monkey uses during inspection.
    */
   private int divisor;
   
   /**
    * The Monkey that this Monkey will throw items when the true case is met.
    */
   private Monkey trueMonkey;
   
   /**
    * The Monkey that this Monkey will throw items when the false case is met.
    */
   private Monkey falseMonkey;
   
   /**
    * The number of times this Monkey has inspected items.
    */
   private int count;
   
   /**
    * The name of this Moneky object.
    */
   private int name;
   
   /**
    * The Least Common Multiple among all the Monkey objects, used to reduce
    * our worry level about an item, when not using standard worry reduction
    * of dividing by 3.
    */
   private static int commonMultiple = 1;
   
   /**
    * Constructor.
    * 
    * @param name ID of this Monkey.
    */
   public Monkey (final int name){
      this.name = name;
      this.count = 0;
      this.items = new LinkedList<Long>();
   }
   
   /**
    * Set the value of the divisor used during inspection by this Moneky.
    * 
    * @param div Divisor value.
    */
   public void setDivisor(final int div){
      this.divisor = div;
      commonMultiple *= div;
   }
   
   /**
    * Set the Monkey that this Monkey will throw items to when the true case
    * has bee met.
    * 
    * @param m Monkey to throw items to during true case.
    */
   public void setTrueMonkey(final Monkey m){
      this.trueMonkey = m;
   }
   
   /**
    * Set the Monkey that this Monkey will throw items to when the false case
    * has been met.
    * 
    * @param m Monkey to throw items to during false case. 
    */
   public void setFalseMonkey(final Monkey m){
      this.falseMonkey = m;
   }
   
   /**
    * Set the operation that this Mokey will perform during inspection.
    * 
    * @param s String containing operation, in the form: "+ 3" or "* 5".
    * Lastly, this value can be "* old" to perform a square function.
    */
   public void setOperation(final String s){
      if(s.equals("* old")){
         this.op = Operation.SQUARE;
      } else {
         String[] operands = s.split(" ");
      
         if(operands[0].equals("+")){
            this.op = Operation.ADD;
         } else {
            this.op = Operation.MULTIPLY;
         }
      
         this.opMag = Integer.valueOf(operands[1]);
      }
   }
   
   /**
    * Get the name of this Monkey object.
    * 
    * @return Name of Monkey object.
    */
   public int getName(){
      return this.name;
   }
   
   /**
    * Get the count of times this Monkey object has inspected an item.
    * 
    * @return Count of item inspections this Monkey has performed.
    */
   public long getCount(){
      return this.count;
   }
   
   /**
    * Add an item to this Moneky's queue.
    * 
    * @param i Item to add to this Monkey's queue.
    */
   public void addItem(final long i){
      this.items.add(i);
   }
   
   /**
    * The Least Common Multiple among all the Monkey objects, used to reduce
    * our worry level about an item, when not using standard worry reduction
    * of dividing by 3.  Reset this value to 1.
    */
   public static void resetCommonMultiple(){
      commonMultiple = 1;
   }
   
   /**
    * Inspect all items in this Monkey's queue, using standard worry reduction
    * or Least Common Multiple.
    * 
    * @param reduce Boolean to indicate using standard worry reduction (divide
    * by 3), when set as true.  Use Least Common Multiple of all Monkey object
    * divisors when set to false.
    */
   public void inspect(final boolean reduce){      
      final int size = this.items.size();
      for(int i=0; i < size; i++){
         long item = this.items.remove();
         long result;


         if(this.op == Operation.SQUARE){
            result = item * item;
         } else if(this.op == Operation.ADD){
            result = item + this.opMag;
         } else {
            result = item * this.opMag;
         }

         if(reduce){
            result = (int) Math.floor(result / 3);
         } else {
            result = result % commonMultiple;
         }

         if(result < 0){
            throw new RuntimeException("Number overflow.");
         }
         
         if(result % this.divisor == 0){
            this.trueMonkey.addItem(result);
         } else {
            this.falseMonkey.addItem(result);
         }
        
         this.count++;
      }
   }

   @Override
   public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append("Monkey " + this.name)
      .append(", items:" + this.items.size())
      .append(", op:" + this.op)
      .append(", opMag:" + this.opMag)
      .append(", divisor:" + this.divisor)
      .append(", tMonkey:" + this.trueMonkey.getName())
      .append(", fMonkey:" + this.falseMonkey.getName());
      
      return sb.toString();
   }
   
   /**
    * Simple enum to capture different operations a Monkey can perform.
    */
   private enum Operation {MULTIPLY, ADD, SQUARE};
}