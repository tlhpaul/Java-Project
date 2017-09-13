import java.util.ArrayList;
import java.util.Collections;

/**
 * This is board class
 * It handles player board and food board.
 * 
 * @author paulhsu
 *
 */
public class Board {
	
	private int[][] board;
	private int [][] foodOnBoard;
	private GameConfig game;
	
	/**
	 * Create the boards for player and food.
	 * @param row the length of row
	 * @param column the length of column
	 * @param game The GameConfig Interface that contains game-related information
	 */
	public Board(int row, int column, GameConfig game){
		this.game = game;
		this.board =  new int[row][column];
		this.foodOnBoard =  new int[row][column];
		for (int i = 0; i < row; i ++){
			for (int j = 0; j < column; j ++){
				board[i][j] = -1;
				foodOnBoard[i][j] = 0;
			}
		}
	}
	
	
	/**
	 * Update the player board
	 * @param row the row would be updated 
	 * @param column the column would be updated 
	 * @param key the player's key
	 */
	public void updateBoard(int row, int column, int key){
		row = checkIndexOutOfBound(row);
		column = checkIndexOutOfBound(column);
		board[row][column] = key;
	}
	
	/**
	 * Set new player at certain place 
	 * @param row the row where new player to be placed
	 * @param column the column where new player to be placed
	 * @param key the player's key
	 */
	public void setKey(int row, int column, int key){
		board[row][column] = key;
	}
	
	/**
	 * Checks the grid is occupied. 
	 * @param row the row would be checked
	 * @param column the column would be checked
	 * @return true if that grid is keyed
	 */
	public boolean isKeyed(int row, int column){
		return board[row][column] != -1; 
	}
	 
	/**
	 * Checks what type of organism is at certain grid.
	 * @param row the row would be checked
	 * @param column the column would be checked
	 * @return the player's key on that grid
	 */
	public int at(int row, int column){
		row = checkIndexOutOfBound(row);
		column = checkIndexOutOfBound(column);
		return board[row][column];
	}
	
	/**
	 * Produce food
	 * @param p probability of spontaneous appearance of food
	 */
	public void producingFood(double p){
		int index = 0;
		ArrayList<Integer> possibilityFood = new ArrayList<Integer>();
		possibilityFood = randomList(p);
		for (int i = 0; i < board.length; i ++){
			for (int j = 0; j < board[0].length; j ++){
				if (board[i][j] == -1 && foodOnBoard[i][j] == 0 && possibilityFood.get(index) == 1){
					foodOnBoard[i][j] = 1;
				}
				index = index + 1;
			}
		}
	}
	
	/**
	 * Double the food
	 * @param q probability of food doubling
	 */
	public void doublingFood(double q){
		int count = 0;
		int originalFood;
		ArrayList<Integer> possibilityFood = new ArrayList<Integer>();
		possibilityFood = randomList(q);
		for (int i = 0; i < board.length; i ++){
			for (int j = 0; j < board[0].length; j ++){
				if (board[i][j] == -1 && foodOnBoard[i][j] != 0){
					originalFood = foodOnBoard[i][j];
					for(int k = 0;  k < originalFood; k ++){
						if (possibilityFood.get(count) == 1 && foodOnBoard[i][j] < game.K()) {
							foodOnBoard[i][j] ++;
						}
					}
				}
				count++;
			}
		}
	}
	
	/**
	 * Check is there any food in that grid
	 * @param row the row would be checked
	 * @param column the column would be checked
	 * @return true if there is food on that grid
	 */
	public boolean foodHere(int row, int column){
		row = checkIndexOutOfBound(row);
		column = checkIndexOutOfBound(column);
		return foodOnBoard[row][column] > 0;
	}
	
	/**
	 * Update the food amount
	 * @param row the row would be checked
	 * @param column the column would be checked
	 * @param consumed the amount of food consumed by player
	 */
	public void foodEaten(int row, int column, int consumed){
		foodOnBoard[row][column] = foodOnBoard[row][column] - consumed;
	}
	
	/**
	 * Get the amount of food in that grid 
	 * @param row the row would be checked
	 * @param column the column would be checked
	 * @return
	 */
	public int foodLeft(int row, int column){
		return foodOnBoard[row][column];
	}
	
	/**
	 * Print the food board
	 */
	public void printFoodOnBoard(){
		for (int i = 0; i < foodOnBoard.length; i ++){
			for (int j = 0; j < foodOnBoard[0].length; j ++){
				System.out.print(foodOnBoard[i][j] + " ");
				
			}
			System.out.println();
		}
	}
	
	/**
	 * Print the food board
	 */
	public void printBoard(){
		for (int i = 0; i < board.length; i ++){
			for (int j = 0; j < board[0].length; j ++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Produce a random list according to probability n 
	 * @param n Probability 
	 * @return a random list with n "1" and (1000-n) "0".
	 */
	public ArrayList<Integer> randomList(double n){
		ArrayList<Integer> randomList = new ArrayList<Integer>();
		double m =  n * 1000;
		for (int i = 0;  i < m; i ++){
			randomList.add(1);
		}
		for(double j = m; j < 1000; j++){
			randomList.add(0);
		}
		Collections.shuffle(randomList);
		return randomList;
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
	 * Get the length of row
	 * @return length of row
	 */
	public int getBoardRowLength(){
		return board.length;
	}
	
	/**
	 * Get the length of column
	 * @return length of column
	 */
	public int getBoardColumnLength(){
		return board[0].length;
	}
}
	
