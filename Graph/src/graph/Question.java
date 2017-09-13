package graph;

import java.util.ArrayList;
import java.util.Random;


/**
 * Question class to answer question A to F
 * @author paulhsu
 *
 */
public class Question {
	private ArrayList<Node> friendList;
	
	/**
	 * Question class constructor
	 * @param friendList a list of nodes
	 */
	public Question(ArrayList<Node> friendList) {
		this.friendList = friendList;
	}
	
	/**
	 * The main method to answer all questions
	 */
	public void answerQuestion() {
		questionA(friendList);
		questionB(friendList);
		questionC(friendList);
		questionD(friendList);
		questionE(friendList);
		questionF(friendList);
	}
	
	/**
	 * Answers question A 
	 * @param friendList a list of nodes
	 */
	public void questionA(ArrayList<Node> friendList) {
		System.out.println("Qestion A: ");
		BFS bfs = new BFS(friendList);
		bfs.search(75);
		int step = bfs.getStepFromNode(friendList, 2190);
		System.out.println("The distance between nodes 75 and 2190 is " + step);
		System.out.println();
	}
	
	/**
	 * Answers question B
	 * @param friendList a list of nodes
	 */
	public void questionB(ArrayList<Node> friendList) {
		System.out.println("Question B: ");
		DFS dfs = new DFS(friendList);
		dfs.search(1);
		System.out.println("If I start DFS from node 1, the start/finish time is " + 
		dfs.getStartFromNode(1) + "/" + dfs.getFinishFromNode(1));
		System.out.println(dfs.getFinishFromNode(1) + " is two times of number of nodes which is "
				+ "" + friendList.size());
		System.out.println("So the graph is connected, otherwise the finish time for node 1 "
				+ "won't be " + dfs.getFinishFromNode(1));
		System.out.println();
	}
	
	/**
	 * Answers question C
	 * @param friendList a list of nodes
	 */
	public void questionC(ArrayList<Node> friendList) {
		System.out.println("Question C: ");
		Random random = new Random();
		for(int i = 0; i < 3; i++){
			BFS bfs = new BFS(friendList);
			int index = random.nextInt(friendList.size() - 1);
			Node node = friendList.get(index);
			int start = node.getValue();
			System.out.print("If starting from " + start + ", the steps are ");
			bfs.getStepsToAllNodes(start);
		}
		System.out.println("Sometime the steps change, sometime they don't. Because "
				+ "even for different node, it could be possible\n"
				+ "they need same steps to visit all nodes, \n"
				+ "For most of cases, different nodes need different number of steps to visit all nodes.");
		System.out.println();
	}
	
	/**
	 * Answers question D
	 * @param friendList a list of nodes
	 */
	public void questionD(ArrayList<Node> friendList) {
		System.out.println("Question D: ");
		for (int i = 0; i < 3; i++) {
			DFS dfs = new DFS(friendList);
			dfs.search(10);
			System.out.println("Node 1 Start/Finish: " + dfs.getStartFromNode(1) + "/" + 
			dfs.getFinishFromNode(1));
			System.out.println("Node 18 Start/Finish: " + dfs.getStartFromNode(18) + "/" + 
			dfs.getFinishFromNode(18));
		}
		System.out.println("No, they don't change. Because of my implementation of DFS, "
				+ "it runs in the same way every time.\nOr we can say DFS visit nodes "
				+ "in the same way every time.\n"
				+ "So I would get the same start and "
				+ "finish time for the same node every time.");
		System.out.println();
	}
	
	/**
	 * Answers question E
	 * @param friendList a list of nodes
	 */
	public void questionE(ArrayList<Node> friendList) {
		System.out.println("Question E:");
		BFS bfs = new BFS(friendList);
		bfs.search(1912);
		int count = 0;
		for (int i = 0; i < friendList.size(); i++) {
			Node node = friendList.get(i);
			if (node.getStep() <= 3) {
				count ++;
			} 
		}
		System.out.println("The number of node within a distance of 3 from node 1912 is " + count);
		System.out.println();
	}
	
	/**
	 * Answers question F
	 * @param friendList a list of nodes
	 */
	public void questionF(ArrayList<Node> friendList) {
		int count = 0;
		System.out.println("Question F: ");
		ArrayList<Integer> finalClique = findBiggestClique(friendList);
		System.out.println("Yes, I use BFS. Because we need to check whether the nodes are adjacent.");
		System.out.println("The maximum clique size is " + finalClique.size());
		System.out.print("It includes ");
		for (int i = 0; i < finalClique.size(); i++) {
			count ++;
			System.out.print(finalClique.get(i) + " ");
			if (count % 10 == 0) {
				System.out.println();
			}
		}
	}
	
	/**
	 * Helper method for questionF method, to find the biggest number of clique
	 * @param friendList a list of nodes
	 * @return list includes the biggest number of clique
	 */
	public ArrayList<Integer> findBiggestClique(ArrayList<Node> friendList) {
		ArrayList<Integer> clique;
		ArrayList<Integer> finalClique = new ArrayList<Integer>();
		BFS bfs = new BFS(friendList);
		for (int i = 0; i < friendList.size(); i ++) {
			Node node = friendList.get(i);
			ArrayList<Integer> friendInNode = node.getFriends();
			for (int j = 0; j < friendInNode.size(); j++) {
				clique = new ArrayList<>();
				int friend1 = friendInNode.get(j);
				clique.add(friend1);
				for (int k = 1; k < friendInNode.size(); k++) {
					int friend2 = friendInNode.get(k);
					if(bfs.isAdjacentTo(friend1, friend2)) {
						clique.add(friend2);
					}
				}
				if (clique.size() > finalClique.size()) {
					finalClique = clique;
					finalClique.add(node.getValue());
				}
			}
		}
		return finalClique;
	}

}
