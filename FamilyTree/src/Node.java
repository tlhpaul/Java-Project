import java.util.ArrayList;

/**
 * Node class. Stores name and subtrees. 
 * @author paulhsu
 *
 */
public class Node {
	
	private String name;
	private ArrayList<Node> children;
	
	/**
	 * Node constructor
	 * @param name
	 * @param children
	 */
	public Node(String name, ArrayList<Node> children) {
		this.name = name;
		this.children = children;
	}
	
	/**
	 * Returns node's name
	 * @return node's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns an node arraylist with nodes of children
	 * @return
	 */
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	/**
	 * Returns an string arraylist with names of children
	 * @return
	 */
	public ArrayList<String> getChildrenName(){
		ArrayList<String> childrenName = new ArrayList<String>();
		for(int i = 0; i < getNumberOfChildren(); i++){
			Node node = children.get(i);
			childrenName.add(node.getName());
		}
		return childrenName;
	}
	
	/**
	 * Returns the number of children.
	 * @return
	 */
	public int getNumberOfChildren(){
		if(children == null) return 0;
		return children.size();
	}
	
	/**
	 * Adds child into children arraylist.
	 * @param child
	 */
	public void setChildren(Node child){
		children.add(child);
	}
}
