package com.spyatthehatch.util;

public class MathUtils {
   public static long leastCommonMultiple(int[] array) {
      long lcm = 1;
      int divisor = 2;
      
      while(true) {
         int counter = 0;
         boolean divisible = false;
         
         for(int i=0; i < array.length; i++) {
            if(array[i] == 1) {
               counter++;
            }
            
            if(array[i] % divisor == 0) {
               divisible = true;
               array[i] = array[i] / divisor;
            }
         }
         
         if(divisible) {
            lcm = lcm * divisor;
         } else {
            divisor++;
         }
         
         if(counter == array.length) {
            return lcm;
         }
      }
   }
}
