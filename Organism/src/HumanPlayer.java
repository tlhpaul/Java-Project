import java.util.Scanner;

/**
 * This is human player class. 
 * Users can control where their players go.
 * @author paulhsu
 * 
 *
 */
public class HumanPlayer implements Player {
	private int key;
	private String name;
	private GameConfig game;
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * Constructor for human player class. 
	 * Users can enter their name as the name of the organism.
	 * @param name
	 */
	public HumanPlayer(String name) {
		this.name = name;
	}

	/**
	 * The name of the organism and user's name. 
	 * Return user's name.
	 */
	@Override
	public String name() {
		return name;
	}

	/**
	 * This method will be called once when the game starts.
	 * It will provide the GameConfig information and the key.
	 * @param game The GameConfig Interface that contains game-related information
	 * @param key the key passed down from the parent.
	 */
	@Override
	public void register(GameConfig game, int key) {
		this.key = key;
		this.game = game;
	}
	
	/**
	 * 
	 * This is called by the Game to determine how human player should move.
	 * @param food a five-element array that indicates whether any food is in adjacent squares
	 * @param neighbors a five-element array that holds the details for any organism in an adjacent square. -1 is no organism present, any value >= 0 if organism present
	 * 
	 * @param foodleft how much food is left on the current square
	 * @param energyleft the human player's remaining energy
	 * @return move class
	 */
	@Override
	public Move move(boolean[] food, int[] neighbors, int foodleft, int energyleft) {
		int direction = 0, childDirection = 0; 
		Move m = null;
		m = new Move(10, 10, 10);
		System.out.println("Hi " + name + "! It is your turn!");
		String[] Directions = { "Stayput", "West", "East", "North", "South" };
		for (int i = 1; i < neighbors.length; i ++){
			if (i == 1) {
				if (neighbors[i] >= 0) System.out.println("There is an organism on the west grid.");
				else System.out.println("There is no organism on the west grid.");
			}
			else if (i == 2){
				if (neighbors[i] >= 0) System.out.println("There is an organism on the east grid.");
				else System.out.println("There is no organism on the east grid.");
			}
			else if (i == 3) {
				if (neighbors[i] >= 0) System.out.println("There is an organism on the north grid.");
				else System.out.println("There is no organism on the north grid.");
			}
			else if (i == 4) {
				if (neighbors[i] >= 0)System.out.println("There is an organism on the south grid.");
				else System.out.println("There is no organism on the south grid.");
			}
		}
		for (int i = 1; i < food.length; i ++){
			if (i == 1) {
				if (food[i]) System.out.println("There is some food on the west grid.");
				else System.out.println("There is no food on the west grid.");
			}
			else if (i == 2) {
				if (food[i]) System.out.println("There is some food on the east grid.");
				else System.out.println("There is no food on the east grid.");
			}
			else if (i == 3){
				if (food[i]) System.out.println("There is some food on the north grid.");
				else System.out.println("There is no food on the north grid.");
			}
			else if (i == 4) {
				if(food[i]) System.out.println("There is some food on the south grid.");
				else System.out.println("There is no food on the south grid.");
			}
		}
		System.out.println("The food left in your current grid is " + foodleft + ".");
		System.out.println("The energy this organism left is " + energyleft  + ".");
		System.out.println("Where do you want to move? ");
		System.out.print("Enter \"0\" to Stay put, \"1\" to go west, \"2\" to go east, \"3\" to go north and \"4\" to go south, \"5\" to reproduce: ");
		System.out.println(); 
		try {
			direction = Integer.parseInt(scanner.next());
		} catch (NumberFormatException e) {
			System.out.println("That is not a number! Stay at the same place as punishment!");
			direction = 0;
		}
		switch (direction) {
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
			for (int j = 1; j < neighbors.length; j ++){
				if (neighbors[j] == -1) System.out.println("You could place the replicate on "+ Directions[j] + " grid");
			}
			System.out.println("Enter \"1\" to place west, \"2\" to place east, \"3\" to place north and \"4\" to place south: ");
			try {
				childDirection = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("That is not a number! Stay at the same place as punishment!");
				direction = 0;
			}
			if (childDirection == 1)
				m = new Move(Constants.REPRODUCE, Constants.WEST, key);
			else if (childDirection == 2)
				m = new Move(Constants.REPRODUCE, Constants.EAST, key);
			else if (childDirection == 3)
				m = new Move(Constants.REPRODUCE, Constants.NORTH, key);
			else if (childDirection == 4)
				m = new Move(Constants.REPRODUCE, Constants.SOUTH, key);	
			break;
		}
		return m;
	}
}
