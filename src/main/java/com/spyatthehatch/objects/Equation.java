package com.spyatthehatch.objects;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class Equation {   
   private long target;
   private int first;
   private List<Operation> operations;
   
   public Equation(final String s) {
      this.operations = new ArrayList<Operation>();
      String[] parts = s.split(":");
      this.target = Long.valueOf(parts[0]);
      
      String[] numbers = parts[1].trim().split(" ");
      
      first = Integer.valueOf(numbers[0]);
      for(int i=1; i < numbers.length; i++) {
         Operation op = new Operation(Integer.valueOf(numbers[i]));
         this.operations.add(op);
      }
   }
   
   public boolean calibrate() {
      boolean correct = false;
      boolean done = false;
      
      while(!correct && !done) {
         if(this.evaluate() == target) {
            correct = true;
         } else {
            done = this.increment();
         }
      }

      return correct;
   }
   
   
   public boolean increment() {
      boolean rollover = false;;
      
      for(int i=0; i<this.operations.size(); i++) {
         Operation op = this.operations.get(i);
         rollover = op.incrementOperator();
         if(!rollover) {
            return false;
         }
      }
      
      return rollover;
   }
   
   public long evaluate() {
      long current = (long)this.first;
      
      for(Operation op : this.operations) {
         current = op.result(current);
      }
      
      return current;
   }
   
   public long getTarget() {
      return this.target;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[target:" + this.target);
      sb.append("," + this.first);
      
      for(Operation op : this.operations) {
         sb.append(op.toString());
      }
      
      sb.append("]");
      return sb.toString();
   }

   public class Operation {
      public int y;
      public Operator op;
      
      public Operation(int y) {
         this.y = y;
         this.op = Operator.ADD;
      }
      
      public long result(long input) {
         switch(this.op) {
         case ADD:
            return input + y;
         case MULTIPLY:
            return input * y;
         case CONCAT:
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            sb.append(y);
            return Long.valueOf(sb.toString());
         default:
            return -1;
         }
      }
      
      public boolean incrementOperator() {
         switch(this.op) {
            case ADD:
               this.op = Operator.MULTIPLY;
               return false;
            case MULTIPLY:
               this.op = Operator.CONCAT;
               return false;
            case CONCAT:
               this.op = Operator.ADD;
               return true;
            default:
               return false;
         }
      }
      
      public String toString() {
         StringBuilder sb = new StringBuilder();
                  
         switch(this.op) {
            case ADD:
               sb.append("+");
               break;
            case MULTIPLY:
               sb.append("*");
               break;
            case CONCAT:
               sb.append("||");
               break;
            default:
               sb.append("?");
         }
         
         sb.append(y);
         return sb.toString();
      }
   }
   
   public enum Operator {
      ADD,
      MULTIPLY,
      CONCAT;
   }
}
