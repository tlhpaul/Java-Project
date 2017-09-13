package recommender;

import java.util.HashMap;

/**
 * UserEngine class, handles which approach user want to predict the ratings 
 * @author paulhsu
 *
 */
public class UserEngine {
	private HashMap<String, HashMap<String, Double>> userMap;
	private HashMap<String, HashMap<String, Double>> itemMap;
	private int approach;
	private String userID; 
	private String itemID;
	private int threshold;
	
	/**
	 * UserEngine constructor for predictRate
	 * @param approach
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param itemID
	 */
	public  UserEngine(int approach, HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, String itemID) {
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.approach = approach;
		this.userID = userID;
		this.itemID = itemID;
	}
	
	/**
	 * UserEngine constructor for predictPreference 
	 * @param approach
	 * @param userMap
	 * @param itemMap
	 * @param userID
	 * @param threshold
	 */
	public  UserEngine(int approach, HashMap<String, HashMap<String, Double>> userMap, 
			HashMap<String, HashMap<String, Double>> itemMap, String userID, int threshold) {
		this.userMap = userMap;
		this.itemMap = itemMap;
		this.approach = approach;
		this.userID = userID;
		this.threshold = threshold;
	}
	
	/**
	 * Predicts rate depending which approach user selects 
	 */
	public void predictRating() {
		switch (approach) {
		case 1:
			BaselineEngine baselineEngine = new BaselineEngine(userMap, itemMap, userID);
			baselineEngine.predictRating(userID, itemID);
			break;
		case 2:
			KNNPearsonEngine knnPearsonEngine = new KNNPearsonEngine(userMap, itemMap, userID);
			knnPearsonEngine.predictRating(userID, itemID);
			break;
		case 3:
			KNNCosineEngine knnCosineEngine = new KNNCosineEngine(userMap, itemMap, userID);
			knnCosineEngine.predictRating(userID, itemID);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Predicts top n preferences depending which approach user selects 
	 */
	public void predictPreference() {
		switch (approach) {
		case 1:
			BaselineEngine baselineEngine = new BaselineEngine(userMap, itemMap, userID, threshold);
			baselineEngine.predictPreference();
			break;
		case 2:
			KNNPearsonEngine knnPearsonEngine = new KNNPearsonEngine(userMap, itemMap, userID, threshold);
			knnPearsonEngine.predictPreference();
			break;
		case 3:
			KNNCosineEngine knnCosineEngine = new KNNCosineEngine(userMap, itemMap, userID, threshold);
			knnCosineEngine.predictPreference();
			break;
		default:
			break;
		}
	}
}
