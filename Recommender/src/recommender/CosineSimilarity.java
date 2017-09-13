package recommender;

import java.util.HashMap;
import java.util.Map;

/**
 * CosineSimilarity class implments Similarity interface
 * @author paulhsu
 *
 */
public class CosineSimilarity implements Similarity{
	private HashMap<String, HashMap<String, Double>> userMap;
	private String userID;
	
	
	/**
	 * CosineSimilarity constructor
	 * @param userMap
	 * @param userID
	 */
	public CosineSimilarity(HashMap<String, HashMap<String, Double>> userMap, String userID) {
		this.userMap = userMap;
		this.userID = userID;
	}

	/**
	 * Compute similarity between userID and user2ID by cosine
	 */
	@Override
	public double computeSimilarity(String user2ID) {
		double numerator = 0;
		HashMap<String, Double> itemMap1 = userMap.get(userID);
		HashMap<String, Double> itemMap2 = userMap.get(user2ID);
		for (Map.Entry<String, Double> itemEntry: itemMap1.entrySet()) {
			String itemID = itemEntry.getKey();
			if (itemMap2.containsKey(itemID)) {
				double rate1 = itemEntry.getValue();
				double rate2 = itemMap2.get(itemID);
				numerator += rate1*rate2;
			}
		}
		return computeFormula(numerator, squareAndSumRatings(userID), squareAndSumRatings(user2ID));
	}

	/**
	 * Computer cosine correlation, and if result is NaN, 
	 * compute it to be 0  
	 * @param numerator
	 * @param denominator1
	 * @param denominator2
	 * @return double cosine results
	 */
	@Override
	public double computeFormula(double numerator, double denominator1, double denominator2) {
		double cosine = 0; 
		if (denominator1 == 0 || denominator2 == 0) {
			return 0;
		} else {
			double denominator = Math.sqrt(denominator1)*Math.sqrt(denominator2);
			cosine = numerator/denominator;
		}
		return cosine;
	}
	
	/**
	 * Saures and sums ratings for given user
	 * @param userID
	 * @return the summation
	 */
	public double squareAndSumRatings(String userID) {
		double result = 0;
		HashMap<String, Double> itemMap = userMap.get(userID);
		for (Map.Entry<String, Double> itemEntry: itemMap.entrySet()) {
			result += itemEntry.getValue()*itemEntry.getValue();
		}
		return result;
	}
	
	/**
	 * Get user hash map
	 * @return HashMap<Integer, HashMap<Integer, Double>> userMap
	 */
	public HashMap<String, HashMap<String, Double>> getUserMap() {
		return userMap;
	}
	

}
