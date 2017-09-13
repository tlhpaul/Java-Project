package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Fileinput class, handles input file
 * @author paulhsu
 *
 */
public class FileInputer {

	/**
	 * Handles input file
	 * @return scanner scan
	 * @throws FileNotFoundException
	 */
	public Scanner input() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input your file: ");
		File inputFile = new File(scanner.nextLine().trim());
		Scanner scan = new Scanner(inputFile);
		scanner.close();
		return scan;
	}
	
}
