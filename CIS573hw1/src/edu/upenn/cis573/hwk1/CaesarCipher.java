package edu.upenn.cis573.hwk1;

import java.util.HashMap;

/**
 * Caesar cipher class, cipher the text by using caesar substitution 
 * @author paulhsu
 *
 */
public class CaesarCipher extends Cipher{
	private HashMap <Character, Integer> map;
	private HashMap <Character, Integer> cipheredMap;
	private HashMap <Character, 	Character> solutionMap;
	
	public CaesarCipher(HashMap <Character, Integer> map) {
		this.map = map;
		cipheredMap = new HashMap <Character, Integer>();
		solutionMap = new HashMap <Character, Character>();
	}
	
	/**
	 * Ciphers the text by caesar substitution
	 */
	@Override
	public void cipher() {
		for (int i = 0; i < 26; i ++) {
			Character cipheredChar = (char) (('a' + ((i + 10) % 26)));
			cipheredMap.put(cipheredChar, map.get((char) ('a' + i)));
			solutionMap.put(cipheredChar, (char) ('a' + i));
		}
	}
	
	@Override
	public HashMap <Character, Integer> getCipheredMap() {
		return cipheredMap;
	}
	
	@Override
	public HashMap<Character, Character> getSolutionMap() {
		return solutionMap;
	}
	
}
