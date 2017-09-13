package Simple21;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21".<p>
 * In this game, the dealer deals two "cards" to each player,
 * one hidden, so that only the player who gets the card knows
 * what it is, and one face up, so that everyone can see it.
 * (Actually, what the other players see is the <i>total</i>
 * of each other player's visible cards, not the individual cards.)<p>
 *
 * The players then take turns requesting cards, trying to get
 * as close to 21 as possible, but not going over 21. These cards
 * will be visible to all players.  A player may pass (ask for no
 * more cards). Once a player has passed, he or she cannot later
 * ask for another card. When all players have passed, the
 * game ends.<p>
 *
 * The winner is the player who has come closest to 21 without
 * exceeding it. In the case of a tie, or if everyone goes over
 * 21, no one wins.<p>
 *
 * In this program, there are some number of computer players
 * and one human player. The game is only played once. 
 * 
 * @author David Matuszek
 * @author (Students: replace this with your name) 
 * @version 0
 */
public class GameControl {
    
    /** All the Player objects, including the Human player.
     * The number of players is set here; other places in the
     * program should use <code>players.length</code>. */
    Player[] players = new Player[4];
   
    /** passed[i] == true indicates that players[i] has passed. */
    boolean[] passed = new boolean[] {false, false, false, false};
    
    /** Used for getting input from the user. */
    Scanner scanner = new Scanner(System.in);
    
    /** A random number generator. */
    Random random = new Random();
    
    Player manny = new Player("Manny");
    Player john = new Player("John");
    Player cindy = new Player("Cindy");
    /**
     * The main method just creates a GameControl object and calls
     * its <code>run</code> method.
     * 
     * @param args Not used.
     */
    public static void main(String args[]) {    
        GameControl gc = new GameControl();
        gc.run();
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each
     * of the following actions:
     * <ol>
     *   <li>Create the Players (one of them a Human),</li>
     *   <li>Deal the initial two cards to each player,</li>
     *   <li>Control the play of the game, and</li>
     *   <li>Print the final results.</li>
     * </ol>
     */
    void run() { 
        // Students: your code goes here.
    	System.out.println("Welcome to the game of 21!");
    	createPlayers();
    	deal();
    	int x;
    	while(true){
    		controlPlay();
    		System.out.println();
    		x = 0;
    		for (boolean i : passed){
    			if (i == false) x += 1;
    		} if (x == 0) break;	
    	}
    	printResults();
    	if (findWinningPlayer() == -1) System.out.print("Nobody wins.");
    	else System.out.print(players[findWinningPlayer()].name + " wins with " + players[findWinningPlayer()].getScore() + ".");
    	}
  
    /**
     * Asks the human player for a name, and then creates a Human
     * object and all the other Player objects, saving them in the
     * <code>players</code> array. (Names of the other players may
     * be hardwired; don't ask the user for them.)
     */
    void createPlayers() {
        System.out.print("What is your name?  ");
        String humansName = scanner.next();
        // Students: your code goes here.
        Human person = new Human(humansName);
        players[0] = person;
        players[1] = manny;
        players[2] = john;
        players[3] = cindy;
    }
    
    /**
     * Deals the initial two cards (one hidden, one not hidden)
     * to each player.
     */
    void deal() { 
        // Students: your code goes here.
    	players[0].takeHiddenCard(nextCard());
    	players[0].takeVisibleCard(nextCard());
    	manny.takeHiddenCard(nextCard());
    	manny.takeVisibleCard(nextCard());
    	john.takeHiddenCard(nextCard());
    	john.takeVisibleCard(nextCard());
    	cindy.takeHiddenCard(nextCard());
    	cindy.takeVisibleCard(nextCard());
    }
    
    /**
     * Returns a random "card", represented by a integer between
     * 1 and 10, inclusive. The odds of returning a 10 are four
     * times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).<br />
     * <b>Note:</b> The java.util package contains a <code>Random</code>
     * class, which is perfect for generating random numbers.
     *
     * @return a random integer in the range 1..10.
     */
    int nextCard() { 
    	// Students: your code goes here.
    	int card = random.nextInt(13) + 1; 
    	if (card > 10){
    		card = 10;
    	}
    	return card;
    }

    /**
     * Gives each player in turn a chance to take a card, until all
     * players have passed. Prints a message when a player passes.
     * Once a player has passed, that player is not given another
     * chance to take a card.<br />
     * <b>Note:</b> The global array <code>passed</code> is used to
     * keep track of which players have passed.
     */
    void controlPlay() { 
        // Students: your code goes here.
    	if (passed[0] == false){
    		if (players[0].offerCard(players) == false) {
    			passed[0] = true; 
    			System.out.println(players[0].name + " passes.");
    		}
    		else players[0].takeVisibleCard(nextCard());
    	}
    	if (passed[1] == false){
    		if (manny.offerCard(players) == false || manny.getScore() >= 21) {
        		passed[1] = true; 
        		System.out.println(manny.name + " passes.");
        	}
    		else manny.takeVisibleCard(nextCard());	       		
    	}
    	if (passed[2] == false){
    		if (john.offerCard(players) == false || john.getScore() >= 21) {
        		passed[2] = true; 
        		System.out.println(john.name + " passes.");
        	}
        	else john.takeVisibleCard(nextCard());        			
    	}
    	if (passed[3] == false){
    		if (cindy.offerCard(players) == false || cindy.getScore() >= 21) {
        		passed[3] = true; 
        		System.out.println(cindy.name + " passes.");
        	}
        	else cindy.takeVisibleCard(nextCard());	
    	}
    }
    
    /**
     * Prints a summary at the end of the game, saying how many
     * points each player had, and who won.
     */
    void printResults() { 
        // Students: your code goes here.
    	System.out.println("Game over.");
    	for (Player player : players){
    		System.out.println(player.name + " has " + player.getScore() + " points.");	
    	}
    }

    /**
     * Determines who won the game, and returns it as an index into
     * the players array.
     * 
     * @return The index of the winner, or -1 if nobody won.
     */
    int findWinningPlayer() { 
        // Students: your code goes here.
    	ArrayList<Integer> score = new ArrayList<Integer>();
    	if (players[0].getScore() <= 21) score.add(players[0].getScore());
    	if (manny.getScore() <= 21) score.add(manny.getScore());
    	if (john.getScore() <= 21) score.add(john.getScore());
    	if (cindy.getScore() <= 21) score.add(cindy.getScore());
    	if (score.size() == 1) {
    		for (Player player : players){
    			if (player.getScore() == score.get(score.size() - 1)) return Arrays.asList(players).indexOf(player);
    		} 		
    	}
    	if (score.size() > 1){
    		Collections.sort(score);
    		if (score.get(score.size() - 1) != score.get(score.size() - 2)){
    		for (Player player : players){
    			if (player.getScore() == score.get(score.size() - 1)) return Arrays.asList(players).indexOf(player);
    			}		
    		}  	
    	} return -1;
    }
}

