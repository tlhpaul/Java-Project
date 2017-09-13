package Percolation;
import java.util.Random;

public class Percolation {
	public double p = 0.5000; 
	public int n;
	double d = 0.0500;

	public static void main(String[] args) {
		Percolation p = new Percolation();
		p.run();
	}
	
	void run(){
		Percolation percolate1 = new Percolation();
		percolate1.n = 50;
		percolate1.findProbability(percolate1.n);
		System.out.println();
		Percolation percolate2 = new Percolation();
		percolate2.n = 100;
		percolate2.findProbability(percolate2.n);
		System.out.println();
		Percolation percolate3 = new Percolation();
		percolate3.n = 200;
		percolate3.d = 0.02;
		percolate3.findProbability(percolate3.n);
	}
	
	/**ground(n, p) returns an array of n arrays of integer, 
	 * where each array is of length n, and each integer has probability p of being a sand grain, (1-p) of being empty (and dry). Use the encoding 0 = empty space, 1 = sand grain, 2 = water.
	 * @param n
	 * @param p
	 * @return
	 */
	int[][] ground(int n, double p){
		int [][] g = new int [n][n];
		for (int i = 0; i < n ; i ++){
			for(int j = 0; j < n; j ++){
				Random random = new Random();
				int x = random.nextInt(100) + 1;
				if (x < p * 100) g[i][j] = 1;
				else g[i][j] = 0;
				} 
			} 
		return g;
		}
		
	/**seep(array, row) causes water to flow from row into row+1, 
	 * modifying the array. In other words, this function performs one step of the simulation.
	 * @param ground
	 * @param row
	 */
	void seep(int[][] ground, int row){
		if (row == -1){
			//fill every empty slot in first row
			for (int i = 0; i < ground.length ; i ++){
				if (ground[0][i] == 0) ground[0][i] = 2;
			}
		} else {
			for (int i = 0; i < ground.length ; i ++){
			//if ground [row][i] is water and ground [row+1][i] is empty 
				if (ground[row][i] == 2 && ground[row + 1][i] == 0) ground[row + 1][i] = 2;
				}
			for (int i = 0; i < (ground.length - 1) ; i ++){
			// if ground [row + 1][i] is filled with water, keep checking its left and right until you meet sand.
				if (ground[row + 1][i] == 2 && ground[row + 1][i + 1] == 0) ground[row + 1][i + 1] = 2;	
				}		
			for (int i = (ground.length - 1); i > 0 ; i --){
				if (ground[row + 1][i] == 2 && ground[row + 1][i - 1] == 0) ground[row + 1][i - 1] = 2;
				}
			}
		}		
	
	/**Returns true if, after water has "seeped" as far as it can, 
	 * water has reached the bottom row, and false otherwise. For the example above, the result would be true.
	 * @param ground
	 * @return
	 */
	boolean percolate(int[][] ground){
		// call seep from row == -1 to row == ground.length - 2 
		// check if any slot in last row is filled with water 
		for (int i = -1; i < ground.length -1 ; i ++){
			seep(ground, i);
			}			
		for (int j = 0; j < ground.length; j ++){
			if (ground[ground.length - 1][j] == 2) return true;
		} return false;
	}
	
	/**For an n by n array, 
	 * determines the packing probability p that causes the array to have a 50% probability of water seeping all the way to the bottom.
	 * @param n
	 * @return
	 */
	double findProbability(int n){	
		while (true){
		int x = 0;
		for (int i = 0; i < 100; i ++){
			if (percolate(ground(n, p))) x = x + 1;	
			}	
		if (48 < x && x < 52) break;
		else if (40 < x && x < 60) {
			d = 0.0001;
			if (40 < x && x < 45) p = p - d;
			else p = p + d;
			}
		else if (30 < x && x < 70) {
			d = 0.0005;
			if (30 < x && x < 40) p = p - d;
			else p = p + d;
			}
		else if (20 < x && x < 80) {
			d = 0.001;
			if (20 < x && x < 30) p = p - d;
			else p = p + d;
			}
		else if (10 < x && x < 90) {
			d = 0.01;
			if (10 < x && x < 20) p = p - d;
			else p = p + d;
			}
		else if (10 > x) p = p - d;
		else if (x > 80) p = p + d;
		}
		System.out.printf("Probability for N = " + n + " is %.2f", p);
		return p;
	}
}
