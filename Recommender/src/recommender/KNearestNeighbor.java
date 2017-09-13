package recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * UserNeighborhood class implments Neighborhood interface
 * @author paulhsu
 *
 */
public class KNearestNeighbor implements Neighborhood{
	private int thresholdSize;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private String itemID;
	private Similarity similarity;
	
	/**
	 * UserNeighborhood constructor
	 * @param size 
	 * @param itemMap
	 * @param itemID
	 * @param userSimilarity
	 */
	public KNearestNeighbor(int thresholdSize, HashMap<String, HashMap<String, Double>> itemMap, String itemID, 
			Similarity similarity) {
		this.thresholdSize = thresholdSize;
		this.itemMap = itemMap;
		this.itemID = itemID;
		this.similarity = similarity;
	}
	
	/**
	 * Find the top highest users' similarities (based on threshold size) 
	 * and return an arraylist with user id and ratings
	 */
	public ArrayList<String[]> getNeighborhood() {
		HashMap<String, Double> neighborhood = itemMap.get(itemID);
		ArrayList<String[]> results = new ArrayList<String[]>();
		ArrayList<String[]> finalResults = new ArrayList<String[]>();
		for (Map.Entry<String, Double> singleNeighbor:neighborhood.entrySet()) {
			String userID = singleNeighbor.getKey();
			String similarityScore = String.valueOf(similarity.computeSimilarity(singleNeighbor.getKey()));
			String [] temp = {userID, similarityScore};
			results.add(temp);
		}
		results = sortList(results);
		thresholdSize = results.size() > thresholdSize ? thresholdSize : results.size();
		for (int i = 0; i < thresholdSize; i ++) {
			finalResults.add(results.get(i));
		}
		return finalResults;
	}	
	
	/**
	 * Sort the arraylist based on the rating value
	 * @param results ArrayList<Double[]> results
	 * @return sorted arrayList
	 */
	public ArrayList<String[]> sortList(ArrayList<String[]> results) {
		Collections.sort(results, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o2[1].compareTo(o1[1]);
			}
		});
		return results;
	}
}
