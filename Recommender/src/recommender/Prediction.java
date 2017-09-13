package recommender;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Prediction class. The class execeutes both functionalities and ways to predict.
 * @author paulhsu
 *
 */
public class Prediction {
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private Scanner scanner;
	boolean exit;
	int approach;
	
	/**
	 * Prediction constructor
	 */
	public Prediction() {
		userMap = new HashMap<String, HashMap<String, Double>>();
		itemMap = new HashMap<String, HashMap<String, Double>>();
		exit = false;
		approach = 0;
	}
	
	/**
	 * Main method to process everything
	 */
	public void predict() {
		while (true) {
			try {
				askInput();
				while (! exit) {
					askApproach();
					switch (askFunctionality()) {
					case 1:
						askPrediction();
						break;
					case 2:
						askPreference();
						break;
					case 3:
						exit = true;
						System.out.println("Bye!");
						break;
					default:
						throw new InputMismatchException();
					}
					System.out.println();
			}
			break;
			} catch (TypeNotSupportedException e) {
				System.out.println("We don't support this file type, sorry.");
			} catch (FileNotFoundException e) {
				System.out.println("File doesn't exist, please try again.");
			} catch (InputMismatchException e) {
				System.out.println("Input is incorrect, please try again.");
			} catch (NumberFormatException e) {
				System.out.println("Your input is invalid, please try again.");
			} catch (NullPointerException e) {
				System.out.println("Input wrong dataset? Either the userID or "
						+ "item ID doesn't exist in the "
						+ "dataset, please try again.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("The threshold is bigger than the number we could recommend.");
			}
		}
	}
	
	/**
	 * Ask user to input the file
	 * @throws FileNotFoundException
	 * @throws TypeNotSupportedException 
	 */
	public void askInput() throws FileNotFoundException, TypeNotSupportedException {
		UserFileReader userFileReader = new UserFileReader();
		userFileReader.askFileInput();
		userMap = userFileReader.getUserMap();
		itemMap = userFileReader.getItemMap();
	}
	
	/***
	 * Asks user to use which option to predict the ratings 
	 * @throws InputMismatchException
	 */
	public void askApproach() throws InputMismatchException{
		scanner = new Scanner(System.in);
		System.out.println("Please enter which approach you would like to execeute:");
		System.out.println("Input \"1\" to use BaseLine predictor");
		System.out.println("Input \"2\" to use K-Neareast neighbors "
				+ "and Pearson Correlation");
		System.out.println("Input \"3\" to use K-Neareast neighbors "
				+ "and Cosine Simalarity");
		System.out.println("Please choose which approach you want to use: ");
		approach = scanner.nextInt();
		if (approach != 1 && approach != 2 && approach != 3) {
			throw new InputMismatchException();
		}
	}
	
	/**
	 * Ask which functionality user want to execeute 
	 * @return different integer for different execeution
	 */
	public Integer askFunctionality() {
		scanner = new Scanner(System.in);
		System.out.println("Please enter which functionality you would like to execeute:");
		System.out.println("Input \"1\" to predict rating for given user,");
		System.out.println("Input \"2\" to get n-highest item for given user,");
		System.out.println("Input \"3\" to exit.");
		String result = scanner.nextLine();
		return Integer.parseInt(result);
	}
	
	/**
	 * Ask user to input user id and item id to predict user's preference.
	 */
	public void askPrediction() {
		String userID, itemID;
		scanner = new Scanner(System.in);
		System.out.println("Please input userID: ");
		userID = scanner.nextLine().trim();
		System.out.println("Please input item ID: ");
		itemID = scanner.nextLine().trim();
		UserEngine userFunction = new  UserEngine(approach, userMap, itemMap, userID, itemID);
		userFunction.predictRating();
	}
	
	/**
	 * Ask user to input user id and threshold number N to predict 
	 * user's N-highest preference.
	 */
	public void askPreference() {
		String userID;
		int threshold;
		scanner = new Scanner(System.in);
		System.out.println("Please input userID: ");
		userID = scanner.nextLine().trim();
		System.out.println("Please input threshold: ");
		threshold = Integer.parseInt(scanner.nextLine().trim());
		UserEngine userFunction = new  UserEngine(approach, userMap, itemMap, userID, threshold);
		userFunction.predictPreference();
	}
}
