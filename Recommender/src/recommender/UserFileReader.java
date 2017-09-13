package recommender;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * UserFileReader class, it is a factory class which has TXTFileReader and CSVFileReader
 * @author paulhsu
 *
 */
public class UserFileReader {
	private Scanner scanner;
	private int type;
	private TXTFileReader txtFileReader;
	private CSVFileReader csvFileReader;
	
	/**
	 * Asks the input file 
	 * @throws FileNotFoundException
	 * @throws TypeNotSupportedException
	 */
	public void askFileInput() throws FileNotFoundException, TypeNotSupportedException {
		scanner = new Scanner(System.in);
		System.out.println("Please input your file: ");
		String inputFile = scanner.nextLine().trim();
		if (inputFile.contains("csv")) {
			csvFileReader = new CSVFileReader(inputFile);
			csvFileReader.readFile();
			type = 1;
		} else if (inputFile.contains("txt") || inputFile.contains("dat")) {
			txtFileReader = new TXTFileReader(inputFile);
			txtFileReader.readFile();
			type = 2;
		} else {
			throw new TypeNotSupportedException(); 
		}
	}
	
	/**
	 * Get the user hashmap
	 * @return
	 */
	public HashMap<String, HashMap<String, Double>> getUserMap() {
		switch (type) {
		case 1:
			return csvFileReader.getUserMap();
		case 2:
			return txtFileReader.getUserMap();
		default:
			return null;
		}
	}
	
	/**
	 * Get the item hashmap
	 * @return
	 */
	public HashMap<String, HashMap<String, Double>> getItemMap() {
		switch (type) {
		case 1:
			return csvFileReader.getItemMap();
		case 2:
			return txtFileReader.getItemMap();
		default:
			return null;
		}
	}

}
