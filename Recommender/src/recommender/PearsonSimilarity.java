package recommender;

import java.util.HashMap;
import java.util.Map;

/**
 * PearsonSimilarity class implments Similarity interface
 * @author paulhsu
 *
 */
public class PearsonSimilarity implements Similarity{
	private HashMap<String, HashMap<String, Double>> userMap;
	private String userID;
	
	/**
	 * PearsonSimilarity constructor
	 * @param userMap
	 * @param userID
	 */
	public PearsonSimilarity(HashMap<String, HashMap<String, Double>> userMap, String userID) {
		this.userMap = userMap;
		this.userID = userID;
	}

	/**
	 * Compute PearsonSimilarity between userID and user2ID by pearson 
	 * @param user2ID
	 */
	@Override
	public double computeSimilarity(String user2ID) {
		double numerator = 0, denominator1 = 0, denominator2 = 0;
		HashMap<String, Double> itemMap1 = userMap.get(userID);
		HashMap<String, Double> itemMap2 = userMap.get(user2ID);
		double avgUser1 = computeAvg(userID);
		double avgUser2 = computeAvg(user2ID);
		for (Map.Entry<String, Double> itemEntry: itemMap1.entrySet()) {
			String itemID = itemEntry.getKey();
			if (itemMap2.containsKey(itemID)) {
				double rate1 = itemMap1.get(itemID);
				double rate2 = itemMap2.get(itemID);
				numerator += (rate1-avgUser1)*(rate2-avgUser2);
				denominator1 += Math.pow(rate1-avgUser1, 2);
				denominator2 += Math.pow(rate2-avgUser2, 2);
			}
		}
		return computeFormula(numerator, denominator1, denominator2);
	}
	
	/**
	 * Compute average rating for given userID
	 * @param userID
	 * @return double rating
	 */
	public double computeAvg(String userID) {
		int totalRating = 0;
		HashMap<String, Double> itemMap = userMap.get(userID);
		for (Map.Entry<String, Double> itemEntry: itemMap.entrySet()) {
			totalRating += itemEntry.getValue();
		}
		double avgRating = Math.floor((totalRating/(double)itemMap.size()) * 1000)/1000;
		return avgRating;
	}
	
	/**
	 * Computer pearson correlation, and if result is NaN, 
	 * compute it to be 0  
	 * @param numerator
	 * @param denominator1
	 * @param denominator2
	 * @return double pearson results
	 */
	public double computeFormula(double numerator, double denominator1, double denominator2) {
		double pearson = 0;
		if (denominator1 == 0 || denominator2 == 0) {
			return 0;
		} else {
			double denominator = Math.sqrt(denominator1)*Math.sqrt(denominator2);
			pearson = numerator/denominator;
		}
		return pearson;
	}
	
	/**
	 * Get userid for this class
	 * @return int userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Get user hash map
	 * @return HashMap<Integer, HashMap<Integer, Double>> userMap
	 */
	public HashMap<String, HashMap<String, Double>> getUserMap() {
		return userMap;
	}
}
