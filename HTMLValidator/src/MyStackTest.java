import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
	GenericNode<Integer> head;
	MyStack<Integer> myStack; 

	@Before
	public void setUp() throws Exception {
		myStack = new MyStack<Integer>(1); 
	}

	@Test
	public void testEmpty() {
		assertFalse(myStack.empty());
		myStack.pop();
		assertTrue(myStack.empty());
	}

	@Test(expected = EmptyStackException.class)
	public void testPeek() throws Exception{
		myStack.push(3);
		myStack.push(4);
		assertTrue(myStack.peek() == 4);
		assertFalse(myStack.peek() == 3);
		myStack.pop();
		myStack.pop();
		myStack.pop();
		myStack.peek();
	}

	@Test (expected = EmptyStackException.class)
	public void testPop() throws Exception{
		assertTrue(myStack.pop() == 1);
		myStack.push(3);
		assertTrue(myStack.pop() == 3);
		myStack.push(4);
		assertTrue(myStack.pop() == 4);
		myStack.pop();
	}

	@Test
	public void testPush() {
		assertTrue(myStack.push(2) == 2);
		assertTrue(myStack.push(3) == 3);
		assertFalse(myStack.push(4) == 5);
	}
}
