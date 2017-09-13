import java.util.ArrayList;

/**
 * Trackplayer class is for keeping track of how each organism does in a round. 
 * @author paulhsu
 *
 */
public class TrackPlayer implements PlayerRoundData {
	private int key;
	private Player player;
	private ArrayList<PlayerInfo> playerInfoList;
	
	/**
	 * Constructor of this class 
	 * @param player player
	 * @param key player's key 
	 * @param playerInfoList arraylist of player's info
	 */
	public TrackPlayer(Player player, int key, ArrayList<PlayerInfo> playerInfoList) {
		this.key = key; 
		this.player = player;
		this.playerInfoList = playerInfoList;
	}
	
	/**
	 * A unique player ID to distinguish different players
	 * @return player id
	 */
	@Override
	public int getPlayerId() {
		return key;
	}

	/**
	 * Total Energy left for certain type of organism
	 * @return the total energy
	 */
	@Override
	public int getEnergy() {
		int tempEnergy = 0;
		for(int i = 0; i < playerInfoList.size(); i ++){
			if (playerInfoList.get(i).getKey() == key){
				tempEnergy += playerInfoList.get(i).getEnergy();
			}
		}
		return tempEnergy;
	}

	/**
	 * The Total Count for this type of organism
	 * @return the total count
	 */
	@Override
	public int getCount() {
		int count = 0;
		for(int i = 0; i < playerInfoList.size(); i ++){
			if (playerInfoList.get(i).getKey() == key){
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * Overrides the toString method. 
	 */
	@Override
	public String toString(){
		return player.name();
	}
	
}
