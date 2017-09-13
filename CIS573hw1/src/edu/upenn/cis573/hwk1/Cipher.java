package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * Cipher class, programmer can extend this class
 * by any type of substitutions
 * @author paulhsu
 *
 */
public abstract class Cipher {
	
	/**
	 * Ciphers the text by substitution chosen. 
	 */
	abstract public void cipher();
	abstract public HashMap <Character, Integer> getCipheredMap();
	abstract public HashMap <Character, Character> getSolutionMap();

}
