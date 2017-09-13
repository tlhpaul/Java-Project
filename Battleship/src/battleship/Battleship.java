package battleship;

/**
 * @author Tse-Lun Hsu
 *
 */
public class Battleship extends Ship {

	public Battleship() {
		this.length = 4;
		this.number = 1;
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
		return "battleship";
	}

}
