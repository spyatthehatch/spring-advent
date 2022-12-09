package com.spyatthehatch.util;

/**
 * Utility class of methods to support binary manipulation and calculations.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class BinaryUtils {
   /*
    * Prevent constructor of this class.
    */
   private BinaryUtils(){};
   
	/**
	 * Return an integer value of an array of binary values, where the 0th bit
	 * is the Most Significant Digit.
	 * 
	 * @param binary Array of binary values.
	 * @return Integer value of input array.
	 * @throws RuntimeException if input array contains illegal characters.
	 */
	public static int binaryArrayToInt(final int[] binary){
		final int length = binary.length;
		int sum = 0;
	
		for(int i = 0; i < length; i++){
		   if(binary[i] < 0 || binary[i] > 1){
		      throw new RuntimeException("Input binary array contains an illegal character.");
		   }
			sum += binary[i] * Math.pow(2, length - 1 - i);
		}
		return sum;
	}
	
	/**
	 * Return an array of binary values from a String input, where the 0th bit
	 * is the Most Significant Digit.
	 * 
	 * @param input String input of 1s and 0s.
	 * @return Array of integer values, where the 0th bit is the MSD.
	 * @throws RuntimeException if input String contains illegal characters.
	 */
	public static int[] stringToBinaryArray(final String input){
		int output[] = new int[input.length()];
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '0'){
				output[i] = 0;
			} else if (input.charAt(i) == '1') {
				output[i] =1;
			} else {
			   throw new RuntimeException("Input binary string contains an illegal character.");
			}
		}
		return output;
	}

	/**
	 * Get the binary value (as a String) of a Hexadecimal String. 
	 * 
	 * @param s String containing Hexadecimal value.
	 * @return Binary value of Hexadecimal String input.
	 */
	public static String hexToBinary(final String s){
	   String hex = s;
		hex = hex.replaceAll("0", "0000");
		hex = hex.replaceAll("1", "0001");
		hex = hex.replaceAll("2", "0010");
		hex = hex.replaceAll("3", "0011");
		hex = hex.replaceAll("4", "0100");
		hex = hex.replaceAll("5", "0101");
		hex = hex.replaceAll("6", "0110");
		hex = hex.replaceAll("7", "0111");
		hex = hex.replaceAll("8", "1000");
		hex = hex.replaceAll("9", "1001");
		hex = hex.replaceAll("A", "1010");
		hex = hex.replaceAll("B", "1011");
		hex = hex.replaceAll("C", "1100");
		hex = hex.replaceAll("D", "1101");
		hex = hex.replaceAll("E", "1110");
		hex = hex.replaceAll("F", "1111");
		return hex;
	}
	
	/**
	 * Get the integer representation of a binary value.
	 * 
	 * @param s Binary value, as a String.
	 * @return Integer value of binary String.
	 */
	public static int binaryToDecimal(final String s){
		return Integer.parseInt(s, 2);
	}
}