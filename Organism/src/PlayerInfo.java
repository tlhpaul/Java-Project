
/**
 * This class is the "body" of the individual player.
 * It includes the energy, location(row and column), key and round number of certain organism.
 * 
 * @author paulhsu
 *
 */
public class PlayerInfo {
	private int energy;
	private int row;
	private int column;
	private int key;
	private int roundNumber;

	/**Constructor of this class
	 * 
	 * @param key player's key
	 * @param row the row which player is at
	 * @param column the column which player is at
	 * @param roundNumber the round number
	 * @param energy individual player's energy  
	 */
	public PlayerInfo(int key, int row, int column, int roundNumber, int energy){
		this.energy = energy;
		this.key = key;
		this.row = row;
		this.column = column;
		this.roundNumber = roundNumber;
	}
	/**
	 * Get the player's key 
	 * @return player's key 
	 */
	public int getKey(){
		return key;
	}

	/**
	 * Get the energy 
	 * @return player's energy 
	 */
	public int getEnergy(){
		return energy;
	}
	
	/**
	 * Update the row which player is at
	 * @param num the row which player is at
	 */
	public void updateRow(int num){
		row = num;
	}
		
	/**
	 * Update the column which player is at
	 * @param num the column which player is at
	 */
	public void updateColumn(int num){
		column = num;
	}
		
	/**
	 * Update the individual player's energy 
	 * @param newEnergy the individual player's energy 
	 */
	public void energyUpdate(int newEnergy){
		energy = newEnergy; 
	}
	
	/**
	 * Get the row which player is at
	 * @return the row which player is at
	 */
	public int getRow(){
		return row;
	}
		
	/**
	 * Get the column which player is at
	 * @return the column which player is at
	 */
	public int getColumn(){
		return column;
	}
	
	/**
	 * Set the round number 
	 * @param num the round number 
	 */
	public void setRoundNumber(int num){
		roundNumber = num;
	}
	
	/**
	 * Get the round number
	 * @return round number
	 */
	public int getRoundNumber(){
		return roundNumber;
	}
	
}
