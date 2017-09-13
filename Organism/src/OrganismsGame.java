import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


/**
 * This is the Game extending OrganismsGameInterface.
 * A Game simulator will run and will be the starting point of running the game.
 * @author paulhsu
 *
 */
public class OrganismsGame implements OrganismsGameInterface {
	private double doublingRate, producingRate;
	private final int BOARD_LEN = 10;
	private TrackPlayer trackPlayer;
	private GameConfig game;
	private ArrayList<PlayerRoundData> trackPlayersList = new ArrayList<PlayerRoundData>();
	private Board board;
	private ArrayList<Player> players;
	private PlayerInfo playerinfo;
	private int round = 1;
	ArrayList<PlayerInfo> playerInfoList = new ArrayList<PlayerInfo>();
	Scanner scanner = new Scanner(System.in);
	Random random = new Random();

	/**
	 * This method will initialize the game.
	 * Each game will run for 5000 rounds.
	 * Each player will start with an energy of 500 at the start of the game.
	 * @param game the GameConfig to run
	 * @param p the secret parameter p - probability of spontaneous appearance of food
	 * @param q the secret parameter q - probability of food doubling
	 * @param players the list of players
	 */
	@Override
	public void initialize(GameConfig game, double p, double q, ArrayList<Player> players) {
		this.game = game;
		board = new Board(BOARD_LEN, BOARD_LEN, game);
		this.producingRate = p;
		doublingRate = q; 
		board.producingFood(p);
		int row = random.nextInt(BOARD_LEN);
		int column = random.nextInt(BOARD_LEN);
		this.players = players;
		
		for (int i = 0; i < players.size(); i++){
			trackPlayer = new TrackPlayer(players.get(i), i, playerInfoList);
			trackPlayersList.add(trackPlayer);
			players.get(i).register(game, i);
			
			Player player = players.get(1);
			System.out.println("=====>" + player.getClass().getCanonicalName());
			
			while (board.isKeyed(row, column)){
				row = random.nextInt(BOARD_LEN);
				column = random.nextInt(BOARD_LEN);	
			}
			board.setKey(row, column, i);
			playerinfo = new PlayerInfo(i, row, column, round, game.M());
			playerInfoList.add(playerinfo);
		}
	}

