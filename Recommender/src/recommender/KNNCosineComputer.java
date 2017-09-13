package recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * KNNCosineComputer implements PredictionComputer interface
 * @author paulhsu
 *
 */
public class KNNCosineComputer implements PredictionComputer{
	private ArrayList<String[]> neighborhood; 
	private CosineSimilarity cosineSimilarity;
	private String itemID;
	private String userID;
	
	/**
	 * KNNCosineComputer constructor
	 * @param neighborhood
	 * @param cosineSimilarity
	 * @param userID
	 * @param itemID
	 */
	public KNNCosineComputer(ArrayList<String[]> neighborhood, 
			CosineSimilarity cosineSimilarity, String userID, String itemID) {
		this.neighborhood = neighborhood;
		this.cosineSimilarity = cosineSimilarity;
		this.userID = userID;
		this.itemID = itemID;
	}
	
	/**
	 * Predict rating by using neighborhood, cosineSimilarity,  
	 * userID and itemID
	 */
	public double predictRating() {
		double numerator = 0, denominator = 0;
		for (int i = 0; i < neighborhood.size(); i ++) {
			String[] neighbor = neighborhood.get(i);
			String userID = neighbor[0];
			double similarity = Double.parseDouble(neighbor[1]);
			HashMap<String, HashMap<String, Double>> userMap = cosineSimilarity.getUserMap();
			HashMap<String, Double> itemMap = userMap.get(userID);
			double rating = itemMap.get(itemID);
			double userAvg = computeAvg(userID);
			numerator += similarity*(rating-userAvg);
			denominator += Math.abs(similarity);
		}
		double avg = computeAvg(userID);
		double predicetdRating = (denominator == 0) ? avg :(numerator/denominator) + avg;
		return predicetdRating;
	}
	
	/**
	 * Computes the average for given user's ratings. 
	 * @param userID
	 * @return average
	 */
	public double computeAvg(String userID) {
		int totalRating = 0;
		HashMap<String, HashMap<String, Double>> userMap = cosineSimilarity.getUserMap();
		HashMap<String, Double> itemMap = userMap.get(userID);
		for (Map.Entry<String, Double> itemEntry: itemMap.entrySet()) {
			totalRating += itemEntry.getValue();
		}
		double avgRating = Math.floor((totalRating/(double)itemMap.size()) * 1000)/1000;
		return avgRating;
	}

}
