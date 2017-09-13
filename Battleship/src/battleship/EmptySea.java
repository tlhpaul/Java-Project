package battleship;

/**
 * @author Tse-Lun Hsu
 *
 */
public class EmptySea extends Ship {

	public EmptySea() {
		this.length = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getNumberOfShip() 
	 * Returns 0 since there is no emptysea ship.
	 */
	@Override
	int getNumberOfShip() {
		return 0;
	}
	
	/* 
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getLength()
	 * Returns the length of this particular ship.
	 */
	@Override
	int getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getShipType() 
	 * Returns "empty sea".
	 */
	@Override
	String getShipType() {
		return "empty sea";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#shootAt(int, int) 
	 * Always returns false to indicate that nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#isSunk() 
	 * Always returns false to indicate that user didn't sink anything.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#toString() Returns a single-character String "-" to
	 * use in the Ocean's print method
	 */
	@Override
	public String toString() {
		return "-";
	}
}
