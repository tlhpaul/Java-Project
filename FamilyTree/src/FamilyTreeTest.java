import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class FamilyTreeTest {
	Node root;
	Node node1;
	Node node2;
	Node node3;
	Node node4;
	Node node5;
	Node node6;
	Node node7;
	Node node8;
	Node node9;
	Node node10;
	Node node11;
	ArrayList<Node> chi;
	ArrayList<Node> chi1;
	ArrayList<Node> chi2;
	ArrayList<Node> chi3;
	ArrayList<Node> chi4;
	ArrayList<Node> chi5;
	FamilyTree familyTree;
	

	@Before
	public void setUp() throws Exception {
		node7 = new Node("7", null);
		node8 = new Node("8", null);
		node9 = new Node("9", null);
		node10 = new Node("10", null);
		node11 = new Node("11", null);
		chi3 = new ArrayList<>(Arrays.asList(node7));
		chi4 = new ArrayList<>(Arrays.asList(node8, node9, node10));
		chi5 = new ArrayList<>(Arrays.asList(node11));
		node3 = new Node("3", chi3);
		node4 = new Node("4", chi4);
		node5 = new Node("5", chi5);
		node6 = new Node("6", null);
		chi1 = new ArrayList<>(Arrays.asList(node3, node4));
		chi2 = new ArrayList<>(Arrays.asList(node5, node6));
		node1 = new Node("1", chi1);
		node2 = new Node("2", chi2);
		chi = new ArrayList<>(Arrays.asList(node1, node2));
		root = new Node("0", chi);
		familyTree = new FamilyTree(root);
	}

	
	@Test
	public void testIsParent() {
		assertTrue(familyTree.isParent("1", "3"));
		assertFalse(familyTree.isParent("3", "4"));
		assertTrue(familyTree.isParent("2", "6"));
		assertTrue(familyTree.isParent("0", "2"));
		assertFalse(familyTree.isParent("0", "4"));
		assertFalse(familyTree.isParent("1", "1"));
	}

	@Test
	public void testIsChild() {
		assertTrue(familyTree.isChild("3", "1"));
		assertFalse(familyTree.isChild("1", "3"));
		assertTrue(familyTree.isChild("5", "2"));
	}

	@Test
	public void testIsAncestor() {
		assertTrue(familyTree.isAncestor("0", "3"));
		assertTrue(familyTree.isAncestor("0", "5"));
		assertFalse(familyTree.isAncestor("1", "6"));
	}

	@Test
	public void testIsDescendant() {
		assertTrue(familyTree.isDescendant("3", "0"));
		assertFalse(familyTree.isDescendant("5", "1"));
	}

	@Test
	public void testIsSibling() {
		assertFalse(familyTree.isSibling("3", "3"));
		assertTrue(familyTree.isSibling("5", "6"));
		assertTrue(familyTree.isSibling("8", "9"));
		assertFalse(familyTree.isSibling("7", "9"));
	}

	@Test
	public void testIsCousin() {
		assertTrue(familyTree.isCousin("7", "8"));
		assertFalse(familyTree.isCousin("7", "7"));
		assertFalse(familyTree.isCousin("3", "4"));
		
	}

	@Test
	public void testIsUncle() {
		assertTrue(familyTree.isUncle("3", "9"));
		assertFalse(familyTree.isUncle("7", "8"));
		assertFalse(familyTree.isUncle("5", "6"));
		assertFalse(familyTree.isUncle("3", "6"));
	}

	@Test
	public void testGetNodeFromTree() {
		familyTree.preorderTraversal();
	}

}
