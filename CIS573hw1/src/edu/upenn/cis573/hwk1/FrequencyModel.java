package edu.upenn.cis573.hwk1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Frequency Model class which extends Decipher Model abstract class
 * @author paulhsu
 *
 */
public class FrequencyModel extends DecipherModel{
	private HashMap<Character, Integer> modelMap; 
	private HashMap<Character, Integer> cipheredMap;
	private HashMap<Character, Character> decipheredMap;
	private Character[] model;
	
	
	public FrequencyModel(HashMap<Character, Integer> modelMap, 
			HashMap<Character, Integer> cipheredMap) {
		this.modelMap = modelMap;
		this.cipheredMap = cipheredMap;
		this.decipheredMap = new HashMap<Character, Character>();
	}

	@Override
	public void buildModel() {
		model = buildAlphabetArray();
		sortArray(model, modelMap);
	}
	
	/**
	 * Deciphers the ciphered text by using frequency model,
	 * first is ciphered word, the second is deciphered one
	 */
	@Override
	public void decipher() {
		Character[] cipheredText = buildAlphabetArray();
		sortArray(cipheredText, cipheredMap);
		for (int i = 0; i < cipheredText.length; i ++) {
			decipheredMap.put(cipheredText[i], model[i]);
		}
	}
	
	/**
	 * Builds an array with 26 alphabets 
	 * @return alphabet array
	 */
	private Character[] buildAlphabetArray() {
		Character[] array = new Character[26];
		for (int i = 0; i < 26; i ++) {
			array[i] = (char) ('a' + i);
		}
		return array; 
	}
	
	/**
	 * Sorts the array based on the frequency of character in hashmap 
	 * @param array
	 * @param map
	 */
	private void sortArray(Character[] charArray, HashMap<Character, Integer> 
	frequencyMap) {
		Arrays.sort(charArray, new Comparator<Character>() {
			@Override
			public int compare(Character a, Character b) {
				return frequencyMap.get(b) - frequencyMap.get(a);
			}
		});
	}
	
	/**
	 * Returns deciphered hash map 
	 */
	@Override
	public HashMap<Character, Character> getDecipheredMap() {
		return decipheredMap;
	}

}
