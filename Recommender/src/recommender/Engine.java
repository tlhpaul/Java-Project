package recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Engine abstract class
 * @author paulhsu
 *
 */
public abstract class Engine {

	/**
	 * Engine constructor for predictRate
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 */
	public Engine(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID) {
		
	}
	
	/**
	 * Engine constructor for predictPreference
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param threshold
	 */
	public Engine(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, int threshold) {
	}
	
	/**
	 * Predicts rates
	 * @param userID
	 * @param itemID
	 */
	public abstract void predictRating(String userID, String itemID);
	
	/**
	 * Predict top n highest preferences
	 */
	public abstract void predictPreference();
	
	/**
	 * Sorts the hashmap by value in different entry
	 * @param preferenceMap HashMap<Integer, Double>
	 * @return ArrayList<Entry<Integer, Double>> sorted ArrayList
	 */
	public ArrayList<Entry<String, Double>> sortHashMap(HashMap<String, Double> preferenceMap) {
		ArrayList<Map.Entry<String, Double>> resultsList = 
				new ArrayList<Map.Entry<String, Double>>(preferenceMap.entrySet());
		Collections.sort(resultsList, new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return resultsList;
	}
	
	/**
	 * Prints the result for askPreference() method
	 * @param threshold threshold number
	 * @param result sorted result
	 */
	public void printResults(int threshold, ArrayList<Entry<String, Double>> result) {
		System.out.println("Recommended items:");
		for (int i = 0; i < threshold; i ++ ) {
			Entry<String, Double> itemEntry = result.get(i);
			System.out.println("item: " + itemEntry.getKey());
		}
	}

}
