package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Processor class handles encrypt, decrypts and measures the accuracy
 * @author paulhsu
 *
 */
public class Processor {
	private PlainTextFileReader plainTextFileReader;
	private int sumCorrect;
	private int sumIncorrect;
	
	public Processor() {
		plainTextFileReader = new PlainTextFileReader();
	}
	
	/**
	 * The main method in Processor class
	 */
	public void process() {
		double accuracy = 0.00;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input your file: ");
		File folder = new File(scanner.next());
		inputFolder(folder);
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		if (sumCorrect != 0 && sumIncorrect != 0) {
			System.out.println("Total: " + sumCorrect + " correct, " + sumIncorrect + " incorrect.");
			accuracy = (double) sumCorrect*100.00/(sumIncorrect+sumCorrect);
			System.out.println("Accuracy: " + decimalFormat.format(accuracy) + "%");
		}
		scanner.close();
	}
	
	/**
	 * Iterates through the files in the folder then calls compute method
	 * for every file.
	 * @param folder
	 */
	public void inputFolder(File folder) {
		ArrayList<String> fileList = new ArrayList<String>();
		String absolutePath = folder.getAbsolutePath();
		try {
			if (folder.isDirectory() && folder.listFiles().length == 0) {
				throw new FolderIsEmpty();
			}
			for (File file: folder.listFiles()) {
				if (! file.getName().equals(".DS_Store")) {
					fileList.add(file.getName());
				}
			} 
			inputFilesBuildModel(fileList, absolutePath);
		} catch (FolderIsEmpty e) {
			System.out.println("Folder is empty.");
		} catch (NullPointerException e) {
			System.out.println("Specified directory doesn't exist.");
		} catch (AccessDeniedException e) {
			System.out.println("You dont have permission.");
		} catch (IOException e) {
			System.out.println("Something wrong with your input file.");
		}
	}
	
	/**
	 * Inputs one file and inputs other files to build model, 
	 * do the same thing for the rest of files
	 * @param fileList
	 * @param absolutePath
	 * @throws IOException
	 */
	public void inputFilesBuildModel(ArrayList<String> fileList, 
			String absolutePath) throws IOException {
		for (int i = 0; i < fileList.size(); i ++) {
			System.out.print(fileList.get(i) + ": ");
			plainTextFileReader.inputSingleFile(absolutePath + "/" + fileList.get(i));
			for (int j = 0; j < fileList.size(); j ++) {
				if (i != j) {
					plainTextFileReader.inputFiles(absolutePath + "/" + fileList.get(j));
				}
			}
			compute();
		}
	}
	
	/**
	 * compute method takes and the one document and a corpus of other documents used to build 
	 * map to decipher and measure accuracy. 
	 */
	public void compute() {
		HashMap <Character, Integer> map = plainTextFileReader.getOriginalMap();
		HashMap <Character, Integer> frquencyMap = plainTextFileReader.getModelMap();
		Cipher caesarCipher = new CaesarCipher(map);
		caesarCipher.cipher();
		DecipherModel decipherModel = new FrequencyModel(frquencyMap, caesarCipher.getCipheredMap());
		decipherModel.buildModel();
		decipherModel.decipher();
		measureAccuracy(decipherModel.getDecipheredMap(), caesarCipher.getSolutionMap(), map);
	}
	
	/**
	 * Measures the accuracy based on the deciphered text and frequency model
	 * @param decipheredMap deciphered text is stored in deciphered text
	 * @param solutionMap the information of frequency model is stored in solutionMap
	 * @param originalMap the original text is stored in originalMap
	 */
	public void measureAccuracy(HashMap<Character, Character> decipheredMap, 
			HashMap<Character, Character> solutionMap, HashMap <Character, Integer> originalMap) {
		int correct = 0, incorrect = 0; 
		for (int i = 0; i < 26; i ++) {
			if (decipheredMap.get((char)('a' + i)) == solutionMap.get((char)('a' + i))) {
				correct += originalMap.get((char)('a' + i));
			}
			else {
				incorrect += originalMap.get((char)('a' + i));
			}
		}
		Outputer outputer = new CommandLineUI();
		outputer.output(correct, incorrect);
		sumCorrect += correct;
		sumIncorrect += incorrect;
	}
	
}
