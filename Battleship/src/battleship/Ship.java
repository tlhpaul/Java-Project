package battleship;

/**
 * @author Tse-Lun Hsu
 *
 */
public abstract class Ship {
	int bowRow;
	int bowColumn;
	int length;
	int number;
	boolean horizontal;
	boolean[] hit;

	/**
	 * Gets length of this particular ship.
	 * 
	 * @return Returns the length of this particular ship.
	 */
	abstract int getLength();

	/**
	 * Gets the type of this ship.
	 * 
	 * @return Returns the type of this ship.
	 */
	abstract String getShipType();

	/**
	 * Gets the number of this ship.
	 * 
	 * @return Returns the number of this ship.
	 */
	abstract int getNumberOfShip();

	/**
	 * Gets bow row.
	 * 
	 * @return Returns bowRow.
	 */
	int getBowRow() {
		return this.bowRow;
	}

	/**
	 * Gets bow column.
	 * 
	 * @return Returns bowColumn.
	 */
	int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * Gets horizontal value.
	 * 
	 * @return Returns horizontal.
	 */
	boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * Sets the value of bow row.
	 * 
	 * @param row
	 *            bow row
	 * 
	 */
	void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Sets the value of bowColumn.
	 * 
	 * @param column
	 *            bow column.
	 * 
	 */
	void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * @param horizontal
	 *            Sets the value of the instance variable horizontal.
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * 
	 * Returns true if it is okay to put a ship of this length with its bow in
	 * this location, with the given orientation, and returns false otherwise.
	 * The ship must not overlap another ship, or touch another ship
	 * (vertically, horizontally, or diagonally), and it must not "stick out"
	 * beyond the array.
	 * 
	 * @param row
	 *            Row number of bow.
	 * @param column
	 *            Column number of bow.
	 * @param horizontal
	 *            Orientation of ship.
	 * @param ocean
	 *            Ocean class.
	 * @return Returns true if it is okay to put a ship of this length with its
	 *         bow in this location, with the given orientation, and returns
	 *         false otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (row < 0 || column < 0 || row > 9 || column > 9)
			return false;
		int count = 0;
		if (horizontal && column < ocean.ships.length + 1 - length) {
			for (int x = row - 1; x < row + 2; x++) {
				for (int y = column - 1; y < column + length + 1; y++) {
					if (0 <= x && 0 <= y && x < ocean.ships.length && y < ocean.ships.length) {
						if (ocean.ships[x][y] instanceof EmptySea)
							continue;
						else
							count += 1;
					}
				}
			}
			return count == 0;
		} else if (!horizontal && row < ocean.ships.length + 1 - length) {
			for (int x = row - 1; x < row + length + 1; x++) {
				for (int y = column - 1; y < column + 2; y++) {
					if (0 <= x && 0 <= y && x < ocean.ships.length && y < ocean.ships.length) {
						if (ocean.ships[x][y] instanceof EmptySea)
							continue;
						else
							count += 1;
					}
				}
			}
			return count == 0;
		} else
			return false;
	}

	/**
	 * "Puts" the ship in the ocean.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (okToPlaceShipAt(row, column, horizontal, ocean)) {
			this.bowRow = row;
			this.bowColumn = column;
			this.horizontal = horizontal;
			if (horizontal) {
				for (int i = column; i < column + length; i++) {
					ocean.ships[row][i] = this;
				}
			} else {
				for (int i = row; i < row + length; i++) {
					ocean.ships[i][column] = this;
				}
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship
	 * hasn't already been sunk, marks that part of the ship as "hit" and return
	 * true, otherwise return false.
	 * 
	 * @param row
	 *            Row number from player.
	 * @param column
	 *            Column number from player.
	 * @return Return true if a part of the ship occupies the given row and
	 *         column, and the ship hasn't already been sunk, otherwise return
	 *         false.
	 */
	boolean shootAt(int row, int column) {
		if (!isSunk()) {
			if (length >= 1) {
				if (row == bowRow && column == bowColumn)
					return hit[0] = true;
			}
			if (length >= 2) {
				if ((row == bowRow && column == bowColumn + 1) || (row == bowRow + 1 && column == bowColumn))
					return hit[1] = true;
			}
			if (length >= 3) {
				if ((row == bowRow && column == bowColumn + 2) || (row == bowRow + 2 && column == bowColumn))
					return hit[2] = true;
			}
			if (length == 4) {
				if ((row == bowRow && column == bowColumn + 3) || (row == bowRow + 3 && column == bowColumn))
					return hit[3] = true;
			}
		}
		return false;
	}

	/**
	 * Checks the ships has sunk or not.
	 * 
	 * @return Return true if every part of the ship has been hit, false
	 *         otherwise.
	 */
	boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (!hit[i])
				return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * Returns "x" if the ship has been sunk, "S" if it has not been sunk.
	 */
	@Override
	public String toString() {
		if (isSunk())
			return "x";
		return "S";
	}
}
