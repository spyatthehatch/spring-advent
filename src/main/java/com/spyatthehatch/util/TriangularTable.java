package com.spyatthehatch.util;

/**
 * Utility class to create a Triangular lookup table.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class TriangularTable {   
	/**
	 * Table to hold Triangular series values.
	 */
	private int[] table;

	/**
	 * Create a new Triangular lookup table of a given size.  Triangular table
	 * is the sum of the sequence 1 + 2 + 3 + 4 + ... + N.
	 * 
	 * @param size Size to create Triangular table.
	 */
	public TriangularTable(final int size){
		this.table = new int[size + 1];
		
		for(int i=0; i <= size; i++){		
			for(int j=i; j > 0; j--){
				this.table[i] += j;
			}
		}
	}
	
	/**
	 * Get the value of the Triangular series at the given index.  The series
	 * value is the sum of the sequence 1 + 2 + 3 + 4 + 5 + ... + N.  For
	 * example, the series value at index 5 is 1 + 2 + 3 + 4 + 5 = 15.
	 * 
	 * @param index Index of Triangular series.
	 * @return Value of the Triangular series at index.
	 * @throws ArrayIndexOutOfBoundsException will be thrown if index is not 0
	 * or greater and within the size defined.
	 */
	public int getValue(final int index){
		return this.table[index];
	}	
}