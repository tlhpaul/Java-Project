/**
 * Family tree class. Constructs this class with Node root.
 * Provides different type of relationships.
 * @author paulhsu
 *
 */
public class FamilyTree {
	private Node root;
	private int nodeNumber;
	private FindNode findNode;
	
	/**
	 * FamilyTree constructor
	 * @param root
	 */
	public FamilyTree(Node root) {
		this.root = root;
		nodeNumber = 1;
		findNode = new FindNode();
	}
	
	/**
	 * Shows the most precise relationship. 
	 * @param a
	 * @param b
	 */
	public void showRelationship(String a, String b){
		if(a.equals(b)){
			System.out.println("They are the same person.");
		}
		else if (isParent(a, b)) {
			System.out.println(a + " is " + b + "'s parent.");
		} 
		else if (isChild(a, b)){
			System.out.println(a + " is " + b + "'s child.");
		} 
		else if (isSibling(a, b)){
			System.out.println(a + " is " + b + "'s sibiling.");
		} 
		else if (isCousin(a, b)){
			System.out.println(a + " is " + b + "'s first cousin.");
		} 
		else if (isUncle(a, b)) {
			System.out.println(a + " is " + b + "'s uncle or aunt.");
		}
		else if (isGrandParents(a, b)) {
			System.out.println(a + " is " + b + "'s grand parent.");
		}
		else if (isAncestor(a, b)){
			System.out.println(a + " is " + b + "'s ancestor.");
		} 
		else if (isDescendant(a, b)){
			System.out.println(a + " is " + b + "'s descendant.");
		}
		else {
			System.out.println("They dont have any relationship.");
		}
		hasMoreChildren(a, b);
	}
	
	/**
	 * This method should return true if a is the parent of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is the parent of b
	 */
	public boolean isParent(String a, String b){
		Node nodeA = findNode.getNodeFromTree(root, a);
		if(nodeA.getChildrenName().contains(b)){
			return true;
		}
		return false;
	}
	
	/**
	 * This method should return true if a is the child of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is the child of b
	 */
	public boolean isChild(String a, String b){
		return isParent(b, a);
	}
	
	/**
	 * This method should return true if a is an ancestor of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is an ancestor of b
	 */
	public boolean isAncestor(String a, String b){
		Node nodeA = findNode.getNodeFromTree(root, a);
		Node nodeB = findNode.getNodeFromTree(nodeA, b);
		if(nodeB != null && ! a.equals(b)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method should return true is a is an descendant of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true is a is an descendant of b
	 */
	public boolean isDescendant(String a, String b){		
		return isAncestor(b,a);
	}
	
	/**
	 * This method should return true if a is a sibling of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return a is a sibling of b
	 */
	public boolean isSibling(String a, String b){
		Node aParent = findNode.getParentFromTree(root, a);
		if (aParent == null) {
			return false;
		}
		else if(isParent(aParent.getName(), b) && ! a.equals(b)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method should return true if a is a first cousin of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is a first cousin of b
	 */
	public boolean isCousin(String a, String b){
		Node aParent = findNode.getParentFromTree(root, a);
		Node bParent = findNode.getParentFromTree(root, b);
		if (aParent == null || bParent == null){
			return false;
		}
		if(isSibling(aParent.getName(), bParent.getName())) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method should return true if a is an uncle or aunt of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is an uncle or aunt of b
	 */
	public boolean isUncle(String a, String b){
		Node bParent = findNode.getParentFromTree(root, b);
		if (bParent == null) {
			return false;
		}
		else if(isSibling(a, bParent.getName())){
			return true;
		}
		return false;
	}
	
	/**
	 * This method should return true if a is a grand parent of b
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 * @return true if a is a grand parent of b
	 */
	public boolean isGrandParents(String a, String b){
		Node bParent = findNode.getParentFromTree(root, b);
		if (bParent == null) {
			return false;
		}
		else if (isParent(a, bParent.getName())){
			return true;
		}
		return false;
	}
	
	/**
	 * This method prints out who has more children 
	 * @param a Input name a from user.
	 * @param b Input name b from user.
	 */
	public void hasMoreChildren(String a, String b){
		Node nodeA = findNode.getNodeFromTree(root, a);
		Node nodeB = findNode.getNodeFromTree(root, b);
		if(nodeA != null && nodeB != null){
			if(nodeA.getNumberOfChildren() > nodeB.getNumberOfChildren()){
				System.out.println(a + " has more children than " + b + ".");
			} else if (nodeA.getNumberOfChildren() < nodeB.getNumberOfChildren()) {
				System.out.println(b + " has more children than " + a + ".");
			} else{
				System.out.println(b + " and " + a + " have same number of children.");
			}
		}
	}
	
	/**
	 * This method should print out the height of the tree, total number of nodes, and the root node.
	 */
	void displayStatistics(){
		System.out.println("Height of the tree is " + (getTreeHeight(root) - 1));
		System.out.println("Number of nodes is " + getNodeNumber(root));
		System.out.println("Root is " + root.getName());
	}
	
	/**
	 * This method should print out the contents of the tree using Preorder Traversal.
	 */
	void preorderTraversal(){
		System.out.print("Preorder traversal is ");
		printPreorder(root);
		System.out.println();
	}
	
	/**
	 * This method should print out the contents of the tree using Postorder Traversal.
	 */
	void postorderTraversal(){
		System.out.print("Postorder traversal is ");
		printPostorder(root);
		System.out.println();
	}
	
	/**
	 * This method should return tree's height
	 * @param root Tree's root 
	 * @return tree's height
	 */
	public int getTreeHeight(Node root){
		if(root == null) {
			return 0;
		} else{
			int maxHeight = 0;
			for(int i = 0; i < root.getNumberOfChildren(); i ++){
				maxHeight = Math.max(maxHeight, getTreeHeight(root.getChildren().get(i)));
			}
			return maxHeight + 1;
		}
	}
	
	/**
	 * This method should return number of nodes in the tree
	 * @param root Tree's root
	 * @return number of nodes in the tree
	 */
	public int getNodeNumber(Node root){
		if(root.getNumberOfChildren() != 0){
			for(int i = 0; i < root.getNumberOfChildren(); i++){
				nodeNumber += 1;
				getNodeNumber(root.getChildren().get(i));
			}
		}
		return nodeNumber;
	}
	
	/**
	 * Prints the tree in preorder traversal based on the tree's root
	 * @param root tree's root
	 */
	public void printPreorder(Node root){
		System.out.print(root.getName() + " ");
		if(root.getNumberOfChildren() != 0){
			for(int i = 0; i < root.getNumberOfChildren(); i ++){
				printPreorder(root.getChildren().get(i));
			}
		}
	}
	
	/**
	 * Prints the tree in postorder traversal based on the tree's root
	 * @param root tree's root
	 */
	public void printPostorder(Node root){
		if(root.getNumberOfChildren() != 0){
			for(int i = 0; i< root.getNumberOfChildren(); i++){
				printPostorder(root.getChildren().get(i));
			}
			System.out.print(root.getName() + " ");
		} else {
			System.out.print(root.getName() + " ");
		}
	}
}
