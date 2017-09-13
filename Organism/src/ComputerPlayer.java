import java.util.ArrayList;



/**
 * This is computer player class. 
 * AI will move based on the instruction provided in this class. 
 * @author paulhsu
 *
 *
 */
public class ComputerPlayer implements Player {
	
	private int key;
	public GameConfig game;
	public static final String NAME = "AI";
	
	/**
	 * Constructor for computer player class. 
	 * Always return "AI"
	 * @param name
	 */
	@Override
	public void register(GameConfig game, int key) {
		this.game = game;
		this.key = key;
	}
	
	/**
	 * Return "AI"
	 */
	@Override
	public String name() {
		return NAME;
	}

	/**
	 * /*
	 * This is called by the Game to determine how computer player should move.
	 * @param food a five-element array that indicates whether any food is in adjacent squares
	 * @param neighbors a five-element array that holds the details for any organism in an adjacent square. -1 is no organism present, any value >= 0 if organism present
	 * 
	 * @param foodleft how much food is left on the current square
	 * @param energyleft the computer player's remaining energy
	 * @return move class
	 */
	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
		Move m = null;
		int direction = 0;
		int directionChild = 0;
		ArrayList<Integer> noNeighborsPosition = new ArrayList<Integer>();
		ArrayList<Integer> foodPosition = new ArrayList<Integer>();
		for (int i = 1; i < neighbors.length; i ++){
			if (neighbors[i] == -1) {
				noNeighborsPosition.add(i);
			}
		}
		for (int i = 1; i < food.length; i ++){
			if (food[i]) foodPosition.add(i);
		}
		if (foodPosition.size() == 0 && noNeighborsPosition.size() == 0) direction = 0;
		else if (energyleft >= game.M() - 200 && noNeighborsPosition.size() > 0){
			direction = 5;
			directionChild = noNeighborsPosition.get(0);
		} else {
			for (int i = 0; i < foodPosition.size(); i++){
				if (noNeighborsPosition.contains(foodPosition.get(i))) direction = foodPosition.get(i);
			}
		}
		switch(direction){
		case 0:
			m = new Move(Constants.STAYPUT);
			break;
		case 1:
			m = new Move(Constants.WEST);
			break;
		case 2:
			m = new Move(Constants.EAST);
			break;
		case 3:
			m = new Move(Constants.NORTH);
			break;
		case 4:
			m = new Move(Constants.SOUTH);
			break;
		case 5:
			if (directionChild == 1) m = new Move(Constants.REPRODUCE, Constants.WEST, key);
			else if (directionChild == 2) m = new Move(Constants.REPRODUCE, Constants.EAST, key);
			else if (directionChild == 3) m = new Move(Constants.REPRODUCE, Constants.NORTH, key);
			else m = new Move(Constants.REPRODUCE, Constants.SOUTH, key);
		}
		return m;		
	}
}

