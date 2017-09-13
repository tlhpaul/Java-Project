import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class. Asks user to input file path, enter two names they are interested to find the relationship. 
 * @author paulhsu
 *
 */
public class FamilyTreeTester {
	static String familyRelationship = "";
	static BuildTree buildTree;
	static FamilyTree familyTree ;

	public static void main(String[] args){
		while (true){
			try{
				Scanner scanner = new Scanner(System.in); 
				System.out.print("Please input file Name: ");
				File inputFile = new File(scanner.nextLine().trim());
				checkFile(inputFile);
		    	Scanner scan = new Scanner(inputFile);	
		    	while(scan.hasNextLine()){
		    		familyRelationship += scan.nextLine();
		    		familyRelationship += "\n";
		    	}
		    	createFamilyTree(familyRelationship);
		    	askForRelationship(buildTree.getRoot());
		    	scanner.close();
		        scan.close();
		        break;
	        	} 
			catch (FileNotFoundException e) {
				System.out.println("Can't find file or it is an invalid file.");
				}
			catch (NullPointerException e){
				System.out.println("Invalid input. The input is case-sensitive.");
			}
			catch (StringIndexOutOfBoundsException e){
				System.out.println("It is an empty file, please try other files.");
			} 
    	}
	}
	
	/**
	 * Receives relationship dataset, buidls the tree, and prints out the tree in preorder and postorder traversal.
	 * Also prints out the stats for the tree
	 * @param familyRelationship
	 */
	public static void createFamilyTree(String familyRelationship){
		buildTree = new BuildTree(familyRelationship);
    	buildTree.processInput();
    	FamilyTree familyTree = new FamilyTree(buildTree.getRoot());
    	familyTree.preorderTraversal();
    	familyTree.postorderTraversal();
    	familyTree.displayStatistics();
	}
	
	/**
	 * Asks users to input two names in order to find the relationship. 
	 * @param root Tree's root
	 */
	public static void askForRelationship(Node root){
		FamilyTree familyTree = new FamilyTree(root);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input two name to find the relationship.");
    	System.out.print("First person's name: ");
    	String name = scanner.nextLine().trim();
    	System.out.print("Second person's name: ");
    	String name2 = scanner.nextLine().trim();
    	familyTree.showRelationship(name, name2);
    	scanner.close();
	}
	
	/**
	 * Checks if the file exits, can be read, and is a file. 
	 * @param file Input file
	 * @return true if file exits, can be read, and is a file. 
	 */
	public static boolean checkFile(File file){
		if (file.exists() && file.canRead() && file.isFile()){
			return true;
		}
		return false;
	}
	
}
