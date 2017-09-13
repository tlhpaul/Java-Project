package recommender;


import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * FileReader abstract class
 * @author paulhsu
 *
 */
public abstract class FileReader {
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	String fileInput;
	
	/**
	 * FileReader constructor
	 */
	public FileReader(String fileInput) {
		userMap = new HashMap<String, HashMap<String, Double>>();
		itemMap = new HashMap<String, HashMap<String, Double>>();
		this.fileInput = fileInput;
	}
	
	/**
	 * Read the file and create two hashmap, one for user and the other for item. 
	 * @throws FileNotFoundException
	 */
	public abstract void readFile() throws FileNotFoundException;
	
	/**
	 * Parses the input file by certain delimiter
	 * @param input
	 * @return String[] after finishing parsing 
	 */
	public abstract String[] parse(String input);

	
	/**
	 * Constructs user map, including userID, itemID and rating
	 * @param userID 
	 * @param itemID
	 * @param rating
	 */
	public void constructUserMap(String userID, String itemID, double rating) {
		if (userMap.containsKey(userID)) {
			HashMap<String, Double> temp = userMap.get(userID);
			temp.put(itemID, rating);
		} else {
			HashMap<String, Double> map = new HashMap<String, Double>();
			map.put(itemID, rating);
			userMap.put(userID, map);
		}
	}
	
	/**
	 * Construct item map, including userID and itemID
	 * @param userID
	 * @param itemID
	 */
	public void constructItemMap(String userID, String itemID, double rating) {
		if (itemMap.containsKey(itemID)) {
			HashMap<String, Double> temp = itemMap.get(itemID);
			temp.put(userID, rating);
		} else {
			HashMap<String, Double> map = new HashMap<String, Double>();
			map.put(userID, rating);
			itemMap.put(itemID, map);
		}
	}
	
	/**
	 * Get the user hashmap 
	 * @return
	 */
	public HashMap<String, HashMap<String, Double>> getUserMap() {
		return userMap;
	}
	
	/**
	 * Get the item hashmap
	 * @return
	 */
	public HashMap<String, HashMap<String, Double>> getItemMap() {
		return itemMap;
	}

}
