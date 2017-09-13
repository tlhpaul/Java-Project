package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CaesarCipherTest {
	private CaesarCipher caesarCipher;
	
	@Before
	public void setUp() throws Exception{
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('a', 3);
		map.put('z', 10);
		map.put('h', 7);
		caesarCipher = new CaesarCipher(map);
	}

	@Test
	public void testCipher() {
		caesarCipher.cipher();
		HashMap <Character, Integer> cipheredMap = caesarCipher.getCipheredMap();
		HashMap<Character, Character> solutionMap = caesarCipher.getSolutionMap();
		assertTrue(3 == cipheredMap.get('k'));
		assertTrue(10 == cipheredMap.get('j'));
		assertTrue(7 == cipheredMap.get('r'));
		assertFalse(7 == cipheredMap.get('k'));
		assertTrue('a' == solutionMap.get('k'));
		assertTrue('z' == solutionMap.get('j'));
		assertFalse('v' == solutionMap.get('j'));
	}
	
}
