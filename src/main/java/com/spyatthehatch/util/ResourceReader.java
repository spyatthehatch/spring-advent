package com.spyatthehatch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResourceReader utility to read files to a Java List.
 * 
 * @author Bill Everton
 * @version Advent 2022
 */
public class ResourceReader {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
	   LoggerFactory.getLogger(ResourceReader.class);
				
	/**
	 * Class InputStream.
	 */
	private InputStream is = null;
	
	/**
	 * Class BufferedReader for one line reads.
	 */
	private BufferedReader reader = null;
	
	/**
	 * ResourceReader constructor.
	 * 
	 * @param resource Resource file to read.
	 */
	public ResourceReader(final String resource) {
		LOGGER.trace("Reading file: " + resource + ".");
		this.is = getClass().getClassLoader().getResourceAsStream(resource);
		this.reader = new BufferedReader(new InputStreamReader(this.is));
	}
	
	/**
    * Get a new InputStreamReader for this ResourceReader.
    * 
    * @return New InputStreamReader.
    */
   public InputStreamReader getInputStreamReader(){
      return new InputStreamReader(this.is);
   }
	
	/**
	 * Get the file contents as an ArrayList of String objects.
	 * 
	 * @return List of String objects.
	 */
	public List<String> toList() {
		final List<String> list = new ArrayList<String>();
		String line = null;
		
		final BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
		try {
			while((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		} catch (final IOException e) {
			LOGGER.warn("I/O error on read from resource.");
			throw new RuntimeException(e.getMessage());
		}
		
		LOGGER.trace("Read in " + list.size() + " elements.");
		return list;
	}
	
	/**
	 * Return a line from a resource file.
	 * 
	 * @return String of one line from the file resource of this
	 * ResourceReader.
	 */
	public String toString() {
		String s = null;
		
		try {
			s = this.reader.readLine();
		} catch (final IOException e) {
			LOGGER.warn("I/O error on read from resource.");
			throw new RuntimeException(e.getMessage());
		}
		
		return s;
	}
	
	/**
	 * Close all streams and readers.
	 */
	public void close(){
	   try {
	      if(this.reader != null){
	         this.reader.close();
	      }
	   } catch (final IOException io){
	      LOGGER.warn("I/O error on close ResourceReader buffered reader.");
	      throw new RuntimeException(io.getMessage());
	   } finally {
	      try {
	         if(this.is != null){
	            this.is.close();
	         }
	      } catch (final IOException io){
	         LOGGER.warn("I/O error on close of ResourceReader input stream.");
	         throw new RuntimeException(io.getMessage());
	      }
	   }
	}
}