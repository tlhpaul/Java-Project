package recommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * TXTFileReader class. Process the txt file. 
 * @author paulhsu
 *
 */
public class TXTFileReader extends FileReader{
	private Scanner scanner;
	
	public TXTFileReader(String fileInput) {
		super(fileInput);
	}
	
	/**
	 * Read the file and create two hashmap, one for user and the other for item. 
	 * @throws FileNotFoundException
	 */
	@Override
	public void readFile() throws FileNotFoundException {
		File file = new File(fileInput);
		scanner = new Scanner(file);
		while (scanner.hasNextLine()){
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
	@Override
	public String[] parse(String input) {
		return input.split("::");
	}
	
}
