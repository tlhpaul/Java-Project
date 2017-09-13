package recommender;

/**
 * PredictionComputer interface, predicting rating 
 * @author paulhsu
 *
 */
public interface PredictionComputer {
	
	/**
	 * Predicts rating by using different similarity methods 
	 * @return
	 */
	public double predictRating();

}
