package graph;

import java.util.ArrayList;

/**
 *Adjacent list class. 
 * @author paulhsu
 *
 */
public class AdjancentList {
	
	/**
	 * Splits the input and makes the adjacent list, 
	 * @param friends List List of node
	 * @param input single line input from the file
	 * @return a list of node with updated information 
	 */
	public ArrayList<Node> makeAdjancentList(ArrayList<Node> friendsList, String input){
		String [] relationshipSet = input.split(" ");
		int friend1 = Integer.parseInt(relationshipSet[0]);
		int friend2 = Integer.parseInt(relationshipSet[1]);
		return becomeFriend(friendsList, friend1, friend2);
	}
	
	/**
	 * Adds new numbers into nodes in list of adjacent list respectively
	 * @param friendsList List of node
	 * @param friend1 first number in the line 
	 * @param friend2 second number in the line
	 * @return a list of node with updated information 
	 */
	public ArrayList<Node> becomeFriend(ArrayList<Node> friendsList, int friend1, int friend2){
		if (friendsList.size() == 0) {
			friendsList.add(new Node(friend1));
		}
		friendsList = mutualFriend(friendsList, friend1, friend2);
		friendsList = mutualFriend(friendsList, friend2, friend1);
		return friendsList;
	}
	
	/**
	 * Adds new number into list of adjacent list
	 * @param friendsList List of node
	 * @param friend1 first number in the line 
	 * @param friend2 second number in the line
	 * @return a list of node with updated information 
	 */
	public ArrayList<Node> mutualFriend(ArrayList<Node> friendsList, int friend1, int friend2){
		for (int i = 0; i < friendsList.size(); i++) {
			Node node = friendsList.get(i);
			if (node.getValue() == friend1) {
				node.addFriends(friend2);
				return friendsList;
			} 
		}
		Node node1 = new Node(friend1);
		node1.addFriends(friend2);
		friendsList.add(node1);
		return friendsList;
	}
}
