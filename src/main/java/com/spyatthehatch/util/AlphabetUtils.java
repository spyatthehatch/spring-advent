package com.spyatthehatch.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Utilities to assist with alphabet problems.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class AlphabetUtils {
   /*
    * Prevent constructor for this class.
    */
   private AlphabetUtils(){};
   
   /**
    * Array of the lower case letters of the English alphabet.  Each letter can
    * be accessed by its index.  'a' is 0 and 'z' is 25.
    */
   public static final char[] LETTERS = new char[] {'a','b','c','d','e','f',
      'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x',
      'y','z'};
   
   /**
    * A String of the English alphabet, starting with the lower case letters
    * and then onto the upper case letters.  The 0th letter is 'a', 25th letter
    * is 'z', the 26th letter is 'A', and so on to the 51th letter being 'Z'.
    */
   public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
   /**
    * Get the index of a letter in the alphabet.  The 0th letter is 'a', 25th
    * letter is 'z', the 26th letter is 'A', and so on to the 51th letter being
    * 'Z'.
    * 
    * @param c Char to find index.
    * @return index of given char.
    */
   public static int getAlphaIndex(final char c){
      return ALPHABET.indexOf(c) + 1;
   }
   
   /**
    * Return the first common character among two String objects.  The first
    * String is iterated over to check the other String for matches.
    * 
    * @param first String to iterate through in search for common character.
    * @param second Second String to check.
    * @return the first common char among the two String objects.  If no
    * common characters are found, return '!' exclamation.
    */
   public static char getCommon(final String first, final String second){
      for(int i=0; i < first.length(); i++){
         char c = first.charAt(i);

         if(second.indexOf(c) >= 0){
            return c;
         }
      }
      
      return '!';
   }
   
   /**
    * Return the first common character among three String objects.  The first
    * String is iterated over to check the other String objects for matches.
    * 
    * @param first String to iterate through in search for common character.
    * @param second Second String to check.
    * @param third Third String to check.
    * @return the first common char among the three String objects.  If no
    * common characters are found, return '!' exclamation.
    */
   public static char getCommon(final String first, final String second,
      final String third){
   
      for(int i=0; i < first.length(); i++){
         char c = first.charAt(i);
      
         if(second.indexOf(c) >= 0 && third.indexOf(c) >= 0){
            return c; 
         }
      }
      
      return '!';
   }
   
   /**
    * Check if a String has any duplicate characters.
    * 
    * @param buffer String to check for duplicates.
    * @return True, if any duplicate characters are found.  False, otherwise.
    */
   public static boolean containsDupes(final String buffer){
      final Set<Character> set = new HashSet<Character>();
      
      for(int i=0; i < buffer.length(); i++){
         set.add(buffer.charAt(i));
      }
      
      if(set.size() < buffer.length()){
         return true;
      } else {
         return false;
      }
   }
}