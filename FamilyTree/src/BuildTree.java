import java.util.ArrayList;
import java.util.Arrays;

/**
 * Build tree class. It processes the input file, creates the tree and return tree's root.
 * @author paulhsu
 *
 */
public class BuildTree {
	private Node root;
	private String familyRelationship;
	private ArrayList<String> relationship;
	private String[] parentChild;
	private ArrayList<String[]> relationship1 = new ArrayList<String[]>();
	private FindNode findNode;
	
	/**
	 * Buildtree constructor. 
	 * @param familyRelationship
	 */
	public BuildTree(String familyRelationship) {
		this.familyRelationship = familyRelationship;
		findNode = new FindNode();
	}
	
	/**
	 * Processes the input file, and then creates the tree by createTree method.
	 */
	public void processInput(){
		relationship = new ArrayList<String>(Arrays.asList(familyRelationship.split("\n")));
		for(int i = 0; i < relationship.size(); i++){
			String parent = "";
			String child = "";
			int index = relationship.get(i).indexOf(", ");
			parent = relationship.get(i).substring(0, index);
			child = relationship.get(i).substring(index+2);
			parentChild = new String[]{parent, child};
			relationship1.add(parentChild);
		}
		createTree();
	}
	
	/**
	 * Creates family tree
	 */
	public void createTree(){
		ArrayList<Node> rootChildren = new ArrayList<Node>();
		root = new Node(relationship1.get(0)[0], rootChildren);
		for(int i = 0; i < relationship1.size(); i++){
			Node parent = null;
			if(findNode.getNodeFromTree(root, relationship1.get(i)[0]) != null){
				parent = findNode.getNodeFromTree(root, relationship1.get(i)[0]);
			} else{
				ArrayList<Node> children = new ArrayList<Node>();
				parent = new Node(relationship1.get(i)[0], children);
			}
			ArrayList<Node> children = new ArrayList<Node>();
			parent.setChildren(new Node(relationship1.get(i)[1], children));
		}
	}
	
	/**
	 * Returns the root of tree
	 * @return the root of tree
	 */
	public Node getRoot(){
		return root;
	}
}
