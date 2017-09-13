/**
 * 
 */
package battleship;

import java.util.Arrays;
import java.util.Random;

/**
 * @author paulhsu
 *
 */
public class Ocean {
	Ship[][] ships;
	String[][] ships1;
	int shotsFired;
	int hitCount;
	Ship battleship = new Battleship();
	Ship cruiser = new Cruiser();
	Ship destroyer = new Destroyer();
	Ship submarine = new Submarine();
	Random rand = new Random();

	Ocean() {
		ships = new Ship[10][10];
		ships1 = new String[10][10];
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships.length; j++) {
				Ship emptysea = new EmptySea();
				ships[i][j] = emptysea;
			}
		}
		for (int i = 0; i < ships1.length; i++) {
			for (int j = 0; j < ships1.length; j++) {
				ships1[i][j] = ".";
			}
		}
		this.hitCount = 0;
		this.shotsFired = 0;
	}

	/**
	 * Places all ten ships randomly on the (initially empty) ocean.
	 */
	void placeAllShipsRandomly() {
		int randomRow = 0;
		int randomColumn = 0;
		boolean horizontal = true;
		placeOneTypeOfShipsRandomly(battleship, randomRow, randomColumn, horizontal);
		placeOneTypeOfShipsRandomly(cruiser, randomRow, randomColumn, horizontal);
		placeOneTypeOfShipsRandomly(destroyer, randomRow, randomColumn, horizontal);
		placeOneTypeOfShipsRandomly(submarine, randomRow, randomColumn, horizontal);
	}

	/**
	 * Checks the given location contains a ship, false if it does not.
	 * 
	 * @param row
	 *            Row number from player.
	 * @param column
	 *            Column number from player.
	 * @return Returns true if the given location contains a ship, false if it
	 *         does not.
	 */
	boolean isOccupied(int row, int column) {
		if (ships[row][column] instanceof EmptySea)
			return false;
		return true;
	}

	/**
	 * Checks the given location contains a real ship, still afloat,
	 * 
	 * @param row
	 *            Row number from player.
	 * @param column
	 *            Column number from player.
	 * @return Returns true if the given location contains a real ship, still
	 *         afloat, false if it does not.
	 */
	boolean shootAt(int row, int column) {
		shotsFired += 1;
		if (isOccupied(row, column)) {
			if (!ships[row][column].isSunk()) {
				ships[row][column].shootAt(row, column);
				hitCount += 1;
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the number of shots fired.
	 * 
	 * @return Returns the number of shots fired.
	 */
	int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Gets the number of hits recorded.
	 * 
	 * @return Returns the number of hits recorded.
	 */
	int getHitCount() {
		return hitCount;
	}

	/**
	 * Checks game is over or not.
	 * 
	 * @return Returns true if all ships have been sunk, otherwise false.
	 */
	boolean isGameOver() {
		int count = 0;
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships.length; j++) {
				if ("S".equals(ships[i][j].toString()))
					count += 1;
			}
		}
		if (count == 0)
			return true;
		return false;
	}

	/**
	 * Gets the the actual 10x10 array of ships.
	 * 
	 * @return Returns the actual 10x10 array of ships, not a copy.
	 */
	Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Prints the ocean.
	 */
	void print() {
		String[][] ocean = new String[11][11];
		ocean[0][0] = "\\";
		for (int i = 1; i < ocean.length; i++) {
			ocean[i][0] = Integer.toString(i - 1);
			ocean[0][i] = Integer.toString(i - 1);
		}
		for (int i = 1; i < ocean.length; i++) {
			for (int j = 1; j < ocean.length; j++) {
				ocean[i][j] = ships1[i - 1][j - 1].toString();
			}
		}
		for (int i = 0; i < ocean.length; i++) {
			System.out.println(Arrays.toString(ocean[i]));
		}
		System.out.println();
	}
	
	void prinout(){
		for (int i = 0; i < ships.length; i++) {
			System.out.println(Arrays.toString(ships[i]));
		}
	}

	/**
	 * Places one type of ships at one time.
	 * 
	 * @param ship
	 *            Either battleship, cruiser, destroyer or submarine.
	 * @param randomRow
	 *            Random row number.
	 * @param randomColumn
	 *            Random column number.
	 * @param horizontal
	 *            True for horizontal, false for vertical.
	 */
	void placeOneTypeOfShipsRandomly(Ship ship, int randomRow, int randomColumn, boolean horizontal) {
		Ship[] oneTypeOfShip = new Ship[ship.getNumberOfShip()];
		for (int i = 0; i < oneTypeOfShip.length; i++) {
			while (true) {
				randomRow = rand.nextInt(10);
				randomColumn = rand.nextInt(10);
				horizontal = rand.nextInt(2) == 0;
				if (ship.length == 4)
					oneTypeOfShip[i] = new Battleship();
				if (ship.length == 3)
					oneTypeOfShip[i] = new Cruiser();
				if (ship.length == 2)
					oneTypeOfShip[i] = new Destroyer();
				if (ship.length == 1)
					oneTypeOfShip[i] = new Submarine();
				oneTypeOfShip[i].setBowRow(randomRow);
				oneTypeOfShip[i].setBowColumn(randomColumn);
				oneTypeOfShip[i].setHorizontal(horizontal);
				if (oneTypeOfShip[i].okToPlaceShipAt(randomRow, randomColumn, horizontal, this))
					break;
			}
			oneTypeOfShip[i].placeShipAt(randomRow, randomColumn, horizontal, this);
		}
	}
}
