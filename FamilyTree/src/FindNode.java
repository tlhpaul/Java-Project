
/**
 * Finding node class. It has two main methods: find the node and node's parents with given name.  
 * @author paulhsu
 *
 */
public class FindNode {
	
	/**
	 * Finds node with given name from tree. 
	 * @param root Tree's root 
	 * @param name node's name
	 * @return node with name input. If it can't find the node, return node with null value.
	 */
	public Node getNodeFromTree(Node root, String name){
		Node node = null;
		if(root.getName().equals(name)){
			return root;
		} else if (root.getNumberOfChildren() != 0) {
			for(int i = 0 ; i < root.getNumberOfChildren(); i++){
				node = getNodeFromTree(root.getChildren().get(i), name);
				if(node != null){
					return node;
				}
			}
		}
		return node;
	}
	
	/**
	 * Finds node's parent with given name from tree. 
	 * @param root Tree's root
	 * @param name node's name
	 * @return node's parent with name input. If it can't find the node, return node with null value.
	 */
	public Node getParentFromTree(Node root, String name){
		Node childNode = null;
		Node node = null;
		if (root.getNumberOfChildren() != 0) {
			for(int i = 0 ; i < root.getNumberOfChildren(); i++){
				childNode = root.getChildren().get(i);
				if(childNode.getName().equals(name)){
					return root;
				} else if (childNode.getNumberOfChildren() != 0){
					node = getParentFromTree(childNode, name);
					if(node != null){
						return node;
					}
				}
			}
		}
		return node;
	}

}
