package recommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CSV File reader extends FileReader
 * @author paulhsu
 *
 */
public class CSVFileReader extends FileReader{
	private Scanner scanner;
	
	/**
	 * CSVFileReader constructor
	 * @param fileInput
	 */
	public CSVFileReader(String fileInput) {
		super(fileInput);
	}
	
	/**
	 * Read the file and create two hashmap, one for user and the other for item. 
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException {
		File file = new File(fileInput);
		scanner = new Scanner(file);
		scanner.nextLine();
		while (scanner.hasNextLine()) {
			String[] input = parse(scanner.nextLine());
			constructUserMap(input[0], input[1], Double.parseDouble(input[2]));
			constructItemMap(input[0], input[1], Double.parseDouble(input[2]));
		}
	}
	
	/**
	 * Parse the input file by ::
	 * @param input
	 * @return
	 */
	public String[] parse(String input){
		input = input.replaceAll("\"","");
		if (input.contains(";")) {
			return input.split(";"); 
		}
		return input.split(",");
	}

}
