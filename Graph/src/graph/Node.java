package graph;

import java.util.ArrayList;

/**
 * Node class
 * @author paulhsu
 *
 */
public class Node {
	private int value;
	private ArrayList<Integer> friends = new ArrayList<>();
	private int start;
	private int finish;
	private int step;
	private boolean adjacentToEach;
	
	/**
	 * Node constructor
	 * @param value
	 */
	public Node(int value) {
		this.value = value; 
		start = 0;
		finish = 0;
		step = 0;
		adjacentToEach = true;
	}
	
	/**
	 * Gets value from node
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Adds new number into friend list
	 * @param value new number we want to add into friend list
	 */
	public void addFriends(int value) {
		friends.add(value);
	}
	
	/**
	 * Gets friend list
	 * @return friend list
	 */
	public ArrayList<Integer> getFriends() {
		return friends;
	}
	
	/**
	 * Sets start time into node
	 * @param value start time
	 */
	public void setStart(int value) {
		start = value;
	}
	
	/**
	 * Sets finish time into node
	 * @param value finish time
	 */
	public void setFinish(int value) {
		finish = value;
	}
	
	/**
	 * Gets start time from node 
	 * @return start time
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Gets finish time from node 
	 * @return finish time
	 */
	public int getFinish() {
		return finish;
	}
	
	/**
	 * Sets number of step into node 
	 * @param value number of step
	 */
	public void setStep(int value) {
		step = value;
	}
	
	/**
	 * Gets number of step into node 
	 * @return number of step
	 */
	public int getStep() {
		return step;
	}
	
	public void setAdjacentToEach(boolean adjacentToEach){
		this.adjacentToEach = adjacentToEach;
	}
	
	public boolean getAdjacentToEach(){
		return adjacentToEach;
	}

}
