package recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * KNNCosineEngine class extends Engine class. It handles two functions calculating by KNN and Cosine
 * @author paulhsu
 *
 */
public class KNNCosineEngine extends Engine{
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private String userID; 
	private int threshold;

	/**
	 * KNNCosineEngine constructor for predictRate
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 */
	public KNNCosineEngine(HashMap<String, HashMap<String, Double>> userMap,
			HashMap<String, HashMap<String, Double>> itemMap, String userID) {
		super(userMap, itemMap, userID);
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
	}
	
	/**
	 * KNNCosineEngine constructor for predictPreference 
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param threshold
	 */
	public KNNCosineEngine(HashMap<String, HashMap<String, Double>> userMap, 
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
			KNNCosineComputer knnCosineComputer = getKNNCosinePearsonComputer(userID, itemID);
			System.out.println("The predicted rating is " + knnCosineComputer.predictRating());
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
				KNNCosineComputer knnCosineComputer = getKNNCosinePearsonComputer(userID, itemID);
				preferenceMap.put(itemID, knnCosineComputer.predictRating());
			}
		}
		ArrayList<Entry<String, Double>> result = sortHashMap(preferenceMap);
		if (result.size() < threshold) {
			throw new IndexOutOfBoundsException ();
		}
		printResults(threshold, result);
	}
	
	/**
	 * Return KNNCosineComputer pluged in with cosineSimilarity and neighborhood 
	 * @param userID 
	 * @param itemID
	 * @return KNNCosineComputer  
	 * 
	 */
	public KNNCosineComputer getKNNCosinePearsonComputer (String userID, String itemID){
		CosineSimilarity cosineSimilarity = new CosineSimilarity(userMap, userID);
		KNearestNeighbor kNearestNeighbor = new KNearestNeighbor(20, itemMap, itemID, cosineSimilarity);
		ArrayList<String[]> neighborhoodList = kNearestNeighbor.getNeighborhood();
		return new KNNCosineComputer(neighborhoodList, cosineSimilarity, userID, itemID); 
	}

}
