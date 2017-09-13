package recommender;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * KNNPearsonComputer class implements PredictionComputer interface
 * @author paulhsu
 *
 */
public class KNNPearsonComputer implements PredictionComputer{
	private ArrayList<String[]> neighborhood; 
	private PearsonSimilarity pearsonSimilarity;
	private String itemID;
	private String userID;
	
	/**
	 * KNNPearsonComputer constructor
	 * @param neighborhood
	 * @param userSimilarity
	 * @param userID
	 * @param itemID
	 */
	public KNNPearsonComputer(ArrayList<String[]> neighborhood, 
			PearsonSimilarity pearsonSimilarity, String userID, String itemID) {
		this.neighborhood = neighborhood;
		this.pearsonSimilarity = pearsonSimilarity;
		this.userID = userID;
		this.itemID = itemID;
	}
	
	/**
	 * Predicts rating by using neighborhood, pearsonSimilarity,
	 * userID and itemID
	 */
	public double predictRating() {
		double numerator = 0, denominator = 0;
		for (int i = 0; i < neighborhood.size(); i ++) {
			String[] neighbor = neighborhood.get(i);
			String userID = neighbor[0];
			double similarity = Double.parseDouble(neighbor[1]);
			HashMap<String, HashMap<String, Double>> userMap = pearsonSimilarity.getUserMap();
			HashMap<String, Double> itemMap = userMap.get(userID);
			double rating = itemMap.get(itemID);
			double userAvg = pearsonSimilarity.computeAvg(userID);
			numerator += similarity*(rating-userAvg);
			denominator += Math.abs(similarity);
		}
		double avg = pearsonSimilarity.computeAvg(userID);
		double predicetdRating = (denominator == 0) ? avg :(numerator/denominator) + avg;
		return predicetdRating;
	}
	
}
