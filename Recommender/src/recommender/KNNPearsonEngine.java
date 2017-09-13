package recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * KNNPearsonEngine class extends Engine class. It handles two functions calculating by KNN and Pearson
 * @author paulhsu
 *
 */
public class KNNPearsonEngine extends Engine{
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private String userID; 
	private int threshold;

	/**
	 * KNNPearsonEngine constructor for predictRate
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 */
	public KNNPearsonEngine(HashMap<String, HashMap<String, Double>> userMap,
			HashMap<String, HashMap<String, Double>> itemMap, String userID) {
		super(userMap, itemMap, userID);
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
	}
	
	/**
	 * KNNPearsonEngine constructor for predictPreference 
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param threshold
	 */
	public KNNPearsonEngine(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, int threshold) {
		super(userMap, itemMap, userID, threshold);
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
		this.threshold = threshold;
	}

	/**
	 * Predicts rate by KNN and Cosine prediction 
	 */
	@Override
	public void predictRating(String userID, String itemID) {
		HashMap<String, Double> userList = itemMap.get(itemID);
		if (userList.containsKey(userID)) {
			System.out.println("User " + userID + " already rated this item.");
		} else {
			KNNPearsonComputer knnPearsonComputer = getKNNPearsonComputer(userID, itemID);
			System.out.println("The predicted rating is " + knnPearsonComputer.predictRating());
		}
	}

	/**
	 * Predicts top n preferences by KNN and Cosine prediction 
	 */
	@Override
	public void predictPreference() {
		HashMap<String, Double> preferenceMap = new HashMap<String, Double>();
		for (Map.Entry<String, HashMap<String, Double>> itemEntry: itemMap.entrySet()) {
			HashMap<String, Double> userList = itemEntry.getValue();
			if (! userList.containsKey(userID)) {
				String itemID = itemEntry.getKey();
				KNNPearsonComputer knnPearsonComputer = getKNNPearsonComputer(userID, itemID);
				preferenceMap.put(itemID, knnPearsonComputer.predictRating());
			}
		}
		ArrayList<Entry<String, Double>> result = sortHashMap(preferenceMap);
		if (result.size() < threshold) {
			throw new IndexOutOfBoundsException ();
		}
		printResults(threshold, result);
	}
	
	/**
	 * Uses pearsonSimilarity, neighbor hood to create KNNPearsonComputer
	 * @param userID 
	 * @param itemID
	 * @return KNNPearsonComputer
	 * 
	 */
	public KNNPearsonComputer getKNNPearsonComputer (String userID, String itemID){
		PearsonSimilarity pearsonSimilarity = new PearsonSimilarity(userMap, userID);
		KNearestNeighbor kNearestNeighbor = new KNearestNeighbor(20, itemMap, itemID, pearsonSimilarity);
		ArrayList<String[]> neighborhoodList = kNearestNeighbor.getNeighborhood();
		return new KNNPearsonComputer(neighborhoodList, pearsonSimilarity, userID, itemID); 
	}

}
