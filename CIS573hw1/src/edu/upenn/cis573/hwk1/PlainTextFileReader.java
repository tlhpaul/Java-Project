package edu.upenn.cis573.hwk1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlainTextFileReader extends FilesReader{
	private HashMap <Character, Integer> originalMap;
	private HashMap <Character, Integer> modelMap;
	private MapProducer mapProducer;
	
	public PlainTextFileReader() {
		mapProducer = new MapProducer();
	}
	
	
	@Override
	public void inputSingleFile(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String line = bufferedReader.readLine();
		if (mapProducer != null) {
			mapProducer = new MapProducer();
		}
		originalMap = mapProducer.getSingleMap();
		while (line != null) {
			buildMap(originalMap, line);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	@Override
	public void inputFiles(String fileName) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String line = bufferedReader.readLine();
		modelMap = mapProducer.getModelMap();
		while (line != null) {
			buildMap(modelMap, line);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	/**
	 * Iterates through the line and store the frequency of each character
	 * in the map  
	 * @param map 
	 * @param line
	 */
	public void buildMap(HashMap <Character, Integer> map, String line) {
		String lineToLowerCase = line.toLowerCase();
		ArrayList<Character> alphabetList = buildAlphabetArray();
		char[] charArray = lineToLowerCase.toCharArray();
		for (char character: charArray) {
			if (alphabetList.contains(character)) {
				if (map.containsKey(character)) {
					map.put(character, map.get(character) + 1);
				}
				else map.put(character, 1);
			}	
		}
	}
	
	/**
	 * Gets map with unciphered text
	 * @return map with unciphered text
	 */
	public HashMap <Character, Integer> getOriginalMap(){
		return originalMap;
	}
	
	/**
	 * Gets decipher model map 
	 * @return decipher model map 
	 */
	public HashMap <Character, Integer> getModelMap(){
		return modelMap;
	}
	
	/**
	 * Builds an array with 26 alphabets 
	 * @return alphabet array
	 */
	private ArrayList<Character> buildAlphabetArray() {
		ArrayList<Character> array = new ArrayList<Character>();
		for (int i = 0; i < 26; i ++) {
			array.add((char) ('a' + i));
		}
		return array; 
	}
	
}
