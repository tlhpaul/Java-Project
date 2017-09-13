package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class FrequencyModelTest {
	FrequencyModel frequencyModel;
	HashMap<Character, Integer> modelMap;
	HashMap<Character, Integer> cipheredMap;
	
	@Before
	public void setUp() throws Exception{
		modelMap = new HashMap<Character, Integer>();
		cipheredMap = new HashMap<Character, Integer>();
		for (int i = 0; i < 26; i ++) {
			modelMap.put((char)('a' + i), i + 200);
		}
		for (int i = 0; i < 26; i ++) {
			cipheredMap.put((char)(('a' + (i + 1) % 26)), i + 50);
		}
		frequencyModel = new FrequencyModel(modelMap, cipheredMap);
	}

	@Test
	public void testDecipher() {
		frequencyModel.buildModel();
		frequencyModel.decipher();
		HashMap<Character, Character> decipheredMap = frequencyModel.getDecipheredMap();
		assertTrue(decipheredMap.get('a') == 'z');
		assertTrue(decipheredMap.get('g') == 'f');
		assertFalse(decipheredMap.get('d') == 'f');
	}

}
