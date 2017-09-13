package graph;

import java.util.ArrayList;

/**
 * BFS class. Runs Breadth-first search.
 * @author paulhsu
 *
 */
public class BFS {
	private ArrayList<Node> friendList;
	private Queue queue;
	private Queue seen; 
	
	/**
	 * BFS constructor. 
	 * @param friendList a list of nodes
	 */
	public BFS(ArrayList<Node> friendList) {
		this.friendList = friendList;
		queue = new Queue();
		seen = new Queue();
	}
	
	/**
	 * Iterates adjacent list to build BFS
	 * @param start the number to start BFS
	 */
	public void search(int start) {
		queue.enqueue(start);
		setStepToNode(friendList, start, 0);
		while (! queue.isEmpty()) {
			int firstValue = queue.dequeue();
			seen.enqueue(firstValue);
			int stepFromFirstNode = getStepFromNode(friendList, firstValue);
			Node firstValueNode = getNode(friendList, firstValue);
			ArrayList<Integer> connectedNumber = firstValueNode.getFriends();
			for (int i = 0; i < connectedNumber.size(); i++) {
				int friend = connectedNumber.get(i);
				if (! seen.contains(friend) && ! queue.contains(friend)) {
					queue.enqueue(friend);
					setStepToNode(friendList, friend, stepFromFirstNode + 1);
				} 
			}
		}
	}
	
	/**
	 * Sets number of frontiers into the node.
	 * @param friendList adjacent list
	 * @param value the value we are looking for
	 * @param step the number of step we would like to put into node
	 */
	public void setStepToNode(ArrayList<Node> friendList, int value, int step) {
		Node node = getNode(friendList, value);
		node.setStep(step);
	}
	
	/**
	 * Gets the number of frontiers from the node.
	 * @param friendList adjacent list
	 * @param value the value we are looking for
	 * @return the number of step from the node
	 */
	public int getStepFromNode(ArrayList<Node> friendList, int value) {
		Node node = getNode(friendList, value);		
		return node.getStep();
	}
	
	/**
	 * Returns the steps for visiting all nodes
	 * @param start which node we would like to start with
	 * @return number of steps to visit all nodes
	 */
	public void getStepsToAllNodes(int start) {
		search(start);
		int lastNumber = seen.getLastValue();
		Node node = getNode(friendList, lastNumber);
		System.out.println(node.getStep());
	}
	
	/**
	 * Checks whether two number are adjacent to each other
	 * @param friend1 number we are interested in
	 * @param friend2 number we are interested in
	 * @return true if friend1 and 2 is adjacent.
	 */
	public boolean isAdjacentTo(int friend1, int friend2) {
		Node friend1Node = getNode(friendList, friend1);
		ArrayList<Integer> friend = friend1Node.getFriends();
		return friend.contains(friend2);
	}
	
	/**
	 * Gets node with given number from adjacent list 
	 * @param friendList adjacent list 
	 * @param value given number
	 * @return node with given number
	 */
	public static Node getNode(ArrayList<Node> friendList, int value) {
		Node node = null;
		for (int i = 0; i < friendList.size(); i++) {
			node = friendList.get(i);
			if (node.getValue() == value) {
				return node;
			}
		}
		return node;
	}
}
