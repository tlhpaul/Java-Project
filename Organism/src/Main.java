

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The game simulator
 * @author paulhsu
 *
 */
public class Main {
	static double p = 0.05;
	static double q = 0.1;
	static GameBrain gameBrain = new GameBrain();
	static ArrayList<Player> players = new ArrayList<Player>(); 
	static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		System.out.println("Welcome to Organism Game!");
		System.out.println("Pleas enter your name: ");
		String name = scanner.next();
		OrganismsGame organismsGame = new OrganismsGame();
		HumanPlayer hp = new HumanPlayer(name);
		ComputerPlayer cp = new ComputerPlayer();
		players.add(hp);
		players.add(cp);
		organismsGame.initialize(gameBrain, p, q, players);
		System.out.println();
		int count = 0;
		while(count < 800){
			organismsGame.setRound(count);
			if (organismsGame.playGame()){
				count++;
			} else {
				break;
			}
			System.out.println();
			System.out.println("This round is over! ");
			for (int i = 0; i < organismsGame.getResults().size(); i++){
				System.out.println("Total count for " + organismsGame.getResults().get(i) + " is " + organismsGame.getResults().get(i).getCount());
				System.out.println("Total energy for " + organismsGame.getResults().get(i) + " is " + organismsGame.getResults().get(i).getEnergy());
				System.out.println();
			}
		} 
		System.out.println("Game is over! ");
		for (int i = 0; i < organismsGame.getResults().size(); i++){
			System.out.println("Total count for " + organismsGame.getResults().get(i) + " is " + organismsGame.getResults().get(i).getCount());
			System.out.println("Total energy for " + organismsGame.getResults().get(i) + " is " + organismsGame.getResults().get(i).getEnergy());
		}
	}
}
