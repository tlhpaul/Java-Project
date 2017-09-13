import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {
	GenericNode<Integer> head;
	GenericNode<String> head1;
	DoublyLinkedList<Integer> doublyLinkedList;
	DoublyLinkedList<String> doublyLinkedList1;

	@Before
	public void setUp() throws Exception {
		head = new GenericNode<Integer>(1);
		head1 = new GenericNode<String>("a");
		doublyLinkedList = new DoublyLinkedList<>(head);
		doublyLinkedList1 = new DoublyLinkedList<>(head1);
	}

	@Test
	public void testAddE() {
		assertTrue(doublyLinkedList.add(4));
		assertFalse(doublyLinkedList.add(null));
		doublyLinkedList.remove(0);
		doublyLinkedList.remove(0);
		assertTrue(doublyLinkedList.add(4));
		assertTrue(doublyLinkedList1.add("b"));
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullPointerExceptionAddIntE() {
		doublyLinkedList.add(2);
		doublyLinkedList.add(3);
		doublyLinkedList.add(1, 4);
		int i = doublyLinkedList.getHead().next.value;
		assertEquals(4, i);
		assertNotEquals(2, i);
		doublyLinkedList.add(0, 5);
		i = doublyLinkedList.getHead().value;
		assertEquals(5, i);
		doublyLinkedList.add(4, 7);
		i = doublyLinkedList.getTail().previous.value;
		assertEquals(7, i);
		doublyLinkedList.add(1, null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsExceptionAddIntE1() {
		doublyLinkedList.add(5, 4);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(doublyLinkedList.isEmpty());
		doublyLinkedList.add(-2);
		doublyLinkedList.add(3);
		doublyLinkedList.clear();
		assertTrue(doublyLinkedList.isEmpty());
		doublyLinkedList.clear();
		assertTrue(doublyLinkedList.isEmpty());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveInt() {
		assertTrue(doublyLinkedList.remove(0) == 1);
		doublyLinkedList.add(2);
		doublyLinkedList.add(3);
		doublyLinkedList.add(4);
		doublyLinkedList.add(5);
		doublyLinkedList.add(6);
		assertTrue(doublyLinkedList.remove(0) == 2);
		assertTrue(doublyLinkedList.remove(3) == 6);
		assertTrue(doublyLinkedList.remove(1) == 4);
		assertFalse(doublyLinkedList.remove(0) == 5);
		doublyLinkedList.remove(4);
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveObject() {
		Integer i = 1;
		Integer i1 = 4;
		Integer i2 = 2; 
		Integer i3 = 5;
		String s1 = "b";
		String s2 = "c";
		String s3 = "aaaa";
		boolean b1 = true;
		doublyLinkedList1.add("b");
		doublyLinkedList1.add("c");
		doublyLinkedList.add(2);
		doublyLinkedList.add(4);
		doublyLinkedList.add(5);
		doublyLinkedList.add(2);
		assertTrue(doublyLinkedList.remove(i3));
		assertTrue(doublyLinkedList.remove(i));
		assertFalse(doublyLinkedList.remove(i));
		assertFalse(doublyLinkedList.remove(s3));
		assertFalse(doublyLinkedList.remove(b1));
		assertTrue(doublyLinkedList.remove(i1));
		assertTrue(doublyLinkedList.remove(i2));
		assertTrue(doublyLinkedList.getTail().value == 2);
		assertFalse(doublyLinkedList.remove(i));
		assertTrue(doublyLinkedList1.remove(s1));
		assertTrue(doublyLinkedList1.remove(s2));
		doublyLinkedList.remove(null);
	}
}
