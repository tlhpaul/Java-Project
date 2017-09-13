package cipher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CaesarTest {
	Caesar caesar;
	
	@Before
	public void setUp(){
		caesar = new Caesar();
	}
	
	@Test
	public void testEncipher() {
		assertEquals("bqqmf", caesar.encipher(1, "apple"));
		assertEquals("Bgdtc", caesar.encipher(2, "Zebra"));
		assertEquals("Bgdtc'u pcog ku crrng, K nqxg EKV590", caesar.encipher(2, "Zebra's name is apple, I love CIT590"));
		assertNotEquals("Bgdtc'upcogkucrrng", caesar.encipher(2, "Zebra's name is apple"));
	}

	@Test
	public void testDecipher() {
		assertEquals("University of Pennsylvania", caesar.decipher("Slgtcpqgrw md Ncllqwjtylgy"));
		assertEquals("I love CIT590", caesar.decipher("D gjqz XDO590"));
		assertEquals("Your ciphered text can't be solved.", caesar.decipher("ABCDEF HUJIKOGF fcdrrtg"));
		assertEquals("Thw treasurea is not here", caesar.decipher("Ocr omzvnpmzv dn ijo czmz"));
		assertEquals("Zebra's name is apple, I love CIT590", caesar.decipher("Bgdtc'u pcog ku crrng, K nqxg EKV590"));
		assertNotEquals("Thw treasurea isr noifd here", caesar.decipher("Ocr omzvnpmzv dn ijo czmz"));
	}
	
	@Test
	public void testShift(){
		assertEquals("bqqmf", caesar.shift(1, "apple"));
		assertEquals("Bgdtc", caesar.shift(2, "Zebra"));
		assertEquals("Bgdtc'u pcog ku crrng", caesar.shift(2, "Zebra's name is apple"));
		assertNotEquals("Bgdtc'upcogkucrrng", caesar.shift(2, "Zebra's name is apple"));
	}

}
