package recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * BaselineEngine class extends Engine class. It handles two functions calculating by baseline prediction 
 * @author paulhsu
 *
 */
public class BaselineEngine extends Engine{
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private String userID; 
	private int threshold;

	/**
	 * Baseline constructor for predictRate
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 */
	public BaselineEngine(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID) {
		super(userMap, itemMap, userID);
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
	}
	
	/**
	 * Baseline constructor for predictPreference
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param threshold
	 */
	public BaselineEngine(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, int threshold) {
		super(userMap, itemMap, userID, threshold);
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
		this.threshold = threshold;
	}

	/**
	 * Predicts rate by baseline prediction 
	 */
	@Override
	public void predictRating(String userID, String itemID) {
		BaseLineComputer baseLineComputer = new BaseLineComputer(userMap, itemMap, userID, itemID);
		HashMap<String, Double> userList = itemMap.get(itemID);
		if (userList.containsKey(userID)) {
			System.out.println("User " + userID + " already rated this item.");
		} else {
			System.out.println("The predicted rating is " + baseLineComputer.predictRating());
		}
	}

	/**
	 * Predicts top n preferences by baseline prediction 
	 */
	@Override
	public void predictPreference() {
		HashMap<String, Double> preferenceMap = new HashMap<String, Double>();
		for (Map.Entry<String, HashMap<String, Double>> itemEntry: itemMap.entrySet()) {
			HashMap<String, Double> userList = itemEntry.getValue();
			if (! userList.containsKey(userID)) {
				String itemID = itemEntry.getKey();
				BaseLineComputer baseLineComputer = new BaseLineComputer(userMap, itemMap, itemID, itemID);
				preferenceMap.put(itemID, baseLineComputer.predictRating());
			}
		}
		ArrayList<Entry<String, Double>> result = sortHashMap(preferenceMap);
		if (result.size() < threshold) {
			throw new IndexOutOfBoundsException ();
		}
		printResults(threshold, result);
	}

}
