package com.spyatthehatch.util;

import java.util.List;

/**
 * List utilities.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class ListUtils {
	/**
	 * Check if any objects from a list are in another.
	 * 
	 * @param listToCheck List to check if objects are in.
	 * @param subset Subset of objects to check if they exist in listToCheck.
	 * This can be a List of objects, or a single object.
	 * @return true if any objects exist, false otherwise.
	 */
	public static boolean containsAny(final List<?> listToCheck, 
		final Object subset){
		
		if(subset instanceof List<?>) {
			final List<?>subsetList = (List<?>) subset;
			
			for(final Object o : subsetList) {
				if(listToCheck.contains(o)){
					return true;
				}
			}
		} else {
			return listToCheck.contains(subset);
		}
		
		return false;
	}
}