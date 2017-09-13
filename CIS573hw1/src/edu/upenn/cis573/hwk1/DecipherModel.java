package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * Decipher Model class, depending on which model programmer wants to use for deciphering 
 * @author paulhsu
 *
 */
public abstract class DecipherModel {
	
	/**
	 * Builds the decipher model. 
	 */
	abstract public void buildModel();
	
	/**
	 * Deciphers the ciphered text by using any decipher model
	 */
	abstract public void decipher();
	
	/**
	 * Returns the deciphered map 
	 * @return
	 */
	abstract public HashMap<Character, Character> getDecipheredMap();

}
