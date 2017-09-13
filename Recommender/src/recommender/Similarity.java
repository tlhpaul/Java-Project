package recommender;

/**
 * Similarity interface, supporting different way to calculate similarity 
 * @author paulhsu
 *
 */
public interface Similarity {
	
	/**
	 * Compute similarity
	 * @param userID
	 * @return
	 */
	public double computeSimilarity(String userID);
	
	/**
	 * Computer certain correlation, and if result is NaN, 
	 * compute it to be 0  
	 * @param numerator
	 * @param denominator1
	 * @param denominator2
	 * @return
	 */
	public double computeFormula(double numerator, double denominator1, double denominator2);
	
}
