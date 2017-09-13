package graph;

import java.util.ArrayList;

/**
 * DFS class. Runs Depth-First Search
 * @author paulhsu
 *
 */
public class DFS {
	private ArrayList<Node> friendList;
	private Stack stack;
	private Queue seen;
	private int time;
	
	/**
	 * DFS constructor
	 * @param friendList
	 */
	public DFS(ArrayList<Node> friendList) {
		this.friendList = friendList;
		stack = new Stack();
		seen = new Queue();
		time = 0;
	}
	
	/**
	 * Iterates adjacent list to build DFS
	 * @param start the number to start DFS
	 */
	public void search(int start) {
		stack.push(start);
		while(! stack.isEmpty()) {
			int lastValue = stack.pop();
			setStartOnNode(lastValue, ++time);
			seen.enqueue(lastValue);
			Node lastValueNode = getNode(friendList, lastValue);
			ArrayList<Integer> connectedNumber = lastValueNode.getFriends();
			for(int i = 0; i < connectedNumber.size(); i++) {
				int friend = connectedNumber.get(i);
				if(! seen.contains(friend)) {
					search(friend);
					time ++;
					setFinishOnNode(lastValue, time + 1);
					setFinishOnNode(friend, time);
				} 
			}
		}
	}

	/**
	 * Sets the start time into node
	 * @param value the value in the node
	 * @param time start time
	 */
	public void setStartOnNode(int value, int time) {
		Node node = getNode(friendList, value);
		node.setStart(time);
	}
	
	/**
	 * Sets the finish time into node
	 * @param value the value in the node
	 * @param time finish time
	 */
	public void setFinishOnNode(int value, int time) {
		Node node = getNode(friendList, value);
		node.setFinish(time);
	}
	
	/**
	 * Gets the start time from node 
	 * @param value the value in the node
	 * @return start time from node
	 */
	public int getStartFromNode(int value) {
		Node node = getNode(friendList, value);
		return node.getStart();
	}
	
	/**
	 * Gets the finish time from node
	 * @param value the value in the node
	 * @return finish time from node
	 */
	public int getFinishFromNode(int value) {
		Node node = getNode(friendList, value);
		return node.getFinish();
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
