package cipher;

/**
 * @author Tse-Lun(Paul) Hsu
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Caesar {

	public static void main(String[] args) {
		Caesar caesar = new Caesar();
		caesar.run();
	}
	
	public void run(){
		encipher(2, "Zebra's name is apple, I love CIT590");
		System.out.println();
		decipher("Mj csy ger vieh xlmw, csy tvsfefpc lezi gsqtpixih xli Geiwev Gmtliv ewwmkrqirx.");
	}
	
	/**
	 * 
	 * Enciphers the message, using the shift. 
	 * 
	 * @param shift The number could be any integer value. 
	 * @param plainText The message need to be enciphered. 
	 * @return A string with enciphered message. 
	 */
	String encipher(int shift, String plainText){ 
		int x = shift % 26;
		String encipheredMessage;
		encipheredMessage = shift(x, plainText);
		return encipheredMessage;
	}
	
	/**
	 * 
	 * Deciphers the message. Given any message enciphered using a Caesar cipher, 
	 * decipher it and return the deciphered message.
	 * 
	 * @param cipheredText The text need to be deciphered. 
	 * @return A string with deciphered message. 
	 */
	String decipher(String cipheredText){
		String decipheredText;
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> decipheredwords = new ArrayList<String>();
		words = readFile("wordsEn.txt");
		int count2 = 0;
		for (int i = 0; i < 26; i ++){
			int count1 = 0;
			decipheredText = shift(i, cipheredText);
			String [] s = decipheredText.split(" ");
			for (int j = 0; j < s.length; j++) {
				if (words.contains(s[j].toLowerCase())) count1 += 1;
			}
			if (count1 > count2) {
				decipheredwords.add(decipheredText);
				count2 = count1;
			} 
		}
		if (decipheredwords.size() > 0) {
			System.out.println(decipheredwords.get(decipheredwords.size() - 1));
			return decipheredwords.get(decipheredwords.size() - 1);
		}
		return ("Your ciphered text can't be solved.");
	}
	
	
	/**
	 * 
	 * Reads the file and adds the words from the file in an arraylist. 
	 * 
	 * @param filename The file to be read. 
	 * @return An arraylist with string of words from the file. 
	 */
	public ArrayList<String> readFile(String filename){
		ArrayList<String> words = new ArrayList<String>();
		try{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null){
		    words.add(line);
			}
		reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return words;
	}
	
	/**
	 * 
	 * Shifts the text with the given shifted amount. This method is used in encipher and decipher method for shifting. 
	 * 
	 * @param shift The number should be between 0 and 25. 
	 * @param Text The text need to be shifted. 
	 * @return
	 */
	public String shift(int shift, String Text){
		char ch1;
		int numberOfz = (int) ('z');	
		int numberOfZ = (int) ('Z');
		String Message = new String();
		char [] message = Text.toCharArray();
		for (int i = 0; i < message.length; i ++){
			if (Character.isUpperCase(message[i])) { 
				if ((int) (message[i] + shift) > numberOfZ) ch1 = (char) ((message[i]) + shift - 26);
				else ch1 = (char) ((message[i]) + shift);
			}
			else if (Character.isLowerCase(message[i])){ 
				if ((int) (message[i] + shift) > numberOfz) ch1 = (char) ((message[i]) + shift - 26);
				else ch1 = (char) ((message[i]) + shift);
			}
			else ch1 = (char) ((message[i]));
			Message += Character.toString(ch1);
		}
		return Message;
	}
}
	
