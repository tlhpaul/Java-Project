package recommender;

import java.util.ArrayList;

/**
 * Neighboorhood interface
 * @author paulhsu
 *
 */
public interface Neighborhood {
	
	/**
	 * Gets neighborhood based on implmentation
	 * @return
	 */
	public ArrayList<String[]> getNeighborhood();

}
