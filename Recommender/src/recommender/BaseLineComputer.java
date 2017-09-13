package recommender;

import java.util.HashMap;
import java.util.Map;


/**
 * Baseline computer implements PredictionComputer interface
 * @author paulhsu
 *
 */
public class BaseLineComputer implements PredictionComputer{
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private double avg;
	private String userID;
	private String itemID;
	
	
	/**
	 * Baseline computer constructor 
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param itemID
	 */
	public BaseLineComputer(HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, String itemID) {
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.userID = userID;
		this.itemID = itemID;
		avg = 0;
	}

	/**
	 * Predicts rating by baseline prediciton 
	 */
	@Override
	public double predictRating() {
		computeOverallAvgRating();
		return avg + baselineForUser(userID) + baselineForItem(itemID);
	}
	
	
	/**
	 * Computes overall average ratings
	 */
	public void computeOverallAvgRating() {
		double num = 0;
		double totalValue = 0;
		for (Map.Entry<String, HashMap<String, Double>> userEntry: userMap.entrySet()) {
			HashMap<String, Double> itemMap = (HashMap<String, Double>) userEntry.getValue();
			for (Map.Entry<String, Double> itemEntry: itemMap.entrySet()) {
				totalValue += itemEntry.getValue();
				num ++;
			}
		}
		avg = totalValue/num;
	}
	
	/**
	 * Computes user baseline predictor
	 * @param userID
	 * @return user baseline predictor
	 */
	public double baselineForUser(String userID) {
		HashMap<String, Double> tempMap = userMap.get(userID);
		int size = tempMap.size();
		double totalValue = 0;
		for (Map.Entry<String, Double> itemEntry:tempMap.entrySet()) {
			totalValue += itemEntry.getValue();
		}
		return (totalValue- avg)/size;
	}
	
	/**
	 * Computes item baseline predictor
	 * @param itemID
	 * @return item baseline predictor
	 */
	public double baselineForItem(String itemID) {
		HashMap<String, Double> tempMap = itemMap.get(itemID);
		int size = tempMap.size();
		double totalValue = 0;
		double totalBaseLineForUser = 0;
		for (Map.Entry<String, Double> userEntry:tempMap.entrySet()) {
			totalValue += userEntry.getValue();
			totalBaseLineForUser += baselineForUser(userEntry.getKey());
		}
		return ((totalValue - totalBaseLineForUser)/size) - avg;
	}

}
