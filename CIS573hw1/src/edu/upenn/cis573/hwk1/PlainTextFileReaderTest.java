package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class PlainTextFileReaderTest {
	PlainTextFileReader plainTextReader;
	PlainTextFileReader plainTextReader1;
	
	@Before
	public void setUp() {
		plainTextReader = new PlainTextFileReader();
		plainTextReader1 = new PlainTextFileReader();
	}
	
	@Test
	public void testInputSingleFile() throws IOException {
		plainTextReader.inputSingleFile("test1.txt");
		HashMap <Character, Integer> map = plainTextReader.getOriginalMap();
		assertTrue(map.get('a') == 3);
		assertTrue(map.get('b') == 2);
		assertFalse(map.get('c') == 7);
	}
	
	@Test
	public void testInputFiles() throws IOException {
		plainTextReader.inputFiles("test2.txt");
		HashMap <Character, Integer> map = plainTextReader.getModelMap();
		assertTrue(map.get('c') == 5);
		assertTrue(map.get('d') == 1);
		assertFalse(map.get('e') == 1);
	}

	@Test
	public void testBuildMap() {
		HashMap <Character, Integer> map = new HashMap<Character, Integer>();
		plainTextReader.buildMap(map, "abBbcdEef;' 3n, i don;t know");
		assertTrue(map.get('a') == 1);
		assertTrue(map.get('b') == 3);
		assertTrue(map.get('e') == 2);
		assertTrue(map.get(';') == null);
		assertTrue(map.get('3') == null);
		assertTrue(map.get('k') == 1);
	}

}
