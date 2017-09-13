package battleship;

import java.util.Scanner;

/**
 * @author Tse-Lun Hsu
 *
 */
public class Game {
	Scanner scanner1 = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	Scanner scanner3 = new Scanner(System.in);
	int row, column, number;
	boolean horizontal;

	public static void main(String[] args) {
		Game battleshipGame = new Game();
		battleshipGame.run();
	}

	/**
	 * Accepts "shots" from the user; displays the results; prints final scores;
	 * and asks the user if he/she wants to play again.
	 */
	void run() {
		while (true) {
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			printRule();
			ocean.print();
			while (! ocean.isGameOver()) {
				try {
					getRowAndColumnNumber();
					ocean.shootAt(row, column);
					ocean.ships1[row][column] = ocean.getShipArray()[row][column].toString();
					changeToX(ocean, row, column);
					System.out.println();
					ocean.print();
					ocean.prinout();
				} catch (IndexOutOfBoundsException e) {
					System.out.println(
							"Please check your input numbers. Row number and column number should be both between 0 and 9. ");
				} catch (NumberFormatException e) {
					System.out.println("Please enter numbers. ");
				}
			}
			printGameIsOver(ocean);
			number = 0;
			while (number != 1 && number != 2) {
				try {
					number = askPlayerToPlayAgain();
				} catch (NumberFormatException e) {
					System.out.println("Your enter is incorrect.");
				}
			}
			if (number == 2)
				break;
		}
		System.out.println("Bye! ");
	}

	/**
	 * Prints the rule.
	 */
	void printRule() {
		System.out.println("Welcome to Battleship games! ");
		System.out.println(
				"There are one battleship, two cruisers, three destroyers and four submarines in the ocean board. ");
		System.out.println("The length of battleship is 4, cruiser is 3, destroyer is 2 and submarine is 1. ");
		System.out.println("The rule is that you need to use as low shots as possible to find out all ships. ");
		System.out.println("'S' indicates a location that you have fired upon and hit a ship,\n'-' "
				+ "indicates a location that you have fired upon and found nothing there,\n"
				+ "'x' indicates location containing a sunken ship,\n"
				+ "'.' indicates a location that you have never fired upon. ");
		System.out.println(
				"No ships are immediately adjacent to each other, either horizontally, vertically, or diagonally");
		System.out.println("PLease enter number from 0 to 9 for your row number and column number.");
		System.out.println();
	}

	/**
	 * Gets input from player.
	 */
	void getRowAndColumnNumber() {
		System.out.print("Please enter row number you would like to hit: ");
		this.row = Integer.parseInt(scanner1.next());
		System.out.print("Please enter column number you would like to hit: ");
		this.column = Integer.parseInt(scanner1.next());
	}

	/**
	 * Notifies player game is over. Prints the number of shots and how many
	 * shots hit the ships.
	 * 
	 * @param ocean
	 *            Ocean class.
	 */
	void printGameIsOver(Ocean ocean) {
		System.out.println();
		System.out.println("Game is over!");
		System.out.println("You fired " + ocean.getShotsFired() + " shots! ");
		System.out.println("There are " + ocean.getHitCount() + " shots hit ships! ");
		System.out.println();
	}

	/**
	 * Checks whether the ship has sunk or not. If the ships is sunk, changes
	 * the previous spots from "S" to "x".
	 * 
	 * @param ocean
	 *            Ocean class.
	 * @param row
	 *            row number from player.
	 * @param column
	 *            column number from player.
	 */
	void changeToX(Ocean ocean, int row, int column) {
		if (ocean.ships[row][column].isSunk()) {
			row = ocean.getShipArray()[row][column].getBowRow();
			column = ocean.getShipArray()[row][column].getBowColumn();
			horizontal = ocean.getShipArray()[row][column].isHorizontal();
			if (horizontal) {
				for (int i = column; i < column + ocean.getShipArray()[row][column].getLength(); i++) {
					ocean.ships1[row][i] = ocean.getShipArray()[row][i].toString();
				}
			} else {
				for (int i = row; i < row + ocean.getShipArray()[row][column].getLength(); i++) {
					ocean.ships1[i][column] = ocean.getShipArray()[i][column].toString();
				}
			}
			System.out.println();
			System.out.println("You just sank a " + ocean.ships[row][column].getShipType() + ".");
		}
	}

	/**
	 * Asks player wants to play again or not. He/She needs to enter 1 or 2 to
	 * specify.
	 * 
	 * @return Either 1 or 2.
	 */
	int askPlayerToPlayAgain() {
		int answer = 0;
		System.out.print("Please enter \"1\" to play again! " + "Enter \"2\" to quit! ");
		answer = Integer.parseInt(scanner1.next().replaceAll("\\s+", ""));
		return answer;
	}
}
