package battleship;

/**
 * @author Tse-Lun Hsu
 *
 */
public class Submarine extends Ship {

	public Submarine() {
		this.length = 1;
		this.number = 4;
		hit = new boolean[4];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getNumberOfShip() Returns number of ships.
	 */
	@Override
	int getNumberOfShip() {
		return number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getLength() Returns the length of this particular
	 * ship.
	 */
	@Override
	int getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see battleship.Ship#getShipType() Returns the type of this ship.
	 */
	@Override
	String getShipType() {
		return "submarine";
	}
	
	
}