	/**
	 * This method will play the game for the given configuration, and takes turn for each player from top-left to bottom-right. 
	 * @return true if the game ended normally, false if exceptions were thrown or unexpected behavior
	 */
	@Override
	public boolean playGame(){
		int tempRound = round;
		Move m = null;
		int type, childpos;	
		Collections.sort(playerInfoList, new PlayerComparator());
		if (playerInfoList.size() != 0){
			for (int i = 0; i < playerInfoList.size(); i ++){
				if (playerInfoList.get(i).getRoundNumber() != tempRound){
					int tempRow, tempColumn, childRow, childColumn, energy = 0, row, column;
					playerInfoList.get(i).setRoundNumber(tempRound);
					row = playerInfoList.get(i).getRow();
					column = playerInfoList.get(i).getColumn();
					eatFood(row, column, playerInfoList.get(i), board);
					energy = playerInfoList.get(i).getEnergy();
					boolean[] food = new boolean[]{true, board.foodHere(row, column-1), 
							board.foodHere(row, column+1), board.foodHere(row-1, column), board.foodHere(row+1, column)};
					int[] neighbors = new int[]{0, board.at(row, column-1), 
							board.at(row, column+1), board.at(row-1, column), board.at(row+1, column)};
					m = players.get(playerInfoList.get(i).getKey()).move(food, neighbors, board.foodLeft(row, column), energy);
					type = m.type();
					childpos = m.childpos();
					tempRow = row;
					tempColumn = column;	
					if (type == 10 || (type == 5 && neighbors[childpos] >= 0) || (type == 5 && childpos == 10)) {
						type = 0; 
						m.setType(0);
						System.out.println("Error! You made an illegal move! Stay at the same place as punishment!");
					}
					if (type != 5 && type != 0) {
						if (neighbors[type] >= 0) {
							type = 0; 
							m.setType(0);
							System.out.println("Error! You made an illegal move! Stay at the same place as punishment!");
						}
						playerInfoList.get(i).energyUpdate(energy - game.v());
						board.updateBoard(tempRow, tempColumn, -1);
					} 
					switch(type) {
					case 0:
						playerInfoList.get(i).energyUpdate(energy - game.s());
						break;
					case 1:
						-- column;
						break;
					case 2:
						++ column;
						break;
					case 3:
						-- row;
						break;
					case 4:
						++ row;
						break;
					case 5:
						int tempEnergy = (energy - game.v())/2;
						playerInfoList.get(i).energyUpdate(tempEnergy);
						childRow = row;
						childColumn = column;
						if (childpos == 1)
							-- childColumn;
						else if (childpos == 2)
							++ childColumn;
						else if (childpos == 3)
							-- childRow;
						else if (childpos == 4)
							++ childRow;
						childRow = checkIndexOutOfBound(childRow);
						childColumn = checkIndexOutOfBound(childColumn);
						playerinfo = new PlayerInfo(playerInfoList.get(i).getKey(), childRow, childColumn, tempRound, tempEnergy);
						playerInfoList.add(playerinfo);
						board.updateBoard(childRow, childColumn, playerInfoList.get(i).getKey());
						break;
					}
					if (aliveCheck(playerInfoList.get(i))){
						row = checkIndexOutOfBound(row);
						column = checkIndexOutOfBound(column);
						playerInfoList.get(i).updateRow(row);
						playerInfoList.get(i).updateColumn(column);
						board.updateBoard(row, column, playerInfoList.get(i).getKey());
					} else {
						playerInfoList.remove(i);
						board.updateBoard(tempRow, tempColumn, -1);
					}
					try{
					System.out.println(players.get(playerInfoList.get(i).getKey()).name() + " \"" + m.toString() + "\" this round.");
					System.out.println();
					} catch (IndexOutOfBoundsException e){}
				}
			}
			board.doublingFood(doublingRate);
			board.producingFood(producingRate);
			return true;
		} else return false;
	}
	
	/**
	 * The list of results for all the players
	 * @return an ArrayList of PlayerRoundData objects
	 */
	@Override
	public ArrayList<PlayerRoundData> getResults() {
		return trackPlayersList;
	}
	
	/**
	 * Wrapping the board. 
	 * @param num Number to be checked 
	 * @return the right number if the world is wrapped.
	 */
	public int checkIndexOutOfBound(int num){
		if (num == 10) num = 0;
		else if (num == -1) num = 9;
		return num;
	}
	
	/**
	 * Set the number of round to the player has already moved. 
	 * @param round the number of round
	 */
	public void setRound(int round){
		this.round = round;
	}
	
	/**
	 * Checks the player is still alive
	 * @param playerInfo the "body" of the player
	 * @return true if player is alive, false if player is dead.
	 */
	public boolean aliveCheck(PlayerInfo playerInfo){
		if (playerInfo.getEnergy() > 0) return true;
		return false;
	}
	
	/**
	 * Execute uptake of food for players.
	 * @param row the row which player is at 
	 * @param column the column which player is at 
	 * @param playerInfo the "body" of the player
	 * @param board the board which player at
	 */
	public void eatFood(int row, int column, PlayerInfo playerInfo, Board board){
		int energy = 0;
		energy = playerInfo.getEnergy();
		if ((energy <= game.M()-game.u()) && board.foodHere(row, column)){
			if (energy + board.foodLeft(row, column) * game.u() >= game.M()){
				int initialFood = board.foodLeft(row, column);
				int consumingFood = (game.M() - energy)/game.u();
				playerInfo.energyUpdate(energy + consumingFood * game.u());
				board.foodEaten(row, column, initialFood - consumingFood);
			} else {
				playerInfo.energyUpdate(energy + board.foodLeft(row, column) * game.u());
				board.foodEaten(row, column, board.foodLeft(row, column));
			}
		}
	}
}
