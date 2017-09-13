package graph;

/**
 * Adjacnet matrix class for extra credit
 * @author paulhsu
 *
 */
public class AdjancentMatrix {
	
	/**
	 * Makes the adjacent matrix 
	 * @param friendList
	 * @param input
	 * @return
	 */
	public int[][]  makeAdjacentMatrix(int[][] friendList, String input) {
		String [] relationshipSet = input.split(" ");
		int friend1 = Integer.parseInt(relationshipSet[0]);
		int friend2 = Integer.parseInt(relationshipSet[1]);
		friendList[friend1][friend2] = 1;
		friendList[friend2][friend1] = 1;
		return friendList;
	}
	
}
