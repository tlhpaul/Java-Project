package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * MapProducer class, creates singleMap for single input file and modelMap
 * for other documents in the corpus
 * @author paulhsu
 *
 */
public class MapProducer {
	private HashMap <Character, Integer> singleMap;
	private HashMap <Character, Integer> modelMap;
	
	public MapProducer() {
		singleMap = new HashMap<Character, Integer>();
		modelMap = new HashMap<Character, Integer>();
	}
	
	/**
	 * Gets single map 
	 * @return map with single file input  
	 */
	public HashMap <Character, Integer> getSingleMap() {
		return singleMap;
	}
	
	/**
	 * Gets model map 
	 * @return
	 */
	public HashMap <Character, Integer> getModelMap() {
		return modelMap;
	}

}
